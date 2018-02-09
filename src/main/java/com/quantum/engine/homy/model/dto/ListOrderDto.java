package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class ListOrderDto {

	private Integer userId;
	private String orderIds;
	private String state;
	private String token;
	private String timestamp;
	private String sign;

	public ClientData toData() {
		ClientData cd = new ClientData();
		cd.setValue("userId", getUserId());
		if (getOrderIds() != null) {
			cd.setValue("orderIds", getOrderIds());
		}
		if (state != null) {
			cd.setValue("state", getState());
		}
		cd.setValue("token", getToken());
		cd.setValue("timestamp", getTimestamp());
		cd.setValue("sign", getSign());
		return cd;
	}

	public Result validate() {
		Result result = new Result();
		if (getUserId() == null) {
			result.wrong("40000", "用户ID不能为空");
			return result;
		}
		if (getState() != null && isNotNumArray(getState())) {
			result.wrong("40000", "无此状态");
			return result;
		}
		if (getOrderIds() != null && isNotNumArray(getOrderIds())) {
			result.wrong("40000", "订单ID格式错误");
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

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isNotNumArray(String s) {

		boolean result = true;
		String[] split = s.split(",");
		for (String string : split) {
			try {
				Integer.parseInt(string);
			} catch (Exception e) {
				result = false;
			}
		}
		return !result;
	}
}
