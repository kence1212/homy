package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class BizModelShoppingCartDto {

	private String modelIds;  // id用逗号隔开
	
	private Integer userId;

	private String token; 
	
	private String timestamp;//201711061504
	
	private String sign;

	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("modelIds", getModelIds());
		cd.setValue("userId", getUserId());
		cd.setValue("token", getToken());
		cd.setValue("timestamp", getTimestamp());
		cd.setValue("sign", getSign());
		return cd;
	}
	
	public Result validate() {
		Result result = new Result();
		if(getUserId() == null){
			result.wrong("40000" , "用户ID不能为空");
			return result;
		}
		if(StringHelper.isNull(getModelIds()) || isNotNumArray(getModelIds())){
			result.wrong("40000" , "模型id不能为空");
			return result;
		}
		if(StringHelper.isNull(getToken())){
			result.wrong("40000" , "token不能为空");
			return result;
		}
		if(StringHelper.isNull(getTimestamp())){
			result.wrong("40000" , "时间戳不能为空");
			return result;
		}
		if(StringHelper.isNull(getSign())){
			result.wrong("40000" , "签名不能为空");
			return result;
		}
		String sign = this.toData().makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}
	
	public String getModelIds() {
		return modelIds;
	}

	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public  boolean isNotNumArray(String s){
		
		boolean result = true;
		String[] split = s.split(",");
		for (String string : split) {
			try{
				Integer.parseInt(string);
			}catch(Exception e){
				result = false;
			}
		}
		return !result;
	}
	
}
