package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizSceneStyle;
import com.quantum.engine.homy.model.ext.BizSceneStyleExt;
import com.quantum.engine.homy.model.page.Page;

/**
 * @author fzz
 * @date 2018年1月18日
 * @description
 */
public interface BizSceneStyleService {
	
	
	public void add(BizSceneStyle sceneStyle, Integer sceneinitId);
	
	public int delete(Integer id);
	
	public BizSceneStyle getById(Integer id);

	public Page<BizSceneStyleExt> getList(String keyword, Integer page, Integer pageSize);

	public void update(BizSceneStyle bizSceneStyleInDB, Integer sceneInitId);

	public int setState(Integer id, Integer stateNormal);

	public List<BizSceneStyleExt> getStyleList();

}
