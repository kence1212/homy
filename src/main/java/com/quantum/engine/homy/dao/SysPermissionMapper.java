package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.SysPermission;
import com.quantum.engine.homy.model.ext.SysPermissionExt;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

	SysPermission selectByNameAndUrlAndMId(@Param("permissionName")String permissionName, @Param("url")String url, @Param("modelId")Integer modelId, @Param("permissionId")Integer permissionId);

	int getTotalCount(@Param("keyword")String keyword, @Param("modelId")Integer modelId);

	List<SysPermissionExt> getList(@Param("pageNum")Integer pageNum, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword, @Param("modelId")Integer modelId);
	
}