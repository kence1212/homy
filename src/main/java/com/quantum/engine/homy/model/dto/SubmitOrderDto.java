package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class SubmitOrderDto {

	private Integer cityId;
	private Integer userId;
	private String cartIds;
	private Integer addressId;
	private String remark;
	private String token;
	private String timestamp;
	private String sign;

	public ClientData toData() {
		ClientData cd = new ClientData();
		cd.setValue("cityId", getCityId());
		cd.setValue("userId", getUserId());
		cd.setValue("cartIds", getCartIds());
		cd.setValue("addressId", getAddressId());
		if (getRemark() != null) {
			cd.setValue("remark", getRemark());
		}
		cd.setValue("token", getToken());
		cd.setValue("timestamp", getTimestamp());
		cd.setValue("sign", getSign());
		return cd;
	}

	public Result validate() {
		Result result = new Result();
		if(getCityId() == null){
			result.wrong("40000", "城市ID不能为空");
			return result;
		}
		if (getUserId() == null) {
			result.wrong("40000", "用户ID不能为空");
			return result;
		}
		if (StringHelper.isNull(getCartIds()) || isNotNumArray(getCartIds())) {
			result.wrong("40000", "购物车不能为空");
			return result;
		}
		if (getAddressId() == null) {
			result.wrong("40000", "收货地址ID不能为空");
			return result;
		}
		if (StringHelper.isNull(getToken())) {
			result.wrong("40000", "token不能为空");
			return result;
		}
		if (StringHelper.isNull(getTimestamp())) {
			result.wrong("40000", "时间戳不能为空");
			return result;
		}
		if (StringHelper.isNull(getSign())) {
			result.wrong("40000", "签名不能为空");
			return result;
		}
		String sign = this.toData().makeSign();
		if (!sign.equals(this.getSign())) {
			result.wrong("40000", "签名错误");
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

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getCartIds() {
		return cartIds;
	}

	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
