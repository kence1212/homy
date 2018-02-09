package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class OrderRefundInfoDto extends Dto{
	
	private Integer orderId;
	private String  token;
	private Integer userId;
	private String  sign;
	private String  timestamp;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("orderId", orderId);
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}
	
	@Override
	public Result validate() {
		Result result = new Result();
		if(orderId == null){
			result.wrong("40000" , "订单不能为空");
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
	
	

}
