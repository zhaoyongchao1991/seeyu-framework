package com.seeyu.core.utils;

public class BasePage {

    protected Integer pageSize = 20;
    protected Integer pageNum = 1;
    protected Long total;
    protected String orderBy;
    protected boolean paging = true;

    public Integer getPageSize() {
        return pageSize;
    }

    public BasePage setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public BasePage setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public BasePage setTotal(Long total) {
        this.total = total;
        return this;
    }

    public boolean isPaging() {
        return paging;
    }

    public BasePage setPaging(boolean paging) {
        this.paging = paging;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public BasePage setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
