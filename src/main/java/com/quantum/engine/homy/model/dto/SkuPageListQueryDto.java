package com.quantum.engine.homy.model.dto;

public class SkuPageListQueryDto {

	private Integer page = 1;
	private Integer pageSize = 10;
	private String keyword = "";
	private Integer goodsId ;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public boolean validate(){
		if(this.page < 0){
			return false;
		}
		if(this.pageSize <= 0){
			return false;
		}
		if(this.goodsId <= 0){
			return false;
		}
		return true;
	}
	
	
	
}
