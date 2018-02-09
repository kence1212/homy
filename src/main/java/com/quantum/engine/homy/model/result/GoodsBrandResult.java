package com.quantum.engine.homy.model.result;

import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.util.StringHelper;

public class GoodsBrandResult {
	
	private Integer brandId;
    private String brandName;
    private String brandCode;
    private String brandDesc;
    private String icon;
    private String appClientIcon;
    private Integer tsort;
    
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
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getTsort() {
		return tsort;
	}
	public void setTsort(Integer tsort) {
		this.tsort = tsort;
	}
	
	public String getAppClientIcon() {
		return appClientIcon;
	}
	public void setAppClientIcon(String appClientIcon) {
		this.appClientIcon = appClientIcon;
	}
	public static GoodsBrandResult init(BasGoodsBrand gb,String path){
		GoodsBrandResult result = new GoodsBrandResult();
		if(gb != null){
			result.setBrandCode(gb.getBrandCode());
			result.setBrandDesc(gb.getBrandDesc());
			result.setBrandId(gb.getBrandId());
			result.setBrandName(gb.getBrandName());
			if(StringHelper.isNotNull(gb.getIcon()) && !gb.getIcon().startsWith("http")){
				result.setIcon(path + gb.getIcon());
			}
			if(StringHelper.isNotNull(gb.getAppClientIcon()) && !gb.getAppClientIcon().startsWith("http")){
				result.setAppClientIcon(path + gb.getAppClientIcon());
			}
			result.setTsort(gb.getTsort());
		}
		return result;
	}
	
}
