package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsColor;

public interface BizGoodsColorMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByGoodsId(Integer goodsId);

    int insert(BizGoodsColor record);

    int insertSelective(BizGoodsColor record);

    BizGoodsColor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsColor record);

    int updateByPrimaryKey(BizGoodsColor record);

	List<Map<String, Object>> getColorById(Integer goodsId);
	
	BizGoodsColor getByGoodsIdAndName(@Param("name")String name,@Param("goodsId") Integer goodsId);

}