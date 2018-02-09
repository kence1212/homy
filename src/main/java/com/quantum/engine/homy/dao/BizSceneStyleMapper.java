package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizSceneStyle;
import com.quantum.engine.homy.model.ext.BizSceneStyleExt;

public interface BizSceneStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizSceneStyle record);

    int insertSelective(BizSceneStyle record);

    BizSceneStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizSceneStyle record);

    int updateByPrimaryKeyWithBLOBs(BizSceneStyle record);

    int updateByPrimaryKey(BizSceneStyle record);

	int getCount(@Param("keyword")String keyword);

	List<BizSceneStyleExt> getList(@Param("keyword")String keyword, @Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	int getCountByCategoryId(@Param("sceneCategoryId")Integer sceneCategoryId);

	List<BizSceneStyleExt> getStyleList();
}