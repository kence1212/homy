package com.quantum.engine.homy.service;

import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.ext.UserAddressExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

public interface UserAddressService {

	UserAddress getDefaultAddr(Integer userId);

	BaseResponse saveUserAddr(UserAddress userAddress);

	Page getUserAddrList(Integer page, Integer pageSize, Integer userId);

	void deleteAddr(Integer delId, Integer userId);

	void editUserAddr(UserAddress userAddress);

	UserAddress getAddr(Integer addrId);

	void setDefaultAddr(Integer addrId, Integer userId);

	UserAddressExt getDefaultAddrExt(Integer userId);

	UserAddressExt getAddrExt(Integer addressId);

}
