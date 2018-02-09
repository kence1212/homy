package com.quantum.engine.homy.model.result;

import com.quantum.engine.homy.model.BizGoodsCategory;

public class GoodsCategoryResult {
	
	private Integer goodsCategoryId;
	private String  goodsCategoryName;
	private String  icon;
	
	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}
	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public static GoodsCategoryResult init(BizGoodsCategory bizGoodsCategory,String path){
		GoodsCategoryResult result = new GoodsCategoryResult();
		if(bizGoodsCategory != null){
			result.setGoodsCategoryId(bizGoodsCategory.getId());
			result.setGoodsCategoryName(bizGoodsCategory.getCategoryName());
			result.setIcon(path + bizGoodsCategory.getIcon());
		}
		return result;
	}
	
}
