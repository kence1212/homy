package com.quantum.engine.homy.model.dto;

import java.util.List;

import com.quantum.engine.homy.model.SysRole;

public class SysRoleDto extends SysRole{
	
	private static final long serialVersionUID = 1L;
	private List<Integer> permissions;
	public List<Integer> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Integer> permissions) {
		this.permissions = permissions;
	}
	
	
}
