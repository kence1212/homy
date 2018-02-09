package com.quantum.engine.homy.model.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.BizGoodsStyle;

public class CategoryAppResult {
	
	private Integer id;
	private String  name;
	private String  code;
	private String  icon;
	
	private List<CategoryAppResult> subCategoryList = new ArrayList<CategoryAppResult>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public List<CategoryAppResult> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(List<CategoryAppResult> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	public static List<CategoryAppResult> styleListTrans(List<BizGoodsStyle> styleList,String baseUrl){
		List<CategoryAppResult>  resultList = new ArrayList<CategoryAppResult>();
		if(styleList != null  && styleList.size() > 0){
			for(BizGoodsStyle style:styleList){
				CategoryAppResult cart = new CategoryAppResult();
				cart.setCode(style.getStyleCode());
				cart.setIcon(baseUrl + style.getAppClientIcon());
				cart.setId(style.getId());
				cart.setName(style.getStyleName());
				resultList.add(cart);
			}
		}
		return resultList;
	}
	
	public static List<CategoryAppResult> brandListTrans(List<BasGoodsBrand> brandList,String baseUrl){
		List<CategoryAppResult>  resultList = new ArrayList<CategoryAppResult>();
		if(brandList != null  && brandList.size() > 0){
			for(BasGoodsBrand brand:brandList){
				CategoryAppResult cart = new CategoryAppResult();
				cart.setCode(brand.getBrandCode());
				cart.setIcon(baseUrl + brand.getAppClientIcon());
				cart.setId(brand.getBrandId());
				cart.setName(brand.getBrandName());
				resultList.add(cart);
			}
		}
		return resultList;
	}
	
	public static List<CategoryAppResult> treeCategoryTrans(List<BizGoodsCategory> categoryList,String baseUrl){
		Map<Integer, CategoryAppResult> maps = new HashMap<Integer, CategoryAppResult>();
		if(categoryList != null && categoryList.size() > 0){
			for(BizGoodsCategory category:categoryList){
				if(category.getPid() == null ||  category.getPid() == 0){
					CategoryAppResult cart = new CategoryAppResult();
					cart.setCode(category.getCategoryCode());
					cart.setIcon(baseUrl + category.getAppClientIcon());
					cart.setId(category.getId());
					cart.setName(category.getCategoryName());
					maps.put(category.getId(), cart);
				}
			}
			for(BizGoodsCategory category:categoryList){
				if(category.getPid() != null &&  category.getPid() != 0){
					CategoryAppResult pacart = maps.get(category.getPid());
					if(pacart != null){
						CategoryAppResult cart = new CategoryAppResult();
						cart.setCode(category.getCategoryCode());
						cart.setIcon(baseUrl + category.getAppClientIcon());
						cart.setId(category.getId());
						cart.setName(category.getCategoryName());
						pacart.getSubCategoryList().add(cart);
					}
				}
			}
		}
		return new ArrayList<CategoryAppResult>(maps.values());
	}
}
