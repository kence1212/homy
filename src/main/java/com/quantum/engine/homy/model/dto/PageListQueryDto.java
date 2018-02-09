package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.BaseResponse;

public class PageListQueryDto {

	private Integer page = 1;
	private Integer pageSize = 10;
	private String keyword = "";
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
	
	public boolean validate(){
		if(this.page < 0){
			return false;
		}
		if(this.pageSize <= 0){
			return false;
		}
		return true;
	}
	
	
	
}
