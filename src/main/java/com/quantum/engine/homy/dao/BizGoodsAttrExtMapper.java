package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizGoodsAttrExt;
import com.quantum.engine.homy.model.result.Option;

public interface BizGoodsAttrExtMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByGoodsId(Integer id);

    int insert(BizGoodsAttrExt record);

    int insertSelective(BizGoodsAttrExt record);

    BizGoodsAttrExt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsAttrExt record);

    int updateByPrimaryKey(BizGoodsAttrExt record);
    
    public List<Option> getAttrsByGoodsId(Integer goodsId);
}