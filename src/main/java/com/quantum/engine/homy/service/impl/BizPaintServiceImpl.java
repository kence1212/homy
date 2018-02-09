/**
 * 
 */
package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizPaintMapper;
import com.quantum.engine.homy.model.BizPaint;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.service.BizPaintService;

/**
 * @author nicya
 * @date 2017��11��16�� ����10:37:57
 */
@Service("bizPaintService")
public class BizPaintServiceImpl implements BizPaintService {
	
	@Autowired
	private BizPaintMapper mapper;

	@Override
	public BizPaint getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BizPaint> getAll() {
		return mapper.getAll();
	}

	@Override
	public void add(BizPaint bizPaint) {
		mapper.insert(bizPaint);
	}

	@Override
	public void update(BizPaint bizPaint) {
		if(bizPaint != null && bizPaint.getId() != null){
			BizPaint source = mapper.selectByPrimaryKey(bizPaint.getId());
			if(bizPaint != null){
				source.setModifyTime(new Date());
				source.setModfiyId(bizPaint.getModfiyId());
				source.setPaintCode(bizPaint.getPaintCode());
				source.setPaintDesc(bizPaint.getPaintDesc());
				source.setPaintName(bizPaint.getPaintName());
				mapper.updateByPrimaryKey(source);
			}
		}
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<BizPaint> page(Integer pageNum, Integer pageSize,
			Map<String, Object> params) {
		if(params == null){
			params = new HashMap<String, Object>();
		}
		if(pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0){
			params.put("pageNum", pageNum);
			params.put("pageSize", pageSize);
		}
		return page(params);
	}

	@Override
	public Page<BizPaint> page(Map<String, Object> params) {
		if(params == null){
			params = new HashMap<String, Object>();
		}
		//TODO
		return null;
	}

}
