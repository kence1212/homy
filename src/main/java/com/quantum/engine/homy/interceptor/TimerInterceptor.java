package com.quantum.engine.homy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TimerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(TimerInterceptor.class);

	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"WatchExecuteTime");

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		long beginTime = System.currentTimeMillis(); // 开始时间
		startTimeThreadLocal.set(beginTime);
		return true;
	}

	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTimeThreadLocal.get();
		logger.info(String.format("%s execute %d ms.",
				req.getRequestURI(), executeTime));
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

}
