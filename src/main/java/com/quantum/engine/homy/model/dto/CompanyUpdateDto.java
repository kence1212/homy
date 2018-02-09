package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class CompanyUpdateDto extends Dto {
	
	private String operatingProjects;
	private String employeeNum;
	private Integer operatingYears;
	private String companyPhone;
	private Integer userId;
	private String token;
	private String timestamp;
	private String sign;
	
	public String getOperatingProjects() {
		return operatingProjects;
	}
	public void setOperatingProjects(String operatingProjects) {
		this.operatingProjects = operatingProjects;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public Integer getOperatingYears() {
		return operatingYears;
	}
	public void setOperatingYears(Integer operatingYears) {
		this.operatingYears = operatingYears;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
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
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		if(StringHelper.isNotNull(this.getOperatingProjects())){
			cd.setValue("operatingProjects", this.getOperatingProjects());
		}
		if(StringHelper.isNotNull(this.getEmployeeNum())){
			cd.setValue("employeeNum", this.getEmployeeNum());
		}
		if(this.getOperatingYears() != null){
			cd.setValue("operatingYears", this.getOperatingYears());
		}
		if(StringHelper.isNotNull(companyPhone)){
			cd.setValue("companyPhone", companyPhone);
		}
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
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
