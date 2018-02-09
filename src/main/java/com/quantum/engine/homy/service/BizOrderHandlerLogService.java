package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.ext.BizOrderHandlerLogExt;

public interface BizOrderHandlerLogService {

	List<BizOrderHandlerLogExt> getLogByOrderId(Integer id);

}
