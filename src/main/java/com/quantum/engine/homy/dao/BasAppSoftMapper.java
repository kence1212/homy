package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BasAppSoft;

public interface BasAppSoftMapper {
	
	BasAppSoft selectMaxVersion(BasAppSoft record);
	
	List<BasAppSoft> getAll();
	
    int deleteByPrimaryKey(Integer id);

    int insert(BasAppSoft record);

    int insertSelective(BasAppSoft record);

    BasAppSoft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasAppSoft record);

    int updateByPrimaryKeyWithBLOBs(BasAppSoft record);

    int updateByPrimaryKey(BasAppSoft record);

	BasAppSoft selectByCodeAndVersionAndType(@Param("appCode")String appCode, @Param("appVersion")Integer appVersion, @Param("appType")Integer appType);
}