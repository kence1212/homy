package com.quantum.engine.homy.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.BizGoodsBase;

public class BizGoodsBoutiqueExt implements Serializable {
    private Integer id;

    private Integer type;

    private String imgPath;

    private Integer position;

    private Integer goodsId;

    private Integer sort;

    private String desc;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;

    private BizGoodsBase bizGoodsBase;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
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

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public BizGoodsBase getBizGoodsBase() {
		return bizGoodsBase;
	}

	public void setBizGoodsBase(BizGoodsBase bizGoodsBase) {
		this.bizGoodsBase = bizGoodsBase;
	}
    
    
}