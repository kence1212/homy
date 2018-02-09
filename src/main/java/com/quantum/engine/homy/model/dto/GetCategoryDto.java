package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class GetCategoryDto extends GetDto {
	
	private Integer brandId;
	
	public Integer getBrandId() {
		return brandId;
	}
	
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		if(brandId != null){
			cd.setValue("brandId", this.getBrandId());
		}
		cd.setValue("token", this.getToken());
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
		return cd;
	}
	
	@Override
	public Result validate() {
		Result result = new Result();
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
