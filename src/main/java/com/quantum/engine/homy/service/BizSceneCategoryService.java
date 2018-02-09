package com.quantum.engine.homy.service;

import com.quantum.engine.homy.model.BizSceneCategory;
import com.quantum.engine.homy.model.page.Page;

/**
 * @author fzz
 * @date 2018年1月18日
 * @description
 */
public interface BizSceneCategoryService {
	
	
	public void add(BizSceneCategory sceneCategory);
	
	public int delete(Integer id);
	
	public BizSceneCategory getById(Integer id);

	public Page<BizSceneCategory> getList(String keyword, Integer page, Integer pageSize);

	public void update(BizSceneCategory sceneCategory);

}
