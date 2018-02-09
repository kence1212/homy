package com.quantum.engine.homy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizOrderHandlerLogMapper;
import com.quantum.engine.homy.model.ext.BizOrderHandlerLogExt;
import com.quantum.engine.homy.service.BizOrderHandlerLogService;

@Service
public class BizOrderHandlerLogServiceImpl implements BizOrderHandlerLogService {

	@Autowired
	private BizOrderHandlerLogMapper mapper;
	
	@Override
	public List<BizOrderHandlerLogExt> getLogByOrderId(Integer id) {
		
		return mapper.getLogByOrderId(id);
	}

}
