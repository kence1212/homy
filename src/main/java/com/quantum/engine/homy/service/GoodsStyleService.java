package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizGoodsStyle;

/**
 * 
 * @ClassName: goodsStyleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月10日 下午2:56:44 
 *
 */
public interface GoodsStyleService {
	
	public List<BizGoodsStyle> listAll();
	
	public void add(BizGoodsStyle goodsStyle);
	
	public int delete(Integer id);
	
	public BizGoodsStyle getById(Integer id);

	public List<Map<String, Object>> getOption();

	public List<Map<String, Object>> getOptionWithOutSelf(Integer id);

	public void update(BizGoodsStyle goodsStyle);
	
	
}
