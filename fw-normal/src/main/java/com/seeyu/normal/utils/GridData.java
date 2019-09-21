package com.seeyu.normal.utils;

import com.seeyu.core.utils.BasePage;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/4/25
 */
public class GridData {

    private Integer pageSize;
    private Integer pageNum;
    private Long total;
    private List list;

    private GridData() {
    }

    private GridData(BasePage page) {
        if(page.isPaging()){
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        }
    }

    public static GridData create(BasePage page, List list) {
        GridData gd = new GridData(page);
        gd.setList(list);
        return gd;
    }

    public static GridData create(com.github.pagehelper.Page pageInfo) {
        boolean paging = pageInfo.getPageNum() != 0 && pageInfo.getPageSize() != 0;
        GridData gd = new GridData(paging ? Page.createDefault().setPageNum(pageInfo.getPageNum()).setPageSize(pageInfo.getPageSize()).setTotal(pageInfo.getTotal()) : Page.createNoPaging());
        gd.list = pageInfo;
        return gd;
    }

    public static GridData create(com.github.pagehelper.Page pageInfo, List list) {
        GridData gd = GridData.create(pageInfo);
        gd.list = list;
        return gd;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Long getTotal() {
        return total;
    }
}
