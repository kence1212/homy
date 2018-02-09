package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BasGoodsBrand;

/**
 * 
 * @ClassName: GoodsBrandService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月10日 下午2:56:44 
 *
 */
public interface GoodsBrandService {
	
	public List<BasGoodsBrand> listAll();
	
	public List<BasGoodsBrand> listIndexHot();
	
	public void add(BasGoodsBrand goodsBrand);
	
	public int delete(Integer id);
	
	public BasGoodsBrand getById(Integer id);
	
	public void update(BasGoodsBrand brand);
	
	public List<BasGoodsBrand> listHave3d(Map<String, Object> params);
	
}
