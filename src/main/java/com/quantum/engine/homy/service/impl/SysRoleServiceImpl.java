package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.SysPermissionModelMapper;
import com.quantum.engine.homy.dao.SysRoleMapper;
import com.quantum.engine.homy.dao.SysRolePermissionMapper;
import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.SysRole;
import com.quantum.engine.homy.model.SysRolePermission;
import com.quantum.engine.homy.model.ext.SysRoleExt;
import com.quantum.engine.homy.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper mapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Override
	public List<SysRole> listAll() {
		return mapper.getAll();
	}
	
	@Override
	public void add(SysRole record, List<Integer> permissions){
		
		//保存角色
		record.setCreateTime(new Date());
		mapper.insert(record);
		
		if(permissions != null){
			
			for (Integer id : permissions) {
				
				//保存角色权限
				SysRolePermission sysRolePermission = new SysRolePermission();
				sysRolePermission.setCreateId(record.getCreateId());
				sysRolePermission.setCreateTime(new Date());
				sysRolePermission.setPermissionId(id);
				sysRolePermission.setRoleId(record.getId());
				sysRolePermissionMapper.insert(sysRolePermission);
				
			}
		}
		
	}
	
	@Override
	public int delete(Integer id){
		SysRole record = mapper.selectByPrimaryKey(id);
		sysRolePermissionMapper.deleteByRoleId(id);
		if(record == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public SysRoleExt getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}



	@Override
	public void update(SysRole record, List<Integer> permissions) {
		
		//删除之前的权限
		sysRolePermissionMapper.deleteByRoleId(record.getId());
		//保存角色权限
		if(permissions != null){
			for (Integer id : permissions) {
				
				SysRolePermission sysRolePermission = new SysRolePermission();
				sysRolePermission.setCreateId(record.getCreateId());
				sysRolePermission.setCreateTime(new Date());
				sysRolePermission.setPermissionId(id);
				sysRolePermission.setRoleId(record.getId());
				sysRolePermissionMapper.insert(sysRolePermission);
				
			}
		}
		
		//更新角色
		SysRole recordInDB = getById(record.getId());
		record.setCreateTime(recordInDB.getCreateTime());
		record.setCreateId(recordInDB.getCreateId());
		record.setModifyTime(new Date());
		
		mapper.updateByPrimaryKeySelective(record);
	}
	

}
