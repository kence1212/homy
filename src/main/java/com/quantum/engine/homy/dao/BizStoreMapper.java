package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizStore;

public interface BizStoreMapper {
	
	List<BizStore> listAll();
	
    int deleteByPrimaryKey(Integer id);

    int insert(BizStore record);

    int insertSelective(BizStore record);

    BizStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizStore record);

    int updateByPrimaryKeyWithBLOBs(BizStore record);

    int updateByPrimaryKey(BizStore record);
}