package com.quantum.engine.homy.model.ext;

import java.util.List;

import com.quantum.engine.homy.model.SysRole;

public class SysRoleExt extends SysRole{

	private static final long serialVersionUID = 1L;
	private List<Integer> permissions;

	public List<Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Integer> permissions) {
		this.permissions = permissions;
	}
	
	
}
