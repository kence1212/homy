package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizGoodsCategoryMapper;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.service.BizGoodsCategoryService;

@Service("bizGoodsCategoryService")
public class BizGoodsCategoryServiceImpl implements BizGoodsCategoryService {
	
	@Autowired
	private BizGoodsCategoryMapper mapper;
	
	@Override
	public void add(BizGoodsCategory category){
		category.setCreateTime(new Date());
		mapper.insert(category);
	}

	@Override
	public BizGoodsCategory getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(BizGoodsCategory category) {
		if(category != null && category.getId() != null){
			BizGoodsCategory source = mapper.selectByPrimaryKey(category.getId());
			if(source != null){
				source.setModifyId(category.getModifyId());
				source.setModifyTime(new Date());
				source.setCategoryCode(category.getCategoryCode());
				source.setCategoryDesc(category.getCategoryDesc());
				source.setCategoryName(category.getCategoryName());
				source.setIcon(category.getIcon());
				source.setPid(category.getPid());
				source.setAppClientIcon(category.getAppClientIcon());
				source.setTsort(category.getTsort());
				source.setCategoryType(category.getCategoryType());
				mapper.updateByPrimaryKeyWithBLOBs(source);
			}
		}
	}

	@Override
	public int delete(Integer categoryId) {
		BizGoodsCategory bean = mapper.selectByPrimaryKey(categoryId);
		if(bean == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(categoryId);
			return 1;
		}
	}

	@Override
	public List<BizGoodsCategory> listAll() {
		return mapper.getAll();
	}
	
	public List<BizGoodsCategory> listHave3d(Map<String, Object> params){
		return mapper.listHave3dBy(params);
	}
	
	@Override
	public List<BizGoodsCategory> getByType(Integer categoryType){
		return mapper.getByType(categoryType);
	}
	
	@Override
	public List<BizGoodsCategory> getByTypeAndBrandId(Integer categoryType,Integer brandId){
		return mapper.getByTypeAndBrandId(categoryType,brandId);
	}

}
