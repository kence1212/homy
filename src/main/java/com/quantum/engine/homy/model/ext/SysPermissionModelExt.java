package com.quantum.engine.homy.model.ext;

import java.util.List;

import com.quantum.engine.homy.model.SysPermissionModel;

public class SysPermissionModelExt extends SysPermissionModel{

	private static final long serialVersionUID = 1L;
	private String parentName;
	
	private List<SysPermissionModelExt> childrenModels;

	private List<SysPermissionExt> modelPermissions;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<SysPermissionModelExt> getChildrenModels() {
		return childrenModels;
	}

	public void setChildrenModels(List<SysPermissionModelExt> childrenModels) {
		this.childrenModels = childrenModels;
	}

	public List<SysPermissionExt> getModelPermissions() {
		return modelPermissions;
	}

	public void setModelPermissions(List<SysPermissionExt> modelPermissions) {
		this.modelPermissions = modelPermissions;
	}
	
	
	
	
	
	
}
