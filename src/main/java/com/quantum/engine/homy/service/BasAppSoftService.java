package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BasAppSoft;
import com.quantum.engine.homy.model.result.Json;

public interface BasAppSoftService {
	
	BasAppSoft getById(Integer id);
	
	List<BasAppSoft> getAll();
	
	Json add(BasAppSoft basAppSoft);
	
	int delete(Integer id);
	
	BasAppSoft selectMaxVersion(BasAppSoft basAppSoft);

	Json update(BasAppSoft soft);
	
}
