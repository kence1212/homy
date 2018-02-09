package com.quantum.engine.homy.excel;

public class GoodsSkuStockModel {
	
	private Integer goodsId; 	//商品ID
	private String  cityName; 	//城市名 
	private String  colorName;		//颜色
	private String  sizeName;		//规格
	private String  textureName;
	private Integer stock;
	private Double  price;
	private String  country;
	private String  province;
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		return sb.append("[").append("'goodsId:'").append(goodsId)
				.append(",'cityName:'").append(cityName)
				.append(",'colorName:'").append(colorName)
				.append(",'sizeName:'").append(sizeName)
				.append(",'textureName:'").append(textureName)
				.append(",'stock:'").append(stock)
				.append(",'price:'").append(price)
				.append(",'country:'").append(country)
				.append(",'province:'").append(province)
				.append("]").toString();
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getTextureName() {
		return textureName;
	}
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
}
