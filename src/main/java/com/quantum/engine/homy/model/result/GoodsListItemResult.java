package com.quantum.engine.homy.model.result;

import java.io.Serializable;

public class GoodsListItemResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer goodsId;
	private String  goodsName;
	private String  goodsDesc;
	private String  goodsIcon;
	private Double  minPrice;
	private Double  maxPrice;
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getGoodsIcon() {
		return goodsIcon;
	}
	public void setGoodsIcon(String goodsIcon) {
		this.goodsIcon = goodsIcon;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	
	public GoodsAppShowResult toShow(){
		GoodsAppShowResult show = new GoodsAppShowResult();
		show.setDescription(this.getGoodsDesc());
		show.setId(this.getGoodsId());
		show.setImageUrl(this.getGoodsIcon());
		show.setName(this.getGoodsName());
		show.setMaxPrice(this.getMaxPrice());
		show.setMinPrice(this.getMinPrice());
		return show;
	}
	
	public GoodsAppShowResult toReviewListShow(String BaseUrl){
		GoodsAppShowResult show = new GoodsAppShowResult();
		String priceRegion = "暂无价格";
		if(getMinPrice() != null && getMaxPrice() == null){
			priceRegion = getMinPrice()+"";
		} else if(getMinPrice() == null && getMaxPrice() != null){
			priceRegion = getMaxPrice() + "";
		} else if(getMinPrice() != null && getMaxPrice() != null){
			priceRegion =  getMinPrice() + "-" + getMaxPrice();
		}
		show.setPriceRegion(priceRegion);
		show.setImageUrl(BaseUrl + getGoodsIcon());
		show.setName(getGoodsName());
		return show;
		
	}
	
}
