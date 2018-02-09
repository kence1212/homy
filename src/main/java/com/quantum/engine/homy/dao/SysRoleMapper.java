package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.SysRole;
import com.quantum.engine.homy.model.ext.SysRoleExt;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRoleExt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	List<SysRole> getAll();
}