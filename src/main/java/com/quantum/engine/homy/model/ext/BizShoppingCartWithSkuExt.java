package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.BizShoppingCart;

public class BizShoppingCartWithSkuExt extends BizShoppingCart {
	
	private BizGoodsSkuExt bizGoodsSku;
	
	private Integer stock;
	
	private String brandName;

	public BizGoodsSkuExt getBizGoodsSku() {
		return bizGoodsSku;
	}

	public void setBizGoodsSku(BizGoodsSkuExt bizGoodsSku) {
		this.bizGoodsSku = bizGoodsSku;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
	
}