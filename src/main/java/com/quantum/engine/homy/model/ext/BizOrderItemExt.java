package com.quantum.engine.homy.model.ext;

import com.quantum.engine.homy.model.BizOrderItem;

public class BizOrderItemExt extends BizOrderItem{
	
	private Integer brandId;
	
	private BizGoodsSkuExt bizGoodsSku;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public BizGoodsSkuExt getBizGoodsSku() {
		return bizGoodsSku;
	}

	public void setBizGoodsSku(BizGoodsSkuExt bizGoodsSku) {
		this.bizGoodsSku = bizGoodsSku;
	}
	
}
