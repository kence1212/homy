package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizGoodsBoutique;
import com.quantum.engine.homy.model.ext.BizGoodsBoutiqueExt;

public interface BizGoodsBoutiqueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsBoutique record);

    int insertSelective(BizGoodsBoutique record);

    BizGoodsBoutique selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsBoutique record);

    int updateByPrimaryKey(BizGoodsBoutique record);

	List<BizGoodsBoutiqueExt> listAll();
	
	List<BizGoodsBoutiqueExt> listByType(Integer type);
}