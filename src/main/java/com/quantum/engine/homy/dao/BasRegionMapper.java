package com.quantum.engine.homy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.ext.BasRegionExt;

public interface BasRegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasRegion record);

    int insertSelective(BasRegion record);

    BasRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasRegion record);

    int updateByPrimaryKey(BasRegion record);

	List<BasRegion> getRegion(@Param("pid")int pid);

	List<BasRegion> getAll();

	List<BasRegion> getHotRegion();
	
	List<BasRegion> getAllCity();

	List<BasRegionExt> getHotCitylist();

	int setHotCityById(Integer id);

	int getCount(@Param("keyword")String keyword);

	ArrayList<BasRegionExt> getPageList(@Param("page")int page,@Param("startIndex") int startIndex, @Param("pageSize")int pageSize,@Param("keyword") String keyword);

	int removeHotCityById(Integer id);

	BasRegion getParentRegionById(Integer id);

	Map<String, Object> getCityByName(@Param("cityName")String cityName);
	
	BasRegion getByCityNameAndGrade(@Param("cityName")String cityName,@Param("grade")Integer grade);

	List<BasRegion> getCityByProvince(@Param("id")Integer id);
}