package com.quantum.engine.homy.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class UserAddrDto extends GetDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	    private String consignee;

	    private String phone;

	    private Integer provinceId;

	    private Integer cityId;

	    private Integer areaId;

	    private String addressDetail;

	    private Short isDefault;

	    private Integer userId;

	    private Date createTime;
	    
		public ClientData toData(){
			ClientData cd = new ClientData();
			cd.setValue("consignee", consignee);
			cd.setValue("phone", phone);
			cd.setValue("provinceId", provinceId);
			cd.setValue("cityId", cityId);
			cd.setValue("areaId", areaId);
			cd.setValue("addressDetail", addressDetail);
			cd.setValue("isDefault", isDefault);
			cd.setValue("userId", userId);
			cd.setValue("token", super.getToken());
			cd.setValue("timestamp", super.getTimestamp());
			cd.setValue("sign", super.getSign());
			return cd;
		}	    
	    
	    
	public Result validate(){
		Result result = new Result();
		if(StringHelper.isNull(this.phone)){
			result.wrong("40000" , "电话不能为空");
			return result;
		}
		if(StringHelper.isNull(this.consignee)){
			result.wrong("40000" , "收货人不能为空");
			return result;
		}
		if(this.provinceId == null || this.provinceId <= 0 ){
			result.wrong("40000" , "省份不能为空");
			return result;
		}
		if(this.cityId == null || this.cityId <= 0 ){
			result.wrong("40000" , "城市不能为空");
			return result;
		}
		if(this.areaId == null || this.areaId <= 0 ){
			result.wrong("40000" , "区域不能为空");
			return result;
		}
		if(StringHelper.isNull(this.addressDetail)){
			result.wrong("40000" , "详细地址不能为空");
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
		String sign = this.toData()
				.makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
