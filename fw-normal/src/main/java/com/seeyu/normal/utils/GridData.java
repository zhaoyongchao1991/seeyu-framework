package com.seeyu.normal.utils;

import com.seeyu.core.utils.BasePage;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/4/25
 */
public class GridData<T> {

    private Integer pageSize;
    private Integer pageNum;
    private Long total;
    private List<T> list;

    private GridData() {
    }

    private GridData(BasePage page) {
        if(page.isPaging()){
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        }
    }

    public static <E> GridData<E> create(BasePage page, List<E> list) {
        GridData<E> gd = new GridData<>(page);
        gd.setList(list);
        return gd;
    }

    public static <E> GridData<E> create(com.github.pagehelper.Page<E> pageInfo) {
        boolean paging = pageInfo.getPageNum() != 0 && pageInfo.getPageSize() != 0;
        GridData<E> gd = new GridData<>(paging ? Page.createDefault().setPageNum(pageInfo.getPageNum()).setPageSize(pageInfo.getPageSize()).setTotal(pageInfo.getTotal()) : Page.createNoPaging());
        gd.list = pageInfo;
        return gd;
    }

    public static <E> GridData<E> create(com.github.pagehelper.Page pageInfo, List<E> list) {
        GridData<E> gd = GridData.create(pageInfo);
        gd.list = list;
        return gd;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
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
