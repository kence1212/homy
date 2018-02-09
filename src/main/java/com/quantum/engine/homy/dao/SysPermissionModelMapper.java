package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;

public interface SysPermissionModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermissionModel record);

    int insertSelective(SysPermissionModel record);

    SysPermissionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermissionModel record);

    int updateByPrimaryKey(SysPermissionModel record);

	List<Map<String, Object>> getTopPermissionModel();

	SysPermissionModel selectByName(String modelName);

	List<Map<String, Object>> getSecondModel(Integer pid);

	List<SysPermissionModelExt> getAll();

	List<Map<String, Object>> getPModels(@Param("id")Integer id);

	int getCountByPid(Integer id);

	SysPermissionModel selectByNameAndPid(@Param("name")String name, @Param("pid")Integer pid,  @Param("id")Integer id);

	
	List<SysPermissionModelExt> getModelList();

	int getTotalCount(@Param("keyword")String keyword);

	List<SysPermissionModelExt> getList(@Param("pageNum")Integer pageNum, @Param("startIndex")int startIndex, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword);
}