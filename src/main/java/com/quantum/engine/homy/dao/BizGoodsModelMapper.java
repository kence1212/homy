package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;

public interface BizGoodsModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsModel record);

    int insertSelective(BizGoodsModel record);

    BizGoodsModelExt selectByPrimaryKey(Integer id);
    
    void updateState(BizGoodsModel record);

    int updateByPrimaryKeySelective(BizGoodsModel record);

    int updateByPrimaryKey(BizGoodsModel record);
    
    List<BizGoodsModel> getAll();

	List<BizGoodsModel> getByGoodsId(Integer goodsId);
	
	int deleteByGoodsId(Integer goodsId);

	int getTotalCount(@Param("keyword")String keyword);

	List<BizGoodsModelExt> getList(@Param("page")Integer page, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword);

	int selectCountByGoodId(Integer goodsId);

	BizGoodsModel getByProperties(@Param("goodsId")Integer goodsId, @Param("colorId")Integer colorId, @Param("sizeId")Integer sizeId, @Param("textureId")Integer textureId, @Param("modelId")Integer modelId);

}