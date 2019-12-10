package com.seeyu.normal.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageHelper;
import com.seeyu.core.utils.Alert;
import com.seeyu.core.utils.Assert;
import com.seeyu.core.utils.BasePage;
import com.seeyu.lang.utils.StringUtils;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/3/25
 */
public class Page extends BasePage {

    public static int MAX_PAGE_SIZE = 1000;
    public static final String SEPARATOR = ",";
    @JsonIgnore
    protected List<OrderEntry> orderByEntryList = new ArrayList<>();
    @JsonIgnore
    protected String defaultOrderBy;


    public static Page createDefault(){
        return new Page();
    }

    public static Page createNoPaging(){
        return new Page().setPaging(false).setPageNum(1).setPageSize(MAX_PAGE_SIZE);
    }


    @JsonIgnore
    public Integer getStart(){
        return (this.pageNum - 1) * this.pageSize;
    }


    @JsonIgnore
    public Integer getEnd(){
        return getStart() + this.pageSize;
    }


    @JsonIgnore
    public Integer getLimit(){
        return pageSize;
    }



    /**
     * 使用pageHelper框架进行分页
     */
    public void startPage(){
        //if(isPaging()){
            PageHelper.startPage(pageNum, pageSize);
        //}
        startOrderBy();
    }

    private void startOrderBy(){
        String orderByString = genOrderByString();
        if(StringUtils.isNotBlank(orderByString)){
            PageHelper.orderBy(orderByString);
        }
    }

    private String genOrderByString(){
        this.parseOrderBy();
        if(this.orderByEntryList == null || this.orderByEntryList.size() ==0){
            return null;
        }
        String[] ss = new String[this.orderByEntryList.size()];
        for(int i = 0; i < this.orderByEntryList.size(); i++){
            ss[i] = columnNameHumpBack(this.orderByEntryList.get(i).getOrderColumn()) + " " + this.orderByEntryList.get(i).getOrderType();
        }
        return StringUtils.StringArrays2String(ss, SEPARATOR);
    }


    private void parseOrderBy(){
        if(this.orderByEntryList.size() != 0){
            return;
        }
        this.orderBy = StringUtils.isNotBlank(this.orderBy) ? this.orderBy : defaultOrderBy;
        if(StringUtils.isBlank(this.orderBy)){
            return;
        }
        String[] obss = this.orderBy.split(SEPARATOR);
        for(String s : obss){
            OrderEntry orderEntry = null;
            String[] ss = s.trim().split("\\s+");
            if(ss.length == 1){
                orderEntry = new OrderEntry(ss[0]);
            }
            else if(ss.length == 2){
                orderEntry = new OrderEntry(ss[0],ss[1]);
            }
            else{
                Alert.alert("orderBy格式错误");
            }
            this.orderByEntryList.add(orderEntry);
        }
    }


    private String columnNameHumpBack(String columnName){
        StringBuilder sb = new StringBuilder();
        for(char c : columnName.toCharArray()){
            if(Character.isUpperCase(c)){
                sb.append("_").append(Character.toLowerCase(c));
            }
            else{
                sb.append(c);
            }
        }
        return sb.toString();
    }


    @Data
    @ToString
    private class OrderEntry {
        private String orderColumn;
        private String orderType;

        private OrderEntry(String orderColumn) {
            this(orderColumn, "asc");
        }

        private OrderEntry(String orderColumn, String orderType) {
            this.orderColumn = orderColumn;
            this.orderType = orderType;
        }
    }


    @Override
    public Page setPageSize(Integer pageSize) {
        super.setPageSize(Math.min(pageSize, MAX_PAGE_SIZE));
        return this;
    }

    @Override
    public Page setPageNum(Integer pageNum) {
        super.setPageNum(pageNum);
        return this;
    }

    @Override
    public Page setTotal(Long total) {
        super.setTotal(total);
        return this;
    }

    @Override
    public Page setOrderBy(String orderBy) {
        super.setOrderBy(orderBy);
        return this;
    }

    @Override
    public Page setPaging(boolean paging) {
        super.setPaging(paging);
        return this;
    }

    public String getDefaultOrderBy() {
        return defaultOrderBy;
    }

    public Page setDefaultOrderBy(String defaultOrderBy) {
        this.defaultOrderBy = defaultOrderBy;
        return this;
    }
}
