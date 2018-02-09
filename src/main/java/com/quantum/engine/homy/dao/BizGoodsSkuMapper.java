package com.quantum.engine.homy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizGoodsSku;

public interface BizGoodsSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizGoodsSku record);

    int insertSelective(BizGoodsSku record);

    BizGoodsSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizGoodsSku record);

    int updateByPrimaryKey(BizGoodsSku record);

	BizGoodsSku getByColorAndSizeAndTex(@Param("colorId")Integer colorId, @Param("sizeId")Integer sizeId, @Param("textureId")Integer textureId, @Param("goodsId")Integer goodsId);

	List<Map<String,Object>> getSizeList(@Param("goodsId")Integer goodsId, @Param("colorId")Integer colorId, @Param("textureId")Integer textureId, @Param("cityId")Integer cityId);

	List<Map<String, Object>> getColorList(@Param("goodsId")Integer goodsId, @Param("sizeId")Integer sizeId, @Param("textureId")Integer textureId, @Param("cityId")Integer cityId);

	List<Map<String, Object>> getTextureList(@Param("goodsId")Integer goodsId, @Param("sizeId")Integer sizeId, @Param("colorId")Integer colorId, @Param("cityId")Integer cityId);

	BizGoodsSku getByGoodsIdAndProperties(@Param("goodsId")Integer goodsId, @Param("colorId")Integer colorId, @Param("sizeId")Integer sizeId, @Param("textureId")Integer textureId);

	List<Map<String, Object>> getCityList(@Param("goodsId")Integer goodsId, @Param("colorId")Integer colorId, @Param("textureId")Integer textureId, @Param("sizeId")Integer sizeId);
	
	int countByColorId(Integer colorId);
	
	int countByTextureId(Integer textureId);
	
	int countBySizeId(Integer sizeId);
	
	void deleteByColorId(Integer colorId);
	
	void deleteByTextureId(Integer textureId);
	
	void deleteBySizeId(Integer sizeId);
	
	
}