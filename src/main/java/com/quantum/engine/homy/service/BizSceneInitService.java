package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizSceneInit;

public interface BizSceneInitService {

	void create(String name, String sceneInfo, Integer userId);


	List<BizSceneInit> getList();

}
