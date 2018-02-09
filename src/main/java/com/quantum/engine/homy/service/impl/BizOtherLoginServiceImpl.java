package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.dao.BizOtherLoginMapper;
import com.quantum.engine.homy.dao.UserMapper;
import com.quantum.engine.homy.model.BizOtherLogin;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.service.BizOtherLoginService;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.StringHelper;

@Service
public class BizOtherLoginServiceImpl implements BizOtherLoginService {

	@Autowired
	BizOtherLoginMapper bizOtherLoginMapper;
	@Autowired
	UserService userService;
	@Autowired
	UserMapper userMapper;
	@Override
	public Map<String,Object> login(String openid, String unionid, int type, String portrait, String nickname) {
		
		Map<String, Object> map = new HashMap<>();
		
		BizOtherLogin bizOtherLoginInDB = bizOtherLoginMapper.getByOpenId(openid);
		
		//有登录记录
		if(bizOtherLoginInDB != null){
			
			Integer userId = bizOtherLoginInDB.getUserId();
			//1、绑定了用户：返回登录成功
			if(userId != null){
				String generateToken = userService.generateToken(userId);
				map.put("token", generateToken);
				map.put("userId", userId);
				return map;
			} 
			
			//2、未绑定用户：返回loginToken
			String loginToken = MD5FileUtil.getMD5String(StringHelper.getRandomStr(60));
			bizOtherLoginInDB.setLoginToken(loginToken);
			bizOtherLoginInDB.setCreateTokenTime(new Date());
			bizOtherLoginMapper.updateByPrimaryKeySelective(bizOtherLoginInDB);
			map.put("loginToken", loginToken);
			map.put("otherLoginId", bizOtherLoginInDB.getId());
			return map;
			
			
		} 
			
		//没有登录记录：生成记录返回loginToken
		BizOtherLogin bizOtherLogin = new BizOtherLogin();
		bizOtherLogin.setCreateTime(new Date());
		String loginToken = MD5FileUtil.getMD5String(StringHelper.getRandomStr(60));
		bizOtherLogin.setLoginToken(loginToken);
		bizOtherLogin.setCreateTokenTime(new Date());
		bizOtherLogin.setNickname(nickname);
		bizOtherLogin.setOpenid(openid);
		bizOtherLogin.setPortrait(portrait);
		bizOtherLogin.setType(type);
		bizOtherLogin.setUnionid(unionid);
		bizOtherLoginMapper.insert(bizOtherLogin);
		map.put("loginToken", loginToken);
		map.put("otherLoginId", bizOtherLogin.getId());
		
		return map;
	}

	@Override
	public Map<String,Object> bindPhone(String phone, String code, String loginToken,Integer otherLoginId, String ip) {
		Map<String, Object> map = new HashMap<>();
		User userInDB = userService.getByPhone(phone);
		if(userInDB == null){
			//验证码错误
			map.put("errorMsg", "验证码错误");
			return map;
		}
		boolean checkCode = checkPhoneCode(userInDB.getPhoneCode(), code);
		if(!checkCode){
			//验证码错误
			map.put("errorMsg", "验证码错误");
			return map;
		}
		
		if(userInDB.getUserState() == ConstantsKey.USER_STATE_REGISTER){
			//验证userId和type是否唯一
			BizOtherLogin bizOtherLoginInDB = bizOtherLoginMapper.selectByPrimaryKey(otherLoginId);
			Integer type = bizOtherLoginInDB.getType();
			BizOtherLogin validate = bizOtherLoginMapper.getByTypeAndUserId(type,userInDB.getUserId());
			if(validate != null){
				String typeStr = "";
				if(type == 0){
					typeStr = "微信";
				}else if(type == 1){
					typeStr = "QQ";
				}
				map.put("errorMsg", "该手机号已经绑定了"+typeStr);
				return map;
			}
			
			//用户已注册:更新三方登录记录
			int updatecount = bizOtherLoginMapper.updateUserId(userInDB.getUserId(),loginToken,otherLoginId);
			if(updatecount == 0){
				//没有记录被更新  loginToken失效
				map.put("errorMsg", "loginToken失效");
				return map;
			}
			
		}
		if(userInDB.getUserState() == ConstantsKey.USER_STATE_INIT){
			//用户未注册(不用验证userId和type是否唯一)
			//1、更新第三方登录记录
			int updatecount = bizOtherLoginMapper.updateUserId(userInDB.getUserId(),loginToken,otherLoginId);
			if(updatecount == 0){
				//没有记录被更新  loginToken失效
				map.put("errorMsg", "loginToken失效");
				return map;
			}
			//2、查询出第三方登录记录把用户记录更新字段
			BizOtherLogin bizOtherLoginInDB = bizOtherLoginMapper.selectByPrimaryKey(otherLoginId);
			userInDB.setUserState(ConstantsKey.USER_STATE_REGISTER);
			String salt = StringHelper.getRandomStr(6);
			userInDB.setSalt(salt);
			userInDB.setPortrait(bizOtherLoginInDB.getPortrait());
			userInDB.setNickname(bizOtherLoginInDB.getNickname());
			userInDB.setRegisterTime(new Date());
			userInDB.setGrade(1);
			userInDB.setRegisterIp(ip);
			userInDB.setPhone("");
			
			userService.update(userInDB);
		}
		
		map.put("userId", userInDB.getUserId());
		return map;
	}
	
	public boolean checkPhoneCode(String phoneCode,String code){
		if(StringHelper.isNull(phoneCode) || StringHelper.isNull(code)){
			return false;
		}
		String[] arr = phoneCode.split("-");
		if(arr.length != 2){
			return false;
		}
		if(!code.equals(arr[0])){
			return false;
		}
		long time = 0;
		try{
			time = Long.parseLong(arr[1]);
		}catch(Exception e){
			return false;
		}
		long now = System.currentTimeMillis();
		//三十分钟后验证码超市
		if((now - time ) > 1000 * 60 * 30){
			return false;
		}
		return true;
	}

}
