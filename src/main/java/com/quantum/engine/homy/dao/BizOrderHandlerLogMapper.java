package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizOrderHandlerLog;
import com.quantum.engine.homy.model.ext.BizOrderHandlerLogExt;

public interface BizOrderHandlerLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizOrderHandlerLog record);

    int insertSelective(BizOrderHandlerLog record);

    BizOrderHandlerLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizOrderHandlerLog record);

    int updateByPrimaryKeyWithBLOBs(BizOrderHandlerLog record);

    int updateByPrimaryKey(BizOrderHandlerLog record);

	List<BizOrderHandlerLogExt> getLogByOrderId(Integer orderId);
}