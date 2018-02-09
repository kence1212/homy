package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @author nicya
 * @date 2017年12月6日 下午3:49:15
 */
public class SearchGoodsDto extends Dto{
	
	private Integer categoryId;
	private Integer page;
	private Integer styleId;
	private Integer minPrice;
	private Integer maxPrice;
	private String  key;
	private String  texture;
	private Integer cityId;
	private String  orderby;
	private String timestamp;//201711061504
	private String sign;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public ClientData toData(){
		ClientData cd = new ClientData();
		if(StringHelper.isNotNull(key)){
			cd.setValue("key", this.getKey());
		}
		if(cityId != null){
			cd.setValue("cityId", this.getCityId());
		}
		if(StringHelper.isNotNull(this.getTexture())){
			cd.setValue("texture", this.getTexture());
		}
		if(page != null){
			cd.setValue("page", this.getPage());
		}
		if(minPrice != null){
			cd.setValue("minPrice", minPrice);
		}
		if(maxPrice != null){
			cd.setValue("maxPrice", maxPrice);
		}
		if(styleId != null){
			cd.setValue("styleId", styleId);
		}
		if(categoryId != null){
			cd.setValue("categoryId", categoryId);
		}
		if(StringHelper.isNotNull(this.getOrderby()))
		{
			cd.setValue("orderby", this.getOrderby());
		}
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		
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
