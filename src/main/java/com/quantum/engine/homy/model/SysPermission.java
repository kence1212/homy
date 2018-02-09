package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class SysPermission implements Serializable {
    private Integer id;

    private String permissionName;

    private String permissionUri;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private Integer permissionModelId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionUri() {
        return permissionUri;
    }

    public void setPermissionUri(String permissionUri) {
        this.permissionUri = permissionUri == null ? null : permissionUri.trim();
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

    public Integer getPermissionModelId() {
        return permissionModelId;
    }

    public void setPermissionModelId(Integer permissionModelId) {
        this.permissionModelId = permissionModelId;
    }
}