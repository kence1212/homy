package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BasRegionMapper;
import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.ext.BasRegionExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.BasRegionService;

@Service
public class BasRegionServiceImpl implements BasRegionService {

	@Autowired
	BasRegionMapper mapper;
	
	@Override
	public List<BasRegion> getRegion(int pid) {
		return mapper.getRegion(pid);
	}
	
	@Override
	public List<BasRegion> getAll(){
		return mapper.getAll();
	}

	@Override
	public Map<String, Object> getHotListAndRegionList() {
		
		List<BasRegion> regionList = mapper.getAllCity();
		 
		List<BasRegion> hotList = mapper.getHotRegion();
		
		Map<String,Object> all = new HashMap<>();
		
		all.put("hotList", hotList);
		
		all.put("regionList", regionList);
		
		return all;
	}

	@Override
	public List<BasRegionExt> hotCitylist() {
		
		return mapper.getHotCitylist();
	}

	@Override
	public void setHotCityById(Integer id) {
		
		mapper.setHotCityById(id);
		
	}

	@Override
	public int delete(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<BasRegionExt> queryList(int page, int pageSize, String keyword) {
		
		if(keyword != null || keyword != ""){
			
			keyword = "%"+keyword+"%";
			
		}
		
		Page<BasRegionExt> pageMsg = new Page<>();
		
		pageMsg.setPageNum(page);
		
		pageMsg.setPageSize(pageSize);
		
		int totalCount = mapper.getCount(keyword);
		
		pageMsg.setTotalCount(totalCount);
		
		if(totalCount == 0){
			pageMsg.setList(new ArrayList<BasRegionExt>());
			return pageMsg;
		}
		
		ArrayList<BasRegionExt> list = mapper.getPageList(page,(page-1)*pageSize,pageSize,keyword);
		
		pageMsg.setList(list);
		
		return pageMsg;
	}

	@Override
	public int removeHotCityById(Integer id) {
		return mapper.removeHotCityById(id);
	}

	@Override
	public BasRegion getParentRegionById(Integer id) {
		
		return mapper.getParentRegionById(id);
		
	}

	@Override
	public BaseResponse getCityByName(String cityName) {
		
		if(cityName == null){
			cityName = "";
		}
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		Map<String,Object> defaultRegion  = mapper.getCityByName(cityName);
		if(defaultRegion == null || defaultRegion.size() == 0){
			
			defaultRegion = new HashMap<>();
			defaultRegion.put("id", 234);
			defaultRegion.put("name", "深圳市");
			
		}
		
		br.setObj(defaultRegion);
		return br;
		
	}
	
}
