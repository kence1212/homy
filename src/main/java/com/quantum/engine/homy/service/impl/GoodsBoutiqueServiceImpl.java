package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizGoodsBoutiqueMapper;
import com.quantum.engine.homy.model.BizGoodsBoutique;
import com.quantum.engine.homy.model.ext.BizGoodsBoutiqueExt;
import com.quantum.engine.homy.service.GoodsBoutiqueService;

@Service
public class GoodsBoutiqueServiceImpl implements GoodsBoutiqueService {
	
	@Autowired
	private BizGoodsBoutiqueMapper mapper;
	
	
	@Override
	public void add(BizGoodsBoutique bizGoodsBoutique){
		bizGoodsBoutique.setCreateTime(new Date());
		mapper.insert(bizGoodsBoutique);
	}
	
	@Override
	public int delete(Integer id){
		BizGoodsBoutique bizGoodsBoutique = mapper.selectByPrimaryKey(id);
		if(bizGoodsBoutique == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public BizGoodsBoutique getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(BizGoodsBoutique bizGoodsBoutique) {
		BizGoodsBoutique bizGoodsBoutiqueInDB = getById(bizGoodsBoutique.getId());
		
		bizGoodsBoutique.setCreateTime(bizGoodsBoutiqueInDB.getCreateTime());
		bizGoodsBoutique.setCreateId(bizGoodsBoutiqueInDB.getCreateId());
		bizGoodsBoutique.setModifyTime(new Date());
		
		mapper.updateByPrimaryKey(bizGoodsBoutique);
	}

	@Override
	public List<BizGoodsBoutiqueExt> listAll() {
		return mapper.listAll();
	}
	
	@Override
	public List<BizGoodsBoutiqueExt> listByType(Integer type){
		return mapper.listByType(type);
	}

}
