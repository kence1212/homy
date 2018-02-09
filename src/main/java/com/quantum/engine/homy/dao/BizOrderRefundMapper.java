package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizOrderRefund;

public interface BizOrderRefundMapper {
	
	BizOrderRefund getByOrderId(Integer orderId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(BizOrderRefund record);

    int insertSelective(BizOrderRefund record);

    BizOrderRefund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizOrderRefund record);

    int updateByPrimaryKey(BizOrderRefund record);
    
    void reject(BizOrderRefund record);
    
    List<BizOrderRefund> list(Map<String, Object> params);
    
    int count(Map<String, Object> params);
}