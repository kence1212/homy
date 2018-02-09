package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.ext.UserAddressExt;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

	UserAddress getDefaultAddr(Integer userId);

	int selectByUserId(Integer userId);

	int updateByUserId(Integer userId);

	int getTotalCount(Integer userId);

	List<UserAddressExt> getUserAddrList(@Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize, @Param("userId")Integer userId);

	int deleteByDelIdAndUserId(@Param("delId")Integer delId, @Param("userId")Integer userId);

	int setDefaultAddrById(Integer addrId);

	UserAddress selectByUserIdAndCreateTime(Integer userId);

	UserAddressExt getDefaultAddrExt(Integer userId);

	UserAddressExt getAddrExt(Integer addressId);
	
}