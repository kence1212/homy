package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizPaint implements Serializable {
    private Integer id;

    private String paintName;

    private String paintCode;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modfiyId;

    private String paintDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaintName() {
        return paintName;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName == null ? null : paintName.trim();
    }

    public String getPaintCode() {
        return paintCode;
    }

    public void setPaintCode(String paintCode) {
        this.paintCode = paintCode == null ? null : paintCode.trim();
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

    public Integer getModfiyId() {
        return modfiyId;
    }

    public void setModfiyId(Integer modfiyId) {
        this.modfiyId = modfiyId;
    }

    public String getPaintDesc() {
        return paintDesc;
    }

    public void setPaintDesc(String paintDesc) {
        this.paintDesc = paintDesc == null ? null : paintDesc.trim();
    }
}