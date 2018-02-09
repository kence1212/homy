package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsTexture;

public interface BizGoodsTextureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsTexture record);

    int insertSelective(BizGoodsTexture record);

    BizGoodsTexture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsTexture record);

    int updateByPrimaryKey(BizGoodsTexture record);
    
    int deleteByGoodsId(Integer goodsId);

	List<Map<String, Object>> getTextureById(Integer goodsId);
	
	List<String> getNames(Map<String, Object> params);
	
	BizGoodsTexture getByGoodsIdAndName(@Param("goodsId")Integer goodsId,@Param("name")String name);
}