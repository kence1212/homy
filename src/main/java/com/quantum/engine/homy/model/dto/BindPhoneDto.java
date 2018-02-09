package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class BindPhoneDto {
	
	private String phone;
	private String code;
	private Integer otherLoginId;
	private String timestamp;
	private String sign;
	private String loginToken;
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("phone", phone);
		cd.setValue("code", code);
		cd.setValue("otherLoginId", otherLoginId);
		cd.setValue("timestamp", timestamp);
		cd.setValue("loginToken", loginToken);
		cd.setValue("sign", sign);
		return cd;
	}
	public Result validate() {
		Result result = new Result();
		if(StringHelper.isNull(getPhone())){
			result.wrong("40000" , "手机号不能为空");
			return result;
		}
		if(StringHelper.isNull(getCode())){
			result.wrong("40000" , "验证码不能为空");
			return result;
		}
		if(getOtherLoginId() == null){
			result.wrong("40000" , "三方登录Id不能为空");
			return result;
		}
		if(StringHelper.isNull(getTimestamp())){
			result.wrong("40000" , "时间戳不能为空");
			return result;
		}
		if(StringHelper.isNull(getLoginToken())){
			result.wrong("40000" , "loginToken不能为空");
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
	
	
	public Integer getOtherLoginId() {
		return otherLoginId;
	}
	public void setOtherLoginId(Integer otherLoginId) {
		this.otherLoginId = otherLoginId;
	}
	public String getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
