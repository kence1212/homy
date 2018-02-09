package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;

public class GetCityDto {

	private Integer goodsId;

	private Integer colorId;

	private Integer sizeId;

	private Integer textureId;

	public Result validate() {
		Result result = new Result();
		if (goodsId == null || goodsId <= 0) {
			result.wrong("40000", "商品ID不能为空");
			return result;
		}
		if (textureId == null && textureId <= 0) {
			result.wrong("40000", "材质ID不能为空");
			return result;
		}
		if (colorId != null && colorId <= 0) {
			result.wrong("40000", "颜色ID不能小于0");
			return result;
		}
		if (sizeId != null && sizeId <= 0) {
			result.wrong("40000", "尺寸ID不能为空");
			return result;
		}
		return result;
	}

	public Integer getTextureId() {
		return textureId;
	}

	public void setTextureId(Integer textureId) {
		this.textureId = textureId;
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
