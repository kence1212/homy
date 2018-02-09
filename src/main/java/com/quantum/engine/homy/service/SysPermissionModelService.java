package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

/**
 * 
 * @author fzz
 * @date 2017年12月8日
 * @description
 */
public interface SysPermissionModelService {
	
	public List<SysPermissionModelExt> listAll();
	
	public BaseResponse add(SysPermissionModel permissionModel);
	
	public int delete(Integer id);
	
	public SysPermissionModel getById(Integer id);

	public BaseResponse update(SysPermissionModel permissionModel);

	public List<Map<String, Object>> getPModels(Integer id);
	
	List<SysPermissionModelExt> getModelList();

	public Page<SysPermissionModelExt> getList(Integer pageNum, Integer pageSize, String keyword);
}
