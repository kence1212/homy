package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.result.GoodsListItemResult;

public interface BizGoodsBaseMapper {
	
	List<BizGoodsBase> getAll();
	
	List<BizGoodsBase> getAllExt();
	
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsBase record);

    int insertSelective(BizGoodsBase record);

    BizGoodsBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsBase record);

    int updateByPrimaryKey(BizGoodsBase record);

	int queryTotal(@Param("keyword")String keyword);
	
	List<BizGoodsBase> queryList(@Param("page")int page, @Param("pageSize")int pageSize, @Param("keyword")String keyword);
	
	List<BizGoodsBase> list(Map<String, Object> params);
	
	int countTotal(Map<String, Object> params);
	
	List<String> searchAsso(@Param("searchStr")String searchStr);
	
	List<GoodsListItemResult> search(Map<String, Object> params);
	
	List<String> searchGoodsTexture(Map<String, Object> params);
	
	BizGoodsBase getDetailById(Integer goodsId);

	GoodsListItemResult getByGoodsIdAndCityId(@Param("goodsId")Integer goodsId, @Param("cityId")Integer cityId);
	
	List<String> getGoodsNameByOrderIds(List<Integer> orderIds);
}