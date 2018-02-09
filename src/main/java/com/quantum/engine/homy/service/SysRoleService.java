package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.SysRole;
import com.quantum.engine.homy.model.ext.SysRoleExt;

/**
 * 
 * @author fzz
 * @date 2017年12月9日
 * @description
 */
public interface SysRoleService {
	
	public List<SysRole> listAll();
	
	public void add(SysRole record, List<Integer> permissions);
	
	public int delete(Integer id);
	
	public SysRoleExt getById(Integer id);

	public void update(SysRole record, List<Integer> permissions);
	
	
}
