package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderListExt;

public interface BizOrderMapper {
    int insert(BizOrder record);

    int insertSelective(BizOrder record);

    BizOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizOrder record);

    int updateByPrimaryKey(BizOrder record);

	List<BizOrderExt> listOrder(@Param("orderIds")List<Integer> orderIds, @Param("userId")Integer userId, @Param("stateList")List<Integer> stateList);

	int getCountByOno(String genOrderNo);

	int getCountByTno(String genOrderTradeNo);

	int setState(@Param("id")Integer id, @Param("state")Integer state);

	int deleteOrder(@Param("id")Integer id, @Param("userId")Integer userId);

	int getTotalCount(@Param("orderNo")String orderNo, @Param("outTradeNo")String outTradeNo, @Param("state")Integer state);

	List<BizOrderListExt> getList(@Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize, @Param("orderNo")String orderNo, @Param("outTradeNo")String outTradeNo, @Param("state")Integer state);

	int setStateByUser(@Param("id")Integer id, @Param("userId")Integer userId, @Param("state")int state);

	BizOrderExt getDetail(Integer id);

	BizOrder getByOrderItemId(Integer goodsItemId);

}