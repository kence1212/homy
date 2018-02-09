package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class GetGoodsDetailDto extends GetDto {
	
	private Integer goodsId;
	
	private Integer cityId;
	
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
		cd.setValue("goodsId", this.getGoodsId());
		cd.setValue("cityId", this.getCityId());
		return cd;
	}
	
	@Override
	public Result validate() {
		Result result = new Result();
		if(getCityId() == null){
			result.wrong("40000" , "城市不能为空");
			return result;
		}
		if(getGoodsId() == null){
			result.wrong("40000" , "goodsId不能为空");
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

}
