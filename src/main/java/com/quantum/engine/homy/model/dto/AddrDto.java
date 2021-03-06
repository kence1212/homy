package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class AddrDto extends Dto {

	private String token;
	private String timestamp;//201711061504
	private String sign;
	private Integer userId;
	private Integer addrId;
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		cd.setValue("userId", userId);
		cd.setValue("addrId", addrId);
		return cd;
	}	    
    
    
public Result validate(){
	Result result = new Result();
	if(userId == null || userId <= 0){
		result.wrong("40000" , "用户id不能为空");
		return result;
	}
	
	if(addrId == null || addrId <= 0){
		result.wrong("40000" , "地址id不能为空");
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


	public Integer getAddrId() {
		return addrId;
	}


	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	

}
