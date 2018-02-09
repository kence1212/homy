package com.quantum.engine.homy.model.ext;

import com.quantum.engine.homy.model.BizOrderItem;

public class BizOrderItemShowExt extends BizOrderItem{
	
	private Integer brandId;
	
	private String goodsName;
	
	private String colorName;
	
	private String sizeName;
	
	private String textureName;
	
	private String skuIcon;
	
	

	public String getSkuIcon() {
		return skuIcon;
	}

	public void setSkuIcon(String skuIcon) {
		this.skuIcon = skuIcon;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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
