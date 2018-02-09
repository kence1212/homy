package com.quantum.engine.homy.dao;

import java.util.List;

import com.quantum.engine.homy.model.BizPaint;

public interface BizPaintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizPaint record);

    int insertSelective(BizPaint record);

    BizPaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizPaint record);

    int updateByPrimaryKeyWithBLOBs(BizPaint record);

    int updateByPrimaryKey(BizPaint record);
    
    List<BizPaint> getAll();
}