package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.PhoneUtil;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: FindPasswordDto 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月7日 下午4:14:03 
 *
 */
public class FindPasswordDto extends Dto {
	
	private String phone;
	private String code;
	private String password;
	private String timestamp;
	private String sign;

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
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("phone", phone);
		cd.setValue("code", code);
		cd.setValue("password", password);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		if(StringHelper.isNull(getPhone())){
			result.wrong("40000" , "电话号码不能为空");
			return result;
		}
		if(!PhoneUtil.checkPhoneNo(getPhone())){
			result.wrong("40000" , "电话号码错误");
			return result;
		}
		if(StringHelper.isNull(getCode())){
			result.wrong("40000" , "验证码不能为空");
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
