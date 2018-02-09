package com.quantum.engine.homy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

/**
 * 
 * @ClassName: goodsModelService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月10日 下午2:56:44 
 *
 */
public interface GoodsModelService {
	
	public List<BizGoodsModel> listAll();
	
	public BaseResponse add(BizGoodsModel goodsModel, HttpServletRequest request);
	
	public int delete(Integer id);
	
	public BizGoodsModelExt getById(Integer id);
	
	public void updateState(BizGoodsModel goodsModel);

	public BaseResponse update(BizGoodsModel goodsModel, HttpServletRequest request);

	public List<BizGoodsModelExt> listAllWithGood();
	
	public List<BizGoodsModel> getByGoodsId(Integer goodsId);

	public Page<BizGoodsModelExt> getList(PageListQueryDto dto);
	
}
