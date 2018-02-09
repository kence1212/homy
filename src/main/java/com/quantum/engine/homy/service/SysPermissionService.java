package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.SysPermission;
import com.quantum.engine.homy.model.ext.SysPermissionExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

public interface SysPermissionService {

	List<Map<String, Object>> getTopPermissionModel();

	BaseResponse add(SysPermission sysPermission);

	List<Map<String, Object>> getSecondModel(Integer pid);

	Page<SysPermissionExt> getList(Integer pageNum, Integer pageSize, String keyword, Integer modelId);

	int delete(Integer id);

	SysPermission getById(Integer id);

	BaseResponse update(SysPermission sysPermission);

}
