package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.excel.GoodsSkuStockModel;
import com.quantum.engine.homy.model.BizGoodsSku;
import com.quantum.engine.homy.model.dto.BizGoodsSkuDto;
import com.quantum.engine.homy.model.dto.SkuPageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

/**
 * 
 * @author fzz
 * @date 2017年12月1日
 * @description
 */
public interface BizGoodsSkuService {
	
	public BaseResponse add(BizGoodsSkuDto dto);

	public Page<BizGoodsSkuStockExt> getList(SkuPageListQueryDto dto);
	
	public List<BizGoodsSkuStockExt> getByGoodsIdAndCity(Integer goodsId,Integer cityId);
	
	public List<BizGoodsSkuStockExt> getByGoodsId(Integer goodsId);

	public int delete(Integer id);

	public BizGoodsSkuStockExt getById(Integer id);

	public BaseResponse update(BizGoodsSkuDto dto);
	
	public int importSku(List<GoodsSkuStockModel> list,Integer goodsId);

	public int getStockByCityIdAndAttrs(Integer cityId, Integer goodsId, Integer colorId, Integer sizeId, Integer textureId);

	public BizGoodsSkuStockExt getGoodsByGoodsIdAndCity(Integer goodsId, Integer cityId, Integer colorId,
			Integer sizeId, Integer textureId);

}
