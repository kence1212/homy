package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.BizGoodsSku;

public class BizGoodsSkuExt extends BizGoodsSku {
	
	private Integer brandId;
	
	private String brandName;
	
	private String goodsName;
	
	private String colorName;
	
	private String sizeName;
	
	private String textureName;
	
	

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
	
	
	
}