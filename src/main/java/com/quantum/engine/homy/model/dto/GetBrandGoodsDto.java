package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @author nicya
 * @date 2017年12月14日 下午7:08:34
 */
public class GetBrandGoodsDto extends GetDto {
	
	private Integer brandId;
	private Integer categoryId;
	private Integer page;
	private Integer cityId;
	
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("brandId", this.getBrandId());
		if(categoryId != null){
			cd.setValue("categoryId", this.getCategoryId());
		}
		if(cityId != null){
			cd.setValue("cityId", this.getCityId());
		}
		if(page != null){
			cd.setValue("page", this.getPage());
		}
		cd.setValue("timestamp", this.getTimestamp());
		cd.setValue("sign", this.getSign());
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		if(getBrandId() == null){
			result.wrong("40000" , "品牌ID不能为空");
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
