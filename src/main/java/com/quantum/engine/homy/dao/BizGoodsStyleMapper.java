package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizGoodsStyle;

public interface BizGoodsStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsStyle record);

    int insertSelective(BizGoodsStyle record);

    BizGoodsStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsStyle record);

    int updateByPrimaryKeyWithBLOBs(BizGoodsStyle record);

    int updateByPrimaryKey(BizGoodsStyle record);
    
    List<BizGoodsStyle> getAll();

	List<Map<String, Object>> getOption();
}