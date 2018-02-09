package com.quantum.engine.homy.model.ext;

import java.io.Serializable;

import com.quantum.engine.homy.model.BasRegion;

public class BasRegionExt implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private Integer pid;

    private Short grade;

    private Short isHot;
    
    private BasRegion parentRegion;

    private static final long serialVersionUID = 1L;

    public BasRegion getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(BasRegion parentRegion) {
		this.parentRegion = parentRegion;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }
}