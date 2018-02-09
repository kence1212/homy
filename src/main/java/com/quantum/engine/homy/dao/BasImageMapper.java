package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BasImage;

public interface BasImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasImage record);

    int insertSelective(BasImage record);

    BasImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasImage record);

    int updateByPrimaryKey(BasImage record);
    
    int deleteByGoodsId(Integer goodsId);
    
    List<BasImage> getByGoodsId(Integer goodsId);
}