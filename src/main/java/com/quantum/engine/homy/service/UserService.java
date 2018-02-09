package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizUserCompany;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.UserToken;

public interface UserService {
	
	public User getById(Integer userId);
	
	public User getByPhone(String phone);
	
	public User getByUsername(String username);
	
	public void update(User user);
	
	public void add(User user);
	
	public String savePhoneCode(String phone,String code);
	
	public String generateToken(Integer userId);
	
	public UserToken getTokenByUserId(Integer userId);
	
	public List<User> listRegisterUser(Map<String, Object> params);
	
	public int delete(Integer userId);

	public UserToken getByTokenAndUserId(String token, Integer userId);
	
	public User getByToken(String token);
	
	public BizUserCompany getCompanyByUserId(Integer userId);
	
	public void updateCompany(BizUserCompany company);

}
