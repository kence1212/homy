package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizGoodsBoutique;
import com.quantum.engine.homy.model.ext.BizGoodsBoutiqueExt;

/**
 * 
 * @author fzz
 * @date 2017年11月24日
 * @description
 */
public interface GoodsBoutiqueService {
	
	
	public void add(BizGoodsBoutique goodsModel);
	
	public int delete(Integer id);
	
	public BizGoodsBoutique getById(Integer id);

	public void update(BizGoodsBoutique goodsModel);

	public List<BizGoodsBoutiqueExt> listAll();
	
	public List<BizGoodsBoutiqueExt> listByType(Integer type);
	
	
}
