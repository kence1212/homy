/**
 * 
 */
package com.quantum.engine.homy.model.request;

import java.util.Date;

import com.quantum.engine.homy.model.BizGoodsBase;

/**
 * @author nicya
 * @date 2017年11月20日 下午3:43:59
 */
public class AddGoodsReq {
	
	private Double defaultPrice;
	private String goodsName;
	private String goodsTypeNumber;
	private String goodsIcon;
	private String[] goodsImg;
	private String[] goodsDescImg;
	private Integer goodsCategoryId;
	private Integer goodsStyleId;
	private String  mainTexture;//主材
	private String  subTexture;//辅材
	private String[] goodsColors;
	private String[] goodsSizes;
	private String[] goodsTextures;//材质
	private Integer  brandId;
	private String goodsDesc;
	
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * @return the goodsTypeNumber
	 */
	public String getGoodsTypeNumber() {
		return goodsTypeNumber;
	}
	/**
	 * @param goodsTypeNumber the goodsTypeNumber to set
	 */
	public void setGoodsTypeNumber(String goodsTypeNumber) {
		this.goodsTypeNumber = goodsTypeNumber;
	}
	/**
	 * @return the goodsIcon
	 */
	public String getGoodsIcon() {
		return goodsIcon;
	}
	/**
	 * @param goodsIcon the goodsIcon to set
	 */
	public void setGoodsIcon(String goodsIcon) {
		this.goodsIcon = goodsIcon;
	}
	/**
	 * @return the goodsImg
	 */
	public String[] getGoodsImg() {
		return goodsImg;
	}
	/**
	 * @param goodsImg the goodsImg to set
	 */
	public void setGoodsImg(String[] goodsImg) {
		this.goodsImg = goodsImg;
	}
	/**
	 * @return the goodsCategoryId
	 */
	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}
	/**
	 * @param goodsCategoryId the goodsCategoryId to set
	 */
	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	/**
	 * @return the goodsStyleId
	 */
	public Integer getGoodsStyleId() {
		return goodsStyleId;
	}
	/**
	 * @param goodsStyleId the goodsStyleId to set
	 */
	public void setGoodsStyleId(Integer goodsStyleId) {
		this.goodsStyleId = goodsStyleId;
	}
	/**
	 * @return the mainTexture
	 */
	public String getMainTexture() {
		return mainTexture;
	}
	/**
	 * @param mainTexture the mainTexture to set
	 */
	public void setMainTexture(String mainTexture) {
		this.mainTexture = mainTexture;
	}
	/**
	 * @return the subTexture
	 */
	public String getSubTexture() {
		return subTexture;
	}
	/**
	 * @param subTexture the subTexture to set
	 */
	public void setSubTexture(String subTexture) {
		this.subTexture = subTexture;
	}
	/**
	 * @return the goodsColors
	 */
	public String[] getGoodsColors() {
		return goodsColors;
	}
	/**
	 * @param goodsColors the goodsColors to set
	 */
	public void setGoodsColors(String[] goodsColors) {
		this.goodsColors = goodsColors;
	}
	/**
	 * @return the goodsSizes
	 */
	public String[] getGoodsSizes() {
		return goodsSizes;
	}
	/**
	 * @param goodsSizes the goodsSizes to set
	 */
	public void setGoodsSizes(String[] goodsSizes) {
		this.goodsSizes = goodsSizes;
	}
	/**
	 * @return the goodsTextures
	 */
	public String[] getGoodsTextures() {
		return goodsTextures;
	}
	/**
	 * @param goodsTextures the goodsTextures to set
	 */
	public void setGoodsTextures(String[] goodsTextures) {
		this.goodsTextures = goodsTextures;
	}
	public Double getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String[] getGoodsDescImg() {
		return goodsDescImg;
	}
	public void setGoodsDescImg(String[] goodsDescImg) {
		this.goodsDescImg = goodsDescImg;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public BizGoodsBase toGoodsBase(){
		BizGoodsBase base = new BizGoodsBase();
		base.setGoodsName(this.getGoodsName());
		base.setCreateTime(new Date());
		base.setGoodsIcon(this.getGoodsIcon());
		base.setGoodsTypeNumber(this.getGoodsTypeNumber());
		base.setHave3d(false);
		base.setGoodsStyleId(this.getGoodsStyleId());
		base.setGoodsCategoryId(this.getGoodsCategoryId());
		base.setGoodsState(BizGoodsBase.GOODS_STATE_NORMAL);
		base.setDefaultPrice(this.getDefaultPrice());
		base.setBrandId(this.getBrandId());
		base.setGoodsDesc(this.getGoodsDesc());
		return base;
	}

}
