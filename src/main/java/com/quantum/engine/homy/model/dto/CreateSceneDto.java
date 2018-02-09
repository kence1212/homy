package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class CreateSceneDto extends Dto {
	
	private String name;
	private String sceneInfo;
	private Integer userId;
	private String token;
	private String timestamp;//201711061504
	private String sign;

	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("name", name);
		cd.setValue("sceneInfo", sceneInfo);
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		if(userId == null){
			result.wrong("40000" , "userId不能为空");
			return result;
		}
		if(StringHelper.isNull(getName())){
			result.wrong("40000" , "场景名称不能为空");
			return result;
		}
		if(StringHelper.isNull(getSceneInfo())){
			result.wrong("40000" , "场景配置信息不能为空");
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
		String sign = this.toData()
				.makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSceneInfo() {
		return sceneInfo;
	}

	public void setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
}
