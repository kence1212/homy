package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BasGoodsBrand implements Serializable {
	
    private Integer brandId;

    private String brandName;

    private String brandCode;

    private String brandDesc;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private String icon;

    private String remark;

    private Integer tsort;
    
    private String appClientIcon;

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
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc == null ? null : brandDesc.trim();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTsort() {
        return tsort;
    }

    public void setTsort(Integer tsort) {
        this.tsort = tsort;
    }

	public String getAppClientIcon() {
		return appClientIcon;
	}

	public void setAppClientIcon(String appClientIcon) {
		this.appClientIcon = appClientIcon;
	}
}