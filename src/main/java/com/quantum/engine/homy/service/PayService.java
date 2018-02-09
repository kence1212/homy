package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizOrderRefund;

/**
 * 支付服务类
 * @author nicya
 * @date 2017年12月29日 下午5:35:03
 */
public interface PayService {
	
	/**
	 * 获取APP支付数据
	 * @param orderIds  订单集
	 * @param type  0表示支付宝 1表示微信支付
	 * @return
	 */
	public Map<String, Object> getAppPayData(String orderIds,int type,Integer userId);
	
	public void exeAutoPayedOrder();
	
	public void exePayedOrderByOutTradeNo(String outTradeNo);
	
	/**
	 * 退款
	 * @param orderId
	 */
	public String refund(Integer orderId,Integer userId);
	
	public String applyRefund(List<String> imgUrls,BizOrderRefund orderRefund);
	
	public String cancelRefundService(Integer orderId,Integer userId);
	
	public BizOrderRefund getRefundInfoByOrderId(Integer orderId);
	
}
