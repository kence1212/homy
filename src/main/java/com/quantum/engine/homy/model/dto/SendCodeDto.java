package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.PhoneUtil;
import com.quantum.engine.homy.util.StringHelper;

public class SendCodeDto extends Dto {
	
	private String phone;
	private String timestamp;//201711061504
	private String sign;
	private Integer type;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "[phone=" + phone +  ",timestamp=" +timestamp+",sign=" + sign + "]";
	}
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		
		cd.setValue("phone", phone);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		if(type != null){
			cd.setValue("type", type);
		}
		return cd;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Result validate(){
		Result result = new Result();
		if(StringHelper.isNull(this.getPhone())){
			result.wrong("40000", "电话号码不能为空");
			return result;
		}
		if(!PhoneUtil.checkPhoneNo(this.getPhone())){
			result.wrong("40000", "电话号码错误");
			return result;
		}
		if(StringHelper.isNull(this.getSign())){
			result.wrong("40000", "签名不能为空");
			return result;
		}
		String sign = this.toData().makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000", "签名错误");
			return result;
		}
		return result;
	}
	
	public static void main(String[] args) {
		ClientData cd = new ClientData();
		
		cd.setValue("timestamp", "201711071644");
		cd.setValue("token", "3a56e05cadd42c40b32bd3fd466cb9bb");
		
		System.out.println(cd.makeSign());
	}
	
}
