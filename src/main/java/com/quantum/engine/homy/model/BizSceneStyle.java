package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BizSceneStyle implements Serializable {
	
	/**下架*/
	public static final Integer STATE_DOWN = 0;
	/**上架*/
	public static final Integer STATE_NORMAL = 1;
	
    private Integer id;

    private String title;

    private String icon;

    private Integer state;

    private Integer createId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  
    private Date createTime;

    private Integer sort;

    private String sceneDesc;

    private String sceneInfo;
    
    //scene_category_id
    private Integer sceneCategoryId;

    private static final long serialVersionUID = 1L;
    
    public Integer getSceneCategoryId() {
		return sceneCategoryId;
	}

	public void setSceneCategoryId(Integer sceneCategoryId) {
		this.sceneCategoryId = sceneCategoryId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc == null ? null : sceneDesc.trim();
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo == null ? null : sceneInfo.trim();
    }
}