package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsModel;

public class BizGoodsModelExt extends BizGoodsModel implements Serializable {
	
	private BizGoodsBase bizGoodsBase;

	private String colorName;
    
    private String sizeName;
    
    private String textureName;
    

    private static final long serialVersionUID = 1L;

    
    public BizGoodsBase getBizGoodsBase() {
		return bizGoodsBase;
	}

	public void setBizGoodsBase(BizGoodsBase bizGoodsBase) {
		this.bizGoodsBase = bizGoodsBase;
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