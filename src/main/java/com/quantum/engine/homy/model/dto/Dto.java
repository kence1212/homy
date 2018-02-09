package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public abstract class Dto {
	
	private static final int tokenValidDay = 14;
	
	public abstract Result validate();
	
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
	
	/**
	 * 验证TOKEN是否超时
	 * @return
	 */
	public boolean checkLimitTime(UserToken userToken){
		if(userToken == null || userToken.getModifyTime() == null){
			return false;
		}
		long modifyTime = userToken.getModifyTime().getTime();
		if((System.currentTimeMillis() - modifyTime) > 1000 * 60 * 60 * 24 * tokenValidDay){
			return false ;
		}
		return true;
	}

}
