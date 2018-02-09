package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class BizSceneStyleDto {

	private Integer id;
	private String title;
	private Integer sort;
	private String sceneDesc;
	private Integer sceneInitId;
	private String icon;
	private Integer sceneCategoryId;
	
	public Result validate() {
		Result result = new Result();
		if(StringHelper.isNull(getTitle())){
			result.wrong("40000" , "标题不能为空");
			return result;
		}
		if(getSort() == null){
			result.wrong("40000" , "排序不能为空");
			return result;
		}
		if(getSceneInitId() == null){
			result.wrong("40000" , "配置不能为空");
			return result;
		}
		if(getSceneCategoryId() == null){
			result.wrong("40000" , "场景风格不能为空");
			return result;
		}
		if(StringHelper.isNull(getIcon())){
			result.wrong("40000" , "图标不能为空");
			return result;
		}
		return result;
	}
	
	
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
		this.title = title;
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
		this.sceneDesc = sceneDesc;
	}
	public Integer getSceneInitId() {
		return sceneInitId;
	}
	public void setSceneInitId(Integer sceneInitId) {
		this.sceneInitId = sceneInitId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
