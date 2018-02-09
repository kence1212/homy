package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.PhoneUtil;
import com.quantum.engine.homy.util.StringHelper;

public class LoginDto extends Dto {
	
	private String phone;
	private String password;
	private String timestamp;//201711061504
	private String sign;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String toString(){
		return "[phone=" + phone + ",password=" + password +  ",timestamp=" +timestamp+",sign=" + sign + "]";
	}
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		
		cd.setValue("phone", phone);
		cd.setValue("password", password);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		
		return cd;
	}
	
	public Result validate(){
		Result result = new Result();
		if(StringHelper.isNull(getPhone())){
			result.wrong("40000" , "电话号码不能为空");
			return result;
		}
		if(!PhoneUtil.checkPhoneNo(getPhone())){
			result.wrong("40000" , "电话号码错误");
			return result;
		}
		if(StringHelper.isNull(getPassword())){
			result.wrong("40000" , "密码不能为空");
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

}
