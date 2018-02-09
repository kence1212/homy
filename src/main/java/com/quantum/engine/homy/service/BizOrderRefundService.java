package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.model.page.Page;

public interface BizOrderRefundService {
	
	public Page<BizOrderRefund> page(int page,int size);
	
	public List<BizOrderRefund> listAll();
	
	public void add(BizOrderRefund refund);
	
	public int delete(Integer id);
	
	public BizOrderRefund getById(Integer id);
	
	public void update(BizOrderRefund refund);

}
