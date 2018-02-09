package com.quantum.engine.homy.model.page;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	
	private static final Integer[] PAGE_NO_DEFAULT = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    public static final int PAGE_MIN_NO = 1;
    public static final int PAGE_MAX_NO = 10;
    /**页码前多少页*/
    public static final int PRE_PAGE_NUM = 4;
    /**页码后多少页*/
    public static final int NEXT_PAGE_NUM = 5;
    
    private int totalCount;
    private int pageSize = 5;
    private int pageNum = 1;
    private List<T> list;
    
    private Integer[] pageNo;
    
    public Page(){
        
    }
    
    public static Page getNullPage(){
    	 Page<Object> page = new Page<>(1, 10, 0);
    	 page.setList(new ArrayList<Object>());
    	 return page;
    }
    public Page(int pageNum , int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    public Page(int pageNum , int pageSize,int totalCount){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
    
    public boolean getHaveNext(){
        return pageNum < getTotalPage();
    }
    
    public boolean getHavePre(){
        return pageNum > 1;
    }
    
    public int getTotalPage(){
    	if(totalCount == 0)
    		return 1;
        return totalCount%pageSize == 0?(totalCount/pageSize):(totalCount/pageSize+1);
    }
    
    public Integer[] getTotalPageNo(){
    	int totalPage = getTotalPage();
    	pageNo = new Integer[totalPage];
        for(int i = 1;i <= totalPage ; i++){
            pageNo[i-1] = new Integer(i);
        }
        return pageNo;
    }
    /**
     * 取得页面显示页面列表
     * @return
     */
    public Integer[] getPageNo() {
    	try{
        int totalPage = getTotalPage();
        int pageMax = PAGE_MAX_NO ;
        int pageMin = PAGE_MIN_NO ;
        if(totalPage <= PAGE_MAX_NO){
            pageMax = totalPage;
        }else{
            if(pageNum <= (PRE_PAGE_NUM+1)){
                pageNo = PAGE_NO_DEFAULT;
            }else if(totalPage - pageNum < NEXT_PAGE_NUM){
                pageMax = totalPage;
                pageMin = (pageMax > PAGE_MAX_NO)? (pageMax - PAGE_MAX_NO + 1):1;
            }else{
                pageMax = (pageNum+NEXT_PAGE_NUM)>totalPage?totalPage:(pageNum+NEXT_PAGE_NUM);
                pageMin = pageNum - PRE_PAGE_NUM;
            }
        }
        int lenth = pageMax - pageMin + 1;
        pageNo = new Integer[lenth];
        for(int i = 0,j = pageMin ; j <= pageMax ; j++,i++){
            pageNo[i] = new Integer(j);
        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return pageNo;
    }
    public void setPageNo(Integer[] pageNo) {
        this.pageNo = pageNo;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    
}
