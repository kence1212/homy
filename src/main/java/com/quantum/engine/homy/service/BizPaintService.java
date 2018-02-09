package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizPaint;
import com.quantum.engine.homy.model.page.Page;

/**
 * 
 * @author nicya
 * @date 2017年11月16日 下午2:02:54
 */
public interface BizPaintService {
	
	public BizPaint getById(Integer id);
	
	public List<BizPaint> getAll();
	
	public void add(BizPaint bizPaint);
	
	public void update(BizPaint bizPaint);
	
	public void delete(Integer id);
	
	public Page<BizPaint> page(Integer pageNum,Integer pageSize , Map<String, Object> params);
	
	public Page<BizPaint> page(Map<String, Object> params);

}
