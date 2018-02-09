package com.quantum.engine.homy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizOrderRefundMapper;
import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.service.BizOrderRefundService;

/**
 * 
 * @author nicya
 * @date 2018年1月12日 下午3:15:18
 */
@Service("bizOrderRefundService")
public class BizOrderRefundServiceImpl implements BizOrderRefundService {
	
	@Autowired
	private BizOrderRefundMapper bizOrderRefundMapper;

	@Override
	public Page<BizOrderRefund> page(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(page > 0 && size > 0){
			params.put("page", (page - 1) * size);
			params.put("size", size);
		}
		int totalCount = bizOrderRefundMapper.count(params);
		List<BizOrderRefund> list = bizOrderRefundMapper.list(params);
		Page<BizOrderRefund> pageList = new Page<BizOrderRefund>(page, size);
		pageList.setTotalCount(totalCount);
		pageList.setList(list);
		return pageList;
	}

	@Override
	public List<BizOrderRefund> listAll() {
		return bizOrderRefundMapper.list(null);
	}

	@Override
	public void add(BizOrderRefund refund) {
		if(refund != null){
			bizOrderRefundMapper.insert(refund);
		} 

	}

	@Override
	public int delete(Integer id) {
		return bizOrderRefundMapper.deleteByPrimaryKey(id);
	}

	@Override
	public BizOrderRefund getById(Integer id) {
		return bizOrderRefundMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(BizOrderRefund refund) {
		bizOrderRefundMapper.updateByPrimaryKey(refund);
	}

}
