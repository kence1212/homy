package com.quantum.engine.homy.dao;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizOtherLogin;

public interface BizOtherLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizOtherLogin record);

    int insertSelective(BizOtherLogin record);

    BizOtherLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizOtherLogin record);

    int updateByPrimaryKey(BizOtherLogin record);

	BizOtherLogin getByOpenId(String openid);

	int updateUserId(@Param("userId")Integer userId, @Param("loginToken")String loginToken, @Param("otherLoginId")Integer otherLoginId);

	BizOtherLogin getByTypeAndUserId(@Param("type")Integer type, @Param("userId")Integer userId);
}