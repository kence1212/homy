package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;

public class GetTextureDto {

	private Integer goodsId;
	
	private Integer colorId;
	
	private Integer sizeId;
	
	private Integer cityId;
	

	public Result validate(){
		Result result = new Result();
		if(goodsId == null || goodsId <= 0){
			result.wrong("40000" , "商品ID不能为空");
			return result;
		}
		if(cityId == null && cityId <= 0){
			result.wrong("40000" , "城市ID不能为空");
			return result;
		}
		if(colorId != null && colorId <= 0){
			result.wrong("40000" , "颜色ID不能小于0");
			return result;
		}
		if(sizeId != null && sizeId <= 0){
			result.wrong("40000" , "尺寸ID不能为空");
			return result;
		}
		return result;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}



	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSizeId() {
		return sizeId;
	}
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}
	
	
}
