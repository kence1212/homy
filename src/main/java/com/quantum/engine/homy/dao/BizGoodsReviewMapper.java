package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsReview;
import com.quantum.engine.homy.model.ext.BizGoodsReviewExt;

public interface BizGoodsReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsReview record);

    int insertSelective(BizGoodsReview record);

    BizGoodsReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsReview record);

    int updateByPrimaryKey(BizGoodsReview record);

	BizGoodsReview getByUserIdAndGoodsItemId(@Param("userId")Integer userId, @Param("goodsItemId")Integer goodsItemId);

	int getCountByItemIds(@Param("itemIds")List<Integer> itemIds);

	List<BizGoodsReviewExt> getReviewList(@Param("goodsId")Integer goodsId, @Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);
	
	public int countByGoodsId(Integer goodsId);
	
	public BizGoodsReviewExt getLastestReviewByGoodsId(Integer goodsId);
	
}