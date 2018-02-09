package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.List;

public class BizShoppingCartWithSkuGroupByBrandExt implements Serializable{

	private Integer brandId;
	private String brandName;
	private List<BizShoppingCartWithSkuExt> products;
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
	public List<BizShoppingCartWithSkuExt> getProducts() {
		return products;
	}
	public void setProducts(List<BizShoppingCartWithSkuExt> products) {
		this.products = products;
	}
	
}
