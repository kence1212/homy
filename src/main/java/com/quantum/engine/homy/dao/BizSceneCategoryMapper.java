package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizSceneCategory;

public interface BizSceneCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizSceneCategory record);

    int insertSelective(BizSceneCategory record);

    BizSceneCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizSceneCategory record);

    int updateByPrimaryKey(BizSceneCategory record);

	int getCount(@Param("keyword")String keyword);

	List<BizSceneCategory> getList(@Param("keyword")String keyword, @Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);
}