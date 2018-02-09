package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizOrderItem;
import com.quantum.engine.homy.model.ext.BizOrderItemExt;

public interface BizOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizOrderItem record);

    int insertSelective(BizOrderItem record);

    BizOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizOrderItem record);

    int updateByPrimaryKeyWithBLOBs(BizOrderItem record);

    int updateByPrimaryKey(BizOrderItem record);
    
    List<BizOrderItemExt> selectByOrderId(Integer orderId);

	void setState(@Param("id")Integer id, @Param("state")Integer state);
	
	void updateStateByOrderId(@Param("orderId")Integer orderId, @Param("state")Integer state);

	List<Integer> getIdByOrderId(Integer orderId);

	void setStateByOrderId(@Param("orderId")Integer orderId, @Param("state")int state);
}