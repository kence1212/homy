package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.dao.UserMapper;
import com.quantum.engine.homy.dao.UserTokenMapper;
import com.quantum.engine.homy.model.BizUserCompany;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.StringHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserTokenMapper userTokenMapper;
	
	@Override
	public User getById(Integer userId){
		return userMapper.getById(userId);
	}
	
	@Override
	public User getByPhone(String phone) {
		return userMapper.getByPhone(phone);
	}
	
	@Override
	public User getByUsername(String username){
		return userMapper.getByUsername(username);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void add(User user) {
		userMapper.insert(user);
	}

	@Override
	public String savePhoneCode(String phone, String code) {
		User user = userMapper.getByPhone(phone);
		if(user == null){
			User createUser = new User();
			createUser.setUserState(ConstantsKey.USER_STATE_INIT);
			createUser.setPhone(phone);
			String phoneCode = code + "-" + System.currentTimeMillis();
			createUser.setPhoneCode(phoneCode);
			userMapper.insert(createUser);
		}else{
			String phoneCode = code + "-" + System.currentTimeMillis();
			user.setPhoneCode(phoneCode);
			userMapper.update(user);
		}
		return code;
	}
	
	@Override
	public String generateToken(Integer userId){
		UserToken userToken = userTokenMapper.getById(userId);
		String token = MD5FileUtil.getMD5String(StringHelper.getRandomStr(60));
		Date now = new Date();
		if(userToken == null){
			userToken = new UserToken();
			userToken.setUserId(userId);
			userToken.setModifyTime(now);
			userToken.setToken(token);
			userTokenMapper.insert(userToken);
		}else{
			userToken.setModifyTime(now);
			userToken.setToken(token);
			userTokenMapper.update(userToken);
		}
		return token;
	}
	
	public List<User> listRegisterUser(Map<String, Object> params){
		params.put("userState", 1);
		return userMapper.list(params);
	}
	
	public int delete(Integer userId){
		User user = userMapper.getById(userId);
		if(user == null){
			return 0;
		}else{
			userMapper.delete(userId);
			return 1;
		}
		
	}
	
	public UserToken getTokenByUserId(Integer userId){
		UserToken userToken = userTokenMapper.getById(userId);
		return userToken;
	}

	@Override
	public UserToken getByTokenAndUserId(String token, Integer userId) {
		
		return userTokenMapper.getByTokenAndUserId(token, userId);
		
	}
	
	@Override
	public User getByToken(String token){
		UserToken userToken = userTokenMapper.getByToken(token);
		if(userToken != null){
			User user = userMapper.getById(userToken.getUserId());
			if(user.getGrade() == ConstantsKey.USER_GRADE_COMPANY){
				user = userMapper.getCompanyByUserId(userToken.getUserId());
			}
			return user;
		}
		return null;
	}
	
	@Override
	public BizUserCompany getCompanyByUserId(Integer userId){
		return userMapper.getCompanyByUserId(userId);
	}
	
	@Override
	public void updateCompany(BizUserCompany company){
		userMapper.update(company);
		BizUserCompany bean = userMapper.getOnlyExtByUserId(company.getUserId());
		if(bean == null){
			userMapper.insertCompany(company);
		}else{
			userMapper.updateCompany(company);
		}
		
	}

}
