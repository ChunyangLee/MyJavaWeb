package com.lichunyang.bean;

import java.util.List;

public class Page<T> {
    private Integer pageNo;   //当前页
    private Integer pageTotalNo; //总页数
    private Integer pageTotalItems;  //总记录数
    private Integer pageSize;   //每页数目
    private List<T> bookList;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo<1){
            this.pageNo =1;
        }else if(pageNo>this.pageTotalNo){
            this.pageNo =this.pageTotalNo;
        }else{
            this.pageNo = pageNo;
        }
    }

    public Integer getPageTotalNo() {
        return pageTotalNo;
    }

    public void setPageTotalNo(Integer pageTotalNo) {
        this.pageTotalNo = pageTotalNo;
    }

    public Integer getPageTotalItems() {
        return pageTotalItems;
    }

    public void setPageTotalItems(Integer pageTotalItems) {
        this.pageTotalItems = pageTotalItems;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getBookList() {
        return bookList;
    }

    public void setBookList(List<T> bookList) {
        this.bookList = bookList;
    }

    public Page() {
    }
}
