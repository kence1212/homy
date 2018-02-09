package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizRecommendSilderMapper;
import com.quantum.engine.homy.model.BizRecommendSilder;
import com.quantum.engine.homy.model.ext.BizRecommendSilderExt;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsModelService;
import com.quantum.engine.homy.service.RecommendSilderService;

@Service
public class RecommendSilderServiceImpl implements RecommendSilderService {
	
	@Autowired
	private BizRecommendSilderMapper mapper;
	
	@Autowired
	private BizGoodsService bizGoodsService;
	
	@Override
	public void add(BizRecommendSilder recommendSilder){
		recommendSilder.setCreateTime(new Date());
		mapper.insert(recommendSilder);
	}
	
	@Override
	public int delete(Integer id){
		BizRecommendSilder recommendSilder = mapper.selectByPrimaryKey(id);
		if(recommendSilder == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public BizRecommendSilder getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(BizRecommendSilder recommendSilder) {
		BizRecommendSilder recommendSilderInDB = getById(recommendSilder.getId());
		
		recommendSilder.setCreateTime(recommendSilderInDB.getCreateTime());
		recommendSilder.setCreateId(recommendSilderInDB.getCreateId());
		recommendSilder.setModifyTime(new Date());
		
		mapper.updateByPrimaryKey(recommendSilder);
	}

	@Override
	public List<BizRecommendSilderExt> listAllWithGood() {
		return mapper.listAllWithGood();
	}
	

}
