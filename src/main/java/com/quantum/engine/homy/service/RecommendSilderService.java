package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizRecommendSilder;
import com.quantum.engine.homy.model.ext.BizRecommendSilderExt;

/**
 * 
 * @author fzz
 * @date 2017年11月24日
 * @description
 */
public interface RecommendSilderService {
	
	
	public void add(BizRecommendSilder goodsModel);
	
	public int delete(Integer id);
	
	public BizRecommendSilder getById(Integer id);

	public void update(BizRecommendSilder goodsModel);

	public List<BizRecommendSilderExt> listAllWithGood();
	
	
}
