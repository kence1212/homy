package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;

public class GetModelDto extends GetDto {
	
	private Integer brandId;
	private String  key;
	private Integer categoryId;
	private Integer styleId;
	private Integer page;
	private Integer size;
	private Integer modelId;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("token", this.getToken());
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
		if(key != null){
			cd.setValue("key", key);
		}
		if(brandId != null){
			cd.setValue("brandId", this.getBrandId());
		}
		if(styleId != null){
			cd.setValue("styleId", this.getStyleId());
		}
		if(categoryId != null ){
			cd.setValue("categoryId", this.getCategoryId());
		}
		if(page != null){
			cd.setValue("page", this.getPage());
		}
		if(size != null){
			cd.setValue("size", this.getSize());
		}
		if(modelId != null){
			cd.setValue("modelId", this.getModelId());
		}
		return cd;
	}

}
