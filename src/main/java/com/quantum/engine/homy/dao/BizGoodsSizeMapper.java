package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsSize;
import com.quantum.engine.homy.model.BizGoodsTexture;

public interface BizGoodsSizeMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByGoodsId(Integer goodsId);

    int insert(BizGoodsSize record);

    int insertSelective(BizGoodsSize record);

    BizGoodsSize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsSize record);

    int updateByPrimaryKey(BizGoodsSize record);

	List<Map<String, Object>> getSizeById(Integer goodsId);
	
	BizGoodsSize getByGoodsIdAndName(@Param("goodsId")Integer goodsId,@Param("name")String name);
}