package com.quantum.engine.homy.model.result;

import java.util.List;

public class ShoppingCartBrandResult {
	
	private Integer brandId;
	private String  brandName;
	
	private List<ShoppingCartBrandItem> items;
	private Double brandTotalPrice;

	public Double getBrandTotalPrice() {
		return brandTotalPrice;
	}

	public void setBrandTotalPrice(Double brandTotalPrice) {
		this.brandTotalPrice = brandTotalPrice;
	}

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

	public List<ShoppingCartBrandItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartBrandItem> items) {
		this.items = items;
	}
	

}
