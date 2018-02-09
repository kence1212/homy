package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;

public class GetColorDto {

	private Integer goodsId;
	
	private Integer textureId;
	
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
		if(textureId != null && textureId <= 0){
			result.wrong("40000" , "材质ID不能小于0");
			return result;
		}
		if(sizeId != null && sizeId <= 0){
			result.wrong("40000" , "尺寸ID不能为空");
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

	public Integer getTextureId() {
		return textureId;
	}

	public void setTextureId(Integer textureId) {
		this.textureId = textureId;
	}

	public Integer getSizeId() {
		return sizeId;
	}
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
