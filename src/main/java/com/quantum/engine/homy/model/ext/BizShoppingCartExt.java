package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.BizShoppingCart;

public class BizShoppingCartExt extends BizShoppingCart {

	private Integer colorId;

	private Integer sizeId;

	private Integer textureId;

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

}