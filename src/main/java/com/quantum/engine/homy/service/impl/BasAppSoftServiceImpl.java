package com.quantum.engine.homy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BasAppSoftMapper;
import com.quantum.engine.homy.model.BasAppSoft;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BasAppSoftService;

@Service("basAppSoftService")
public class BasAppSoftServiceImpl implements BasAppSoftService {
	
	@Autowired
	private BasAppSoftMapper mapper;

	@Override
	public BasAppSoft getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BasAppSoft> getAll() {
		return mapper.getAll();
	}
	
	@Override
	public Json add(BasAppSoft basAppSoft){
		
		Json json = new Json();
		BasAppSoft selectByCodeAndVersionAndType = mapper.selectByCodeAndVersionAndType(basAppSoft.getAppCode(),basAppSoft.getAppVersion(),basAppSoft.getAppType());
		if(selectByCodeAndVersionAndType != null){
			json.setMsg("无法重复保存包名，版本号，类型相同的记录");
			json.setSuccess(false);
			return json;
		} 
		
		mapper.insert(basAppSoft);
		json.setMsg("保存成功");
		json.setSuccess(true);
		return json;
	}
	
	@Override
	public int delete(Integer id){
		BasAppSoft bean = mapper.selectByPrimaryKey(id);
		if(bean == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public BasAppSoft selectMaxVersion(BasAppSoft basAppSoft){
		return mapper.selectMaxVersion(basAppSoft);
	}

	@Override
	public Json update(BasAppSoft soft) {
		Json json = new Json();
		
		BasAppSoft selectByCodeAndVersionAndType = mapper.selectByCodeAndVersionAndType(soft.getAppCode(),soft.getAppVersion(),soft.getAppType());
		if(selectByCodeAndVersionAndType != null && !selectByCodeAndVersionAndType.getId().equals(soft.getId())){
			json.setMsg("无法重复保存包名，版本号，类型相同的记录");
			json.setSuccess(false);
			return json;
		} 
		
		mapper.updateByPrimaryKeySelective(soft);
		json.setMsg("保存成功");
		json.setSuccess(true);
		return json;
	}

}
