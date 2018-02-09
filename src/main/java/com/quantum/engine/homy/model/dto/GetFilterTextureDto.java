package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class GetFilterTextureDto extends Dto {
	
	private Integer categoryId;
	private String  timestamp;//201711061504
	private String  sign;
	
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
		if(this.getCategoryId() != null){
			cd.setValue("categoryId", this.getCategoryId());
		}
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
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

}
