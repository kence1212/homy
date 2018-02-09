package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class GetBrandDto extends GetDto {
	
	private Integer categoryId;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public ClientData toData(){
		ClientData cd = new ClientData();
		if(categoryId != null){
			cd.setValue("categoryId", this.getCategoryId());
		}
		if(StringHelper.isNotNull(this.getToken())){
			cd.setValue("token", this.getToken());
		}
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
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
		String sign = this.toData().makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}

}
