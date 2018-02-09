package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.PhoneUtil;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: RegisterDto 
 * @Description: 登录DTO 
 * @author nicya
 * @date 2017年11月6日 下午7:03:28 
 *
 */
public class RegisterDto extends Dto {
	
	private String phone;
	private String code;
	private String password;
	private Integer grade;
	private String timestamp;
	private String sign;
	
	private String job;//职位
	private String company;//公司名
	private String companyAddr;//公司地址
	
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
	
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("phone", phone);
		cd.setValue("code", code);
		cd.setValue("grade", grade);
		cd.setValue("password", password);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		if(this.getGrade() != null 
				&& ConstantsKey.USER_GRADE_COMPANY == this.getGrade()){
			cd.setValue("job", job);
			cd.setValue("company", company);
			cd.setValue("companyAddr", companyAddr);
		}
		
		return cd;
	}
	
	public Result validate(){
		Result result = new Result();
		if(StringHelper.isNull(getPhone())){
			result.wrong("40000" , "电话号码不能为空");
			return result;
		}
		if(!PhoneUtil.checkPhoneNo(getPhone())){
			result.wrong("40000" , "电话号码错误");
			return result;
		}
		if(grade == null){
			result.wrong("40000" , "用户类型不能为空");
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
		if(this.getGrade() != null 
				&& ConstantsKey.USER_GRADE_COMPANY == this.getGrade()){
			if(StringHelper.isNull(getJob())){
				result.wrong("40000" , "职位不能为空");
				return result;
			}
			if(StringHelper.isNull(getCompany())){
				result.wrong("40000" , "公司名不能为空");
				return result;
			}
			if(StringHelper.isNull(getCompanyAddr())){
				result.wrong("40000" , "公司地址不能为空");
				return result;
			}
		}
		String sign = this.toData().makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}
	
}
