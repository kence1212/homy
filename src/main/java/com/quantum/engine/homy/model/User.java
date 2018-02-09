package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: User 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月3日 下午7:02:28 
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int USER_SEX_UNKOWN = 0;
	public static final int USER_SEX_MALE = 1;
	public static final int USER_SEX_FEMALE = 2;
	
	private Integer userId;
	private String  phone;
	private String  password;
	private String  salt;
	private String  username;
	private String  portrait;
	private String  phoneCode;
	private Date    registerTime;
	private String  registerIp;
	private Integer registerClient;
	private String  email;
	private Integer grade;
	private Integer userState;
	private Integer sex;//0 表示未知 , 1表示男性 ， 2表示女性
	private String  nickname;
	
	private String job;//职位
	private String company;//公司名
	private String companyAddr;//公司地址
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public Integer getRegisterClient() {
		return registerClient;
	}
	public void setRegisterClient(Integer registerClient) {
		this.registerClient = registerClient;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
