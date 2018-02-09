package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;

public class GetSizeDto {

	private Integer goodsId;
	
	private Integer textureId;
	
	private Integer colorId;
	
	private Integer cityId;
	
	public Result validate(){
		Result result = new Result();
		if(goodsId == null || goodsId <= 0){
			result.wrong("40000" , "商品ID不能为空");
			return result;
		}
		if(textureId != null && textureId <= 0){
			result.wrong("40000" , "材质ID不能小于0");
			return result;
		}
		if(colorId != null && colorId <= 0){
			result.wrong("40000" , "颜色ID不能为空");
			return result;
		}
		if(cityId != null && cityId <= 0){
			result.wrong("40000" , "城市ID不能为空");
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

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
	
}
