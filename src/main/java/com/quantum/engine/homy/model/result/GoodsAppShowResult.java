package com.quantum.engine.homy.model.result;

import java.io.Serializable;

public class GoodsAppShowResult  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String  name;
	private String  imageUrl;
	private String  description;
	private Double  minPrice;
	private Double  maxPrice;
	
	private Double  originalPrice;
	private String priceRegion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPriceRegion() {
		return priceRegion;
	}
	public void setPriceRegion(String priceRegion) {
		this.priceRegion = priceRegion;
	}
	
	
}
