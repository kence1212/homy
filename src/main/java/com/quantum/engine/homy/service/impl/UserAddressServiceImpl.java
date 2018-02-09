package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.UserAddressMapper;
import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.ext.UserAddressExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService {

	@Autowired
	UserAddressMapper mapper;
	
	@Override
	public UserAddress getDefaultAddr(Integer userId) {
		return mapper.getDefaultAddr(userId);
	}

	@Override
	public BaseResponse saveUserAddr(UserAddress userAddress) {
		
		int totalCount = mapper.getTotalCount(userAddress.getUserId());
		
		if(totalCount >= 20){
			return BaseResponse.getWrong("最多保存20个地址");
		}
		
		int count = mapper.selectByUserId(userAddress.getUserId());
		
		userAddress.setCreateTime(new Date());
		
		if(count == 0){
			userAddress.setIsDefault((short)1);
		} else if(userAddress.getIsDefault() != null && userAddress.getIsDefault() == 1){
			mapper.updateByUserId(userAddress.getUserId());
		} else{
			userAddress.setIsDefault((short)0);
		}
		mapper.insert(userAddress);
		
		BaseResponse br = BaseResponse.getSuccess("保存成功");
		return br;
	}

	@Override
	public Page getUserAddrList(Integer page, Integer pageSize, Integer userId) {
		
		Page pageMsg = new Page();
		
		pageMsg.setPageNum(page);
		
		pageMsg.setPageSize(pageSize);
		
		int count = mapper.getTotalCount(userId);
		
		pageMsg.setTotalCount(count);
		
		if(count == 0){
			
			pageMsg.setList(new ArrayList<>());
			
			return pageMsg;
			
		}
		
		List<UserAddressExt> list = mapper.getUserAddrList(page,(page-1)*pageSize,pageSize,userId);
		
		pageMsg.setList(list);
		
		return pageMsg;
	}

	@Override
	public void deleteAddr(Integer delId, Integer userId) {
		
		UserAddress selectByPrimaryKey = mapper.selectByPrimaryKey(delId);
		if(selectByPrimaryKey != null && selectByPrimaryKey.getIsDefault() != null && selectByPrimaryKey.getIsDefault() == (short)1){
			UserAddress newest = mapper.selectByUserIdAndCreateTime(userId);
			if(newest != null){
				newest.setIsDefault((short)1);
				mapper.updateByPrimaryKeySelective(newest);
			}
		}
		mapper.deleteByDelIdAndUserId(delId,userId);
		
		
	}

	@Override
	public void editUserAddr(UserAddress userAddress) {
		
		if(userAddress.getIsDefault() != null && (short)1 == userAddress.getIsDefault()){
			
			mapper.updateByUserId(userAddress.getUserId());
			
		}
		
		mapper.updateByPrimaryKeySelective(userAddress);
		
	}

	@Override
	public UserAddress getAddr(Integer addrId) {
		
		return mapper.selectByPrimaryKey(addrId);
		
	}

	@Override
	public void setDefaultAddr(Integer addrId, Integer userId) {
		
			mapper.updateByUserId(userId);
			mapper.setDefaultAddrById(addrId);
			
	}

	@Override
	public UserAddressExt getDefaultAddrExt(Integer userId) {
		return mapper.getDefaultAddrExt(userId);
	}

	@Override
	public UserAddressExt getAddrExt(Integer addressId) {
		return mapper.getAddrExt(addressId);
	}

}
