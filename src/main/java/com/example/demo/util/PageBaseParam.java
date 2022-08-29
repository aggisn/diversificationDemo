package com.example.demo.util;

/**
 * @author xhj
 * @description 分页基础参数
 * @date 2020/05/19 16:05
 */
public class PageBaseParam {
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * @return the pageNo
     */
    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        if (pageSize == null || pageSize > 100) {
            this.pageSize = 10;
        }
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageBaseParam() {
        this.pageNo = 1;
        this.pageSize = 10;
    }
}
