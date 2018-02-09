package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizStore implements Serializable {
	
    private Integer id;
    private String storeName;
    private String storeNo;
    private Integer userId;
    private String storeIcon;
    private Short storeState;
    private Short storeGrade;
    private Integer createId;
    private Date createTime;
    private Integer modifyId;
    private Date modifyTime;
    private String storeDesc;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStoreIcon() {
        return storeIcon;
    }

    public void setStoreIcon(String storeIcon) {
        this.storeIcon = storeIcon == null ? null : storeIcon.trim();
    }

    public Short getStoreState() {
        return storeState;
    }

    public void setStoreState(Short storeState) {
        this.storeState = storeState;
    }

    public Short getStoreGrade() {
        return storeGrade;
    }

    public void setStoreGrade(Short storeGrade) {
        this.storeGrade = storeGrade;
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

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc == null ? null : storeDesc.trim();
    }
}