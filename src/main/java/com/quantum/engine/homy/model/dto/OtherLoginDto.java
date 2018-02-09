package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class OtherLoginDto {

	private String openid;
	private String unionid;
	private Integer type;
	private String portrait;
	private String nickname;
	private String timestamp;
	private String sign;
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("openid", openid);
		if(unionid != null){
			cd.setValue("unionid", unionid);
		}
		cd.setValue("type", type);
		cd.setValue("portrait", portrait);
		cd.setValue("nickname", nickname);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}
	
	public Result validate() {
		Result result = new Result();
		if(openid == null){
			result.wrong("40000" , "openid不能为空");
			return result;
		}
		
		if(type == null){
			result.wrong("40000" , "type不能为空");
			return result;
		}
		
		if(portrait == null){
			result.wrong("40000" , "portrait不能为空");
			return result;
		}
		
		if(nickname == null){
			result.wrong("40000" , "nickname不能为空");
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
	
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
