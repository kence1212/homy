package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class PayDto extends Dto {
	/**支付宝-支付*/
	public static final int PAY_TYPE_ALI = 0;
	/**微信-支付*/
	public static final int PAY_TYPE_WX = 1;
	
	private Integer type;//0表示支付宝  1表示微信支付
	private String  orderIds;
	private String  token;
	private Integer userId;
	private String  sign;
	private String  timestamp;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("type", type);
		cd.setValue("orderIds", orderIds);
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}

	public Result validate() {
		Result result = new Result();
		if(type == null ){
			result.wrong("40000" , "支付类型不能为空");
			return result;
		}
		if(StringHelper.isNull(orderIds)){
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
