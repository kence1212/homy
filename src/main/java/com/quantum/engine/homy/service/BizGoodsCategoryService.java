package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizGoodsCategory;

public interface BizGoodsCategoryService {
	
	public void add(BizGoodsCategory category);
	
	public BizGoodsCategory getById(Integer id);
	
	public void update(BizGoodsCategory category);
	
	public int delete(Integer categoryId);
	
	public List<BizGoodsCategory> listAll();
	
	public List<BizGoodsCategory> listHave3d(Map<String, Object> params);
	
	public List<BizGoodsCategory> getByType(Integer categoryType);
	
	public List<BizGoodsCategory> getByTypeAndBrandId(Integer categoryType,Integer brandId);
	

}
