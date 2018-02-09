package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizGoodsSku implements Serializable {
    private Integer id;

    private Integer goodsId;

    private Integer colorId;

    private Integer sizeId;

    private Integer textureId;

    private Integer createId;

    private String skuIcon;
    
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getSkuIcon() {
		return skuIcon;
	}

	public void setSkuIcon(String skuIcon) {
		this.skuIcon = skuIcon;
	}
    
    
}