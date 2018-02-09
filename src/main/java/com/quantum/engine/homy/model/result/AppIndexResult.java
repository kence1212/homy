package com.quantum.engine.homy.model.result;

import java.util.List;

/**
 * 
 * @author nicya
 * @date 2017年12月1日 下午2:44:54
 */
public class AppIndexResult {
	
	private String resultCode;
	private String msg;
	private String errorMsg;
	
	private List<AppIndexSliderImgResult> banner;
	private List<AppIndexBrandResult> brands;
	private List<GoodsAppShowResult> week;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<AppIndexSliderImgResult> getBanner() {
		return banner;
	}
	public void setBanner(List<AppIndexSliderImgResult> banner) {
		this.banner = banner;
	}
	public List<AppIndexBrandResult> getBrands() {
		return brands;
	}
	public void setBrands(List<AppIndexBrandResult> brands) {
		this.brands = brands;
	}
	public List<GoodsAppShowResult> getWeek() {
		return week;
	}
	public void setWeek(List<GoodsAppShowResult> week) {
		this.week = week;
	}
}
