package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quantum.engine.homy.model.BizGoodsSkuStock;

public class BizGoodsSkuStockExt extends BizGoodsSkuStock implements Serializable {

    private Integer goodsId;

    private Integer colorId;

    private Integer sizeId;

    private Integer textureId;
    
    private String cityName;
    
    private String goodsName;
    
    private String colorName;
    
    private String sizeName;
    
    private String textureName;
    
    private String skuIcon;
    
    private Integer num ;
    
    private Integer cartIds;
    
    private Integer brandId;
    
    private String brandName;
    
    public static BizGoodsSkuStockExt toShow(BizGoodsSkuStockExt ext, Integer num, String baseUrl){
    	BizGoodsSkuStockExt show = new BizGoodsSkuStockExt();
    	show.setGoodsName(ext.getGoodsName());
    	show.setSizeName(ext.getSizeName());
    	show.setTextureName(ext.getTextureName());
    	show.setColorName(ext.getColorName());
    	show.setPrice(ext.getPrice());
    	show.setNum(num);
    	show.setSkuIcon(baseUrl+ext.getSkuIcon());
    	show.setBrandId(ext.getBrandId());
    	show.setBrandName(ext.getBrandName());
    	return show;
    }

    private static final long serialVersionUID = 1L;
    
	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getCartIds() {
		return cartIds;
	}

	public void setCartIds(Integer cartIds) {
		this.cartIds = cartIds;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getSkuIcon() {
		return skuIcon;
	}

	public void setSkuIcon(String skuIcon) {
		this.skuIcon = skuIcon;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

  
    
}