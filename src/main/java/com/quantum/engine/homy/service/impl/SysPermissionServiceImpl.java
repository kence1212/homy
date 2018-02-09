package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.SysPermissionMapper;
import com.quantum.engine.homy.dao.SysPermissionModelMapper;
import com.quantum.engine.homy.model.BizRecommendSilder;
import com.quantum.engine.homy.model.SysPermission;
import com.quantum.engine.homy.model.ext.SysPermissionExt;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.SysPermissionService;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	SysPermissionModelMapper sysPermissionModelMapper;
	@Autowired
	SysPermissionMapper sysPermissionMapper;
	@Override
	public List<Map<String, Object>> getTopPermissionModel() {
		
		return sysPermissionModelMapper.getTopPermissionModel();
		
	}

	
	
	@Override
	public BaseResponse update(SysPermission sysPermission) {
		
		//判断数据库中是否已存在
		String permissionName = sysPermission.getPermissionName();
		String url = sysPermission.getPermissionUri();
		Integer modelId = sysPermission.getPermissionModelId();
		SysPermission permissionInDB = sysPermissionMapper.selectByNameAndUrlAndMId(permissionName,url,modelId,sysPermission.getId());
		if(permissionInDB != null){
			return BaseResponse.getWrong("名称、url、权限模块相同的权限已存在");
		}
		
		//更新权限
		sysPermission.setModifyTime(new Date());
		sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
		return BaseResponse.getSuccess("更新成功");
	}

	@Override
	public BaseResponse add(SysPermission sysPermission) {
			//判断数据库中是否已存在
			String permissionName = sysPermission.getPermissionName();
			String url = sysPermission.getPermissionUri();
			Integer modelId = sysPermission.getPermissionModelId();
			SysPermission permissionInDB = sysPermissionMapper.selectByNameAndUrlAndMId(permissionName,url,modelId, null);
			if(permissionInDB != null){
				return BaseResponse.getWrong("不能保存名称、url、权限模块相同的权限");
			}
			
			//保存url权限
			sysPermission.setCreateTime(new Date());
			sysPermissionMapper.insert(sysPermission);
			return BaseResponse.getSuccess("保存成功");
			
	}

	@Override
	public List<Map<String, Object>> getSecondModel(Integer pid) {
		return sysPermissionModelMapper.getSecondModel(pid);
	}

	@Override
	public Page<SysPermissionExt> getList(Integer pageNum, Integer pageSize, String keyword, Integer modelId) {
		
		if("".equals(keyword)){
			keyword = null ;
		}else{
			keyword = "%"+keyword+"%";
		}
		
		Page<SysPermissionExt> page = new Page<>(pageNum, pageSize);
		
		int count = sysPermissionMapper.getTotalCount(keyword, modelId);
		
		page.setTotalCount(count);
		
		if(count == 0){
			page.setList(new ArrayList<SysPermissionExt>());
			return page;
		}
		
		List<SysPermissionExt> list = sysPermissionMapper.getList(pageNum,(pageNum-1)*pageSize,pageSize,keyword, modelId);
		
		page.setList(list);
		
		return page;
		
	}

	@Override
	public int delete(Integer id) {
		SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(id);
		if(sysPermission == null){
			return 0;
		}else{
			sysPermissionMapper.deleteByPrimaryKey(id);
			return 1;
		}
	}

	@Override
	public SysPermission getById(Integer id) {
		return sysPermissionMapper.selectByPrimaryKey(id);
	}

}
