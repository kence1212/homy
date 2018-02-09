package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.dao.BizSceneInitMapper;
import com.quantum.engine.homy.dao.UserMapper;
import com.quantum.engine.homy.model.BizSceneInit;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.service.BizSceneInitService;

@Service
public class BizSceneInitServiceImpl implements BizSceneInitService {

	@Autowired
	private BizSceneInitMapper mapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public void create(String name, String sceneInfo, Integer userId) {
		
		User userInDB = userMapper.getByUserId(userId);
		if(userInDB == null){
			return ;
		}
		BizSceneInit bizSceneInit = new BizSceneInit();
		bizSceneInit.setCreateTime(new Date());
		bizSceneInit.setIsValid(Constants.VALID);
		bizSceneInit.setName(name);
		bizSceneInit.setSceneInfo(sceneInfo);
		bizSceneInit.setUserId(userId);
		bizSceneInit.setUsername(userInDB.getUsername());
		mapper.insert(bizSceneInit);
		
	}

	@Override
	public List<BizSceneInit> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

}
