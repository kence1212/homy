package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizGoodsCategory implements Serializable {
	
	public static final int TYPE_SPACE = 0;
	public static final int TYPE_CATEGORY = 1;
	
    private Integer id;
    private String categoryName;
    private String categoryCode;
    private String icon;
    private Integer pid;
    private Date createTime;
    private Integer createId;
    private Date modifyTime;
    private Integer modifyId;
    private String categoryDesc;
    private String appClientIcon;
    private Integer tsort;
    private Integer categoryType;//0空间 1品类
    
    private static final long serialVersionUID = 1L;

    public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

	public String getAppClientIcon() {
		return appClientIcon;
	}

	public void setAppClientIcon(String appClientIcon) {
		this.appClientIcon = appClientIcon;
	}

	public Integer getTsort() {
		return tsort;
	}

	public void setTsort(Integer tsort) {
		this.tsort = tsort;
	}

}