package com.example;

public class Pager {

    /**
     * 默认每页的数据量
     */
    public static final Integer DEFAULT_PAGE_SIZE = 100;

    /**
     * 默认当前页
     */
    public static final Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 最大每页数量
     */
    private static final Integer MAX_PAGE_SIZE = 10000;

    private Integer pageSize;	//每页的数据量

    private Integer currentPage;	//当前页

    private Integer recordTotal;	//数据总数

    private Integer pageTotal;	//总页数

    private Integer prevPage;	//上一页

    private Integer nextPage;	//下一页

    private Integer startNum;	//起始

    private Integer endNum;		//结束

    public Pager(Integer currentPage, Integer pageSize, Integer recordTotal){
        if(pageSize == null || pageSize <= 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }else {
            this.pageSize = pageSize;
        }
        if(currentPage == null || currentPage <= 0){
            currentPage = DEFAULT_CURRENT_PAGE;
        }
        if(pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }

        this.pageTotal = (int)Math.ceil((double)recordTotal / pageSize);	//计算总页数
        this.recordTotal = recordTotal;

        //防止超出已有页数
        if(currentPage > pageTotal)
            this.currentPage = pageTotal;
        else if(currentPage < 1)
            this.currentPage = 1;
        else
            this.currentPage = currentPage;

        this.endNum = this.currentPage * pageSize - 1;	//计算分页结束值
        this.startNum = this.currentPage * pageSize - pageSize;		//计算分页起始值
        if(this.startNum < 0) {
            this.startNum = 0;
        }

        //计算上一页、下一页
        if(this.currentPage == 1) {
            this.prevPage = 1;
        }else {
            this.prevPage = this.currentPage - 1;
        }
        if(this.currentPage == this.pageTotal) {
            this.nextPage = this.currentPage;
        }else {
            this.nextPage = this.currentPage + 1;
        }
    }

    public Integer getStartNum() {
        return startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }
}
