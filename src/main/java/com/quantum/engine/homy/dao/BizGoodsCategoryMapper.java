package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsCategory;

public interface BizGoodsCategoryMapper {
	
	List<BizGoodsCategory> getAll();
	
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsCategory record);

    int insertSelective(BizGoodsCategory record);

    BizGoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsCategory record);

    int updateByPrimaryKeyWithBLOBs(BizGoodsCategory record);

    int updateByPrimaryKey(BizGoodsCategory record);
    
    List<BizGoodsCategory> listHave3dBy(Map<String, Object> params);
    
    List<BizGoodsCategory> getByType(Integer categoryType);
    
    List<BizGoodsCategory> getByTypeAndBrandId(@Param("categoryType")Integer categoryType,@Param("brandId")Integer brandId);
}