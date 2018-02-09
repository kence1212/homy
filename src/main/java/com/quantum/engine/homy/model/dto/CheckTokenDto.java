package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: CheckTokenDto 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月8日 下午2:21:43 
 *
 */
public class CheckTokenDto extends Dto {
	
	private String token;
	private String timestamp;
	private String sign;
	private Integer userId;
	
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
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("userId", userId);
		cd.setValue("sign", sign);
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
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
