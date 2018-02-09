package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;

public class ListReviewDto {
	
	private Integer goodsId;
	private Integer cityId;
	private Integer page;
	private Integer pageSize = 10; //默认为10条记录
	
	public Result validate(){
		
		Result result = new Result();
		if(goodsId == null || goodsId <= 0){
			result.wrong("40000" , "商品ID不能为空");
			return result;
		}
		if(cityId == null || cityId <= 0){
			result.wrong("40000" , "城市ID不能为空");
			return result;
		}
		if(page != null && page <= 0){
			result.wrong("40000" , "页数不能小于0");
			return result;
		}
		
		return result;
	
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
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
	
	
}
