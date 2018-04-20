package com.shimh.vo;

public class PageVo {

    private Integer pageNumber;

    private Integer pageSize;

    private String name;

    private String sort;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
