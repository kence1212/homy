package com.quantum.engine.homy.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizOrderPayInfo;

public interface BizOrderPayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizOrderPayInfo record);

    int insertSelective(BizOrderPayInfo record);

    BizOrderPayInfo selectByPrimaryKey(Integer id);
    
    BizOrderPayInfo getByOutTradeNo(String outTradeNo);

    int updateByPrimaryKeySelective(BizOrderPayInfo record);

    int updateByPrimaryKey(BizOrderPayInfo record);
    
    List<BizOrderPayInfo> listAutoQuery(@Param("state")Integer state,@Param("preDate")Date preDate);
}