package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizRecommendSilder;
import com.quantum.engine.homy.model.ext.BizRecommendSilderExt;

public interface BizRecommendSilderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizRecommendSilder record);

    int insertSelective(BizRecommendSilder record);

    BizRecommendSilder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizRecommendSilder record);

    int updateByPrimaryKey(BizRecommendSilder record);

	List<BizRecommendSilderExt> listAllWithGood();
}