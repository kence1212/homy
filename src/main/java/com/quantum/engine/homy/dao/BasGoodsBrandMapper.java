package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BasGoodsBrand;

public interface BasGoodsBrandMapper {
	
	List<BasGoodsBrand> getAll();
	
	List<BasGoodsBrand> listIndexHot();
	
    int deleteByPrimaryKey(Integer brandId);

    int insert(BasGoodsBrand record);

    int insertSelective(BasGoodsBrand record);

    BasGoodsBrand selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(BasGoodsBrand record);

    int updateByPrimaryKey(BasGoodsBrand record);
    
    List<BasGoodsBrand> listHave3d(Map<String, Object> params);
}