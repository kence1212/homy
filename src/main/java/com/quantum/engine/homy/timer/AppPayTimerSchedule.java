package com.quantum.engine.homy.timer;


import java.util.Date;

import org.apache.log4j.Logger;

import com.quantum.engine.homy.service.PayService;
import com.quantum.engine.homy.util.DateUtil;

public class AppPayTimerSchedule {
	
	private static final Logger logger = Logger.getLogger(AppPayTimerSchedule.class);
	
	private PayService payService;
	
	public void execute(){
		logger.info("执行时间：" + DateUtil.dateToString(new Date(), "yyyyMMdd HH:mm:ss"));
		payService.exeAutoPayedOrder();
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}
}
