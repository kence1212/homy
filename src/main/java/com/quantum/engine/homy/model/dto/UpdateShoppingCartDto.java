package com.quantum.engine.homy.model.dto;

import java.util.List;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class UpdateShoppingCartDto {

	private String token;
	private String timestamp;
	private String sign;
	private Integer userId;
	private Integer id;
	private Integer num;
	
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("id", id);
		cd.setValue("num", num);
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}

	public Result validate() {
		Result result = new Result();
		if(id == null){
			result.wrong("40000" , "ID不能为空");
			return result;
		}
		if(num == null){
			result.wrong("40000" , "数量不能为空");
			return result;
		}
		if(getUserId() == null){
			result.wrong("40000" , "用户ID不能为空");
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
	
	
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	
	
}
