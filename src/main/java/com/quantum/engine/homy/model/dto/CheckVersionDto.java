package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class CheckVersionDto extends Dto {
	
	private String timestamp;//201711061504
	private String sign;
	private String  appCode;
	private Integer appType;
	private Integer appVersion;
	
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

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("appCode", appCode);
		cd.setValue("appType", appType);
		cd.setValue("appVersion", appVersion);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		if(getAppCode() == null){
			result.wrong("40000" , "包名不能为空");
			return result;
		}
		if(getAppVersion() == null){
			result.wrong("40000" , "版本数字号不能为空");
			return result;
		}
		if(getAppType() == null){
			result.wrong("40000" , "应用类型不能为空");
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
