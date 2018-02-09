package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.ext.BasRegionExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

public interface BasRegionService {

	List<BasRegion> getRegion(int pid);
	
	List<BasRegion> getAll();

	Map<String, Object> getHotListAndRegionList();

	List<BasRegionExt> hotCitylist();

	void setHotCityById(Integer id);

	int delete(Integer id);

	Page<BasRegionExt> queryList(int page, int pageSize, String keyword);

	int removeHotCityById(Integer id);

	BasRegion getParentRegionById(Integer id);

	BaseResponse getCityByName(String cityName);

}
