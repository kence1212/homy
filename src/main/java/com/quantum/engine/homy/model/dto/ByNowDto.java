package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class ByNowDto {
    
	private Integer userId;
	
	private Integer cityId;
	
	private Integer goodsId;
	
	private Integer num;
	
    private Integer colorId;   
    
    private Integer sizeId; 
    
    private Integer textureId;  

	private String token; 
	
	private String timestamp;//201711061504
	
	private String sign;
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("cityId", getCityId());
		cd.setValue("userId", getUserId());
		cd.setValue("goodsId", getGoodsId());
		cd.setValue("num", getNum());
		cd.setValue("colorId", getColorId());
		cd.setValue("sizeId", getSizeId());
		cd.setValue("textureId", getTextureId());
		cd.setValue("token", getToken());
		cd.setValue("timestamp", getTimestamp());
		cd.setValue("sign", getSign());
		return cd;
	}
	
	public Result validate() {
		Result result = new Result();
		if(getUserId() == null){
			result.wrong("40000" , "用户ID不能为空");
			return result;
		}
		if(getGoodsId() == null){
			result.wrong("40000" , "商品ID不能为空");
			return result;
		}
		if(getColorId() == null){
			result.wrong("40000" , "颜色ID不能为空");
			return result;
		}
		if(getSizeId() == null){
			result.wrong("40000" , "尺寸ID不能为空");
			return result;
		}
		if(getTextureId() == null){
			result.wrong("40000" , "材质ID不能为空");
			return result;
		}
		if(getNum() == null){
			result.wrong("40000" , "数量不能为空");
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
	
	
    private static final long serialVersionUID = 1L;
    
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public Integer getTextureId() {
		return textureId;
	}

	public void setTextureId(Integer textureId) {
		this.textureId = textureId;
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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

  
    
}
