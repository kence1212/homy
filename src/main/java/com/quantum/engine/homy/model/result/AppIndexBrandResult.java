package com.quantum.engine.homy.model.result;

public class AppIndexBrandResult {
	
	private String  imageUrl;
	private Integer type;
	private String  target;
	private Integer brandId;
	private String  brandName;
	
	private String  appClientIcon;
	private String  brandDesc;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public String getAppClientIcon() {
		return appClientIcon;
	}
	public void setAppClientIcon(String appClientIcon) {
		this.appClientIcon = appClientIcon;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
}
