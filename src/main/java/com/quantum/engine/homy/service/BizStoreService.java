package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizStore;

public interface BizStoreService {
	
	public BizStore getById(Integer id);
	public List<BizStore> getAll();
	public void update(BizStore store);
	public void add(BizStore store);
	public int  delete(Integer id);

}
