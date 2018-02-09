package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizSceneInit;

public interface BizSceneInitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizSceneInit record);

    int insertSelective(BizSceneInit record);

    BizSceneInit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizSceneInit record);

    int updateByPrimaryKeyWithBLOBs(BizSceneInit record);

    int updateByPrimaryKey(BizSceneInit record);

	List<BizSceneInit> getList();
}