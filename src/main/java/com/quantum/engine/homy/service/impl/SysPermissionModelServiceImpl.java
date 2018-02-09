package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.SysPermissionModelMapper;
import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.ext.SysPermissionExt;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.SysPermissionModelService;

@Service
public class SysPermissionModelServiceImpl implements SysPermissionModelService {
	
	@Autowired
	private SysPermissionModelMapper mapper;

	@Override
	public List<SysPermissionModelExt> listAll() {
		return mapper.getAll();
	}
	
	@Override
	public BaseResponse add(SysPermissionModel permissionModel){
		
		SysPermissionModel sysPermissionModel = mapper.selectByNameAndPid(permissionModel.getName(),permissionModel.getPid(),null);
		if(sysPermissionModel != null ){
			return BaseResponse.getWrong("不能保存父模块和名称相同的模块");
		}
		permissionModel.setCreateTime(new Date());
		mapper.insert(permissionModel);
		return BaseResponse.getSuccess("保存成功");
	}
	
	@Override
	public int delete(Integer id){
		SysPermissionModel permissionModel = mapper.selectByPrimaryKey(id);
		if(permissionModel == null){
			return 0;
		}else{
			int count = mapper.getCountByPid(id);
			if(count == 0){
				mapper.deleteByPrimaryKey(id);
				return 1;
			} else{
				return 2;
			}
		}
	}
	
	@Override
	public SysPermissionModel getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}


	@Override
	public BaseResponse update(SysPermissionModel permissionModel) {
		
		SysPermissionModel sysPermissionModel = mapper.selectByNameAndPid(permissionModel.getName(),permissionModel.getPid(),permissionModel.getId());
		if(sysPermissionModel != null ){
			return BaseResponse.getWrong("已存在父模块和名称相同的模块");
		}
		
		SysPermissionModel permissionModelInDB = getById(permissionModel.getId());
		permissionModel.setCreateTime(permissionModelInDB.getCreateTime());
		permissionModel.setCreateId(permissionModelInDB.getCreateId());
		permissionModel.setModifyTime(new Date());
		
		mapper.updateByPrimaryKey(permissionModel);
		return BaseResponse.getSuccess("修改成功");
	}

	@Override
	public List<Map<String, Object>> getPModels(Integer id) {
		return mapper.getTopPermissionModel();
	}

	@Override
	public List<SysPermissionModelExt> getModelList() {
		return mapper.getModelList();
	}

	@Override
	public Page<SysPermissionModelExt> getList(Integer pageNum, Integer pageSize, String keyword) {
		
		if("".equals(keyword)){
			keyword = null ;
		}else{
			keyword = "%"+keyword+"%";
		}
		
		Page<SysPermissionModelExt> page = new Page<>(pageNum, pageSize);
		
		int count = mapper.getTotalCount(keyword);
		
		page.setTotalCount(count);
		
		if(count == 0){
			page.setList(new ArrayList<SysPermissionModelExt>());
			return page;
		}
		
		List<SysPermissionModelExt> list = mapper.getList(pageNum,(pageNum-1)*pageSize,pageSize,keyword);
		
		page.setList(list);
		
		return page;
		
	}
	

}
