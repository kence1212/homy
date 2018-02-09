package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsSkuStock;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;

public interface BizGoodsSkuStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsSkuStock record);

    int insertSelective(BizGoodsSkuStock record);

    BizGoodsSkuStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsSkuStock record);

    int updateByPrimaryKey(BizGoodsSkuStock record);

	BizGoodsSkuStock getBySkuIdAndCityId(@Param("skuId")Integer skuId,@Param("cityId")Integer cityId);
	
	BizGoodsSkuStockExt getBySkuIdAndCityIdWithSku(@Param("skuId")Integer skuId,@Param("cityId")Integer cityId);

	int getTotalCount(@Param("keyword")String keyword,@Param("goodsId")Integer goodsId);

	List<BizGoodsSkuStockExt> getList(@Param("page")Integer page, @Param("startIndex")int startIndex, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword, @Param("goodsId")Integer goodsId);
	
	List<BizGoodsSkuStockExt> getByGoodsAndCity(@Param("goodsId")Integer goodsId,@Param("cityId")Integer cityId);
	
	List<BizGoodsSkuStockExt> getByGoodsId(@Param("goodsId")Integer goodsId);

	int getTotalCountBySkuId(Integer skuId);

	BizGoodsSkuStockExt getById(Integer id);

	void setStock(@Param("add")Integer add, @Param("cityId")Integer cityId, @Param("goodsSkuId")Integer goodsSkuId);

	BizGoodsSkuStockExt getGoodsByGoodsIdAndCity(@Param("goodsId")Integer goodsId, @Param("cityId")Integer cityId, @Param("colorId")Integer colorId, @Param("sizeId")Integer sizeId, @Param("textureId")Integer textureId);
	
	int countByColorId(Integer colorId);
	
	int countByTextureId(Integer textureId);
	
	int countBySizeId(Integer sizeId);

}