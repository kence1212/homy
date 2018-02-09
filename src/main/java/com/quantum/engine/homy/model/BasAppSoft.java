package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BasAppSoft implements Serializable {
	
    private Integer id;

    private String appCode;

    private Integer appVersion;

    private String appVersionStr;

    private Integer appType;

    private Boolean compelUpdate;

    private Integer createId;

    private Date createTime;

    private String appInfo;
    
    private String downloadUrl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionStr() {
        return appVersionStr;
    }

    public void setAppVersionStr(String appVersionStr) {
        this.appVersionStr = appVersionStr == null ? null : appVersionStr.trim();
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Boolean getCompelUpdate() {
        return compelUpdate;
    }

    public void setCompelUpdate(Boolean compelUpdate) {
        this.compelUpdate = compelUpdate;
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

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo == null ? null : appInfo.trim();
    }

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}