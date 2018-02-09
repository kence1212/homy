package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizGoodsTexture implements Serializable {
    private Integer id;

    private String textureName;

    private String textureCode;

    private Integer goodsId;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName == null ? null : textureName.trim();
    }

    public String getTextureCode() {
        return textureCode;
    }

    public void setTextureCode(String textureCode) {
        this.textureCode = textureCode == null ? null : textureCode.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }
}