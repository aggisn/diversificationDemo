package com.example.demo.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class RequestMap {
    /**
     * 总数
     */
    private Long total;
    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 当前页展示数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 页数据
     */
    private List list;

    public RequestMap(Long total, Integer pageNo, Integer pageSize, Integer pages, List list) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pages = pages;
        this.list = list;
    }

    public RequestMap(PageInfo pageInfo, List list) {
        this.total = pageInfo.getTotal();
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.list = list;
    }

    public RequestMap() {

    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
