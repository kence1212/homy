package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.dao.BasImageMapper;
import com.quantum.engine.homy.dao.BizOrderHandlerLogMapper;
import com.quantum.engine.homy.dao.BizOrderItemMapper;
import com.quantum.engine.homy.dao.BizOrderMapper;
import com.quantum.engine.homy.dao.BizOrderPayInfoMapper;
import com.quantum.engine.homy.dao.BizOrderRefundMapper;
import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.BizOrderHandlerLog;
import com.quantum.engine.homy.model.BizOrderPayInfo;
import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.pay.ali.AliPayService;
import com.quantum.engine.homy.pay.ali.param.AlipayAppPayParam;
import com.quantum.engine.homy.pay.ali.param.AlipayQueryParam;
import com.quantum.engine.homy.pay.ali.param.AlipayRefundParam;
import com.quantum.engine.homy.pay.wx.WxPayApi;
import com.quantum.engine.homy.pay.wx.WxPayData;
import com.quantum.engine.homy.pay.wx.WxPayException;
import com.quantum.engine.homy.service.BizOrderService;
import com.quantum.engine.homy.service.PayService;
import com.quantum.engine.homy.util.ListHelper;
import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.OrderUtil;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @author nicya
 * @date 2017年12月29日 下午5:38:28
 */
@Service("payService")
public class PayServiceImpl implements PayService{

	@Autowired
	private AliPayService alipayService;
	@Autowired
	private BizOrderMapper bizOrderMapper;
	@Autowired
	private BizOrderItemMapper bizOrderItemMapper;
	@Autowired
	private BizOrderService bizOrderService;
	@Autowired
	private BizOrderPayInfoMapper bizOrderPayInfoMapper;
	@Autowired
	private BizOrderHandlerLogMapper bizOrderHandlerLogMapper;
	@Autowired
	private BizOrderRefundMapper bizOrderRefundMapper;
	@Autowired
	private BasImageMapper basImageMapper;
	
	@Override
	public Map<String, Object> getAppPayData(String orderIds,int type,Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringHelper.isNull(orderIds)){
			result.put("error", "订单错误");
			return result;
		}
		String outTradeNo = bizOrderService.getOnlyOrderTradeNo();
		String body = "Homy家具支付";
		String subject = "Homy家具支付";
		double totalAmount = 0.00;
		List<Integer> orderIdList = ListHelper.getIdList(orderIds);
		for(Integer orderId:orderIdList){
			BizOrder order = bizOrderMapper.selectByPrimaryKey(orderId);
			if(order.getState() == OrderUtil.ORDER_STATE_INIT){
				totalAmount += order.getTotalPrice();
				order.setOutTradeNo(outTradeNo);
				order.setPayType(type);
				bizOrderMapper.updateByPrimaryKey(order);
			}
		}
		//支付表插入
		// orderids , outTradeNo , create_time , state , type , trade_no , payed_time
		BizOrderPayInfo info = new BizOrderPayInfo();
		info.setCreateTime(new Date());
		info.setOrderIds(orderIds);
		info.setOutTradeNo(outTradeNo);
		info.setState(BizOrderPayInfo.PAY_STATE_INIT);
		info.setTotalAmount(totalAmount);
		info.setUserId(userId);
		info.setPayType(type);
		bizOrderPayInfoMapper.insertSelective(info);
		
		if(Constants.PAY_TYPE_ALIPAY == type){
			AlipayAppPayParam param = new AlipayAppPayParam();
			param.setBody(body);
			param.setOutTradeNo(outTradeNo);
			param.setSubject(subject);
			param.setTimeoutExpress("30m");
			param.setTotalAmount(totalAmount+"");
			String payString = alipayService.getAppPay(param);
			result.put("payString", payString);
		}else if(Constants.PAY_TYPE_WXPAY == type){
			WxPayData data = new WxPayData();
			try {
				data.setValue("out_trade_no", outTradeNo);
				data.setValue("body", body);
				data.setValue("total_fee", (int)(totalAmount*100));
				data.setValue("trade_type", "APP");
				WxPayData resultData = WxPayApi.unifiedOrder(data);
				WxPayData checkData = new WxPayData();
				checkData.setValue("appid", resultData.getValue("appid"));
				checkData.setValue("partnerid", resultData.getValue("mch_id"));
				checkData.setValue("prepayid", resultData.getValue("prepay_id"));
				checkData.setValue("noncestr", MD5FileUtil.getMD5String(StringHelper.getRandomStr(8)));
				checkData.setValue("timestamp", System.currentTimeMillis() / 1000);
				checkData.setValue("package", "Sign=WXPay");
				checkData.setValue("sign", checkData.makeSign());
				result = checkData.getValues();
			} catch (WxPayException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public void exeAutoPayedOrder(){
		//列出需要查询的     state = 1 时间在三十分钟以内
		Date date = new Date(System.currentTimeMillis() - 1000*60*30);
		List<BizOrderPayInfo> list = bizOrderPayInfoMapper.listAutoQuery(BizOrderPayInfo.PAY_STATE_INIT, date);
		if(list != null & list.size() > 0 ){
			for(BizOrderPayInfo info:list){
				exePayedOrderByOutTradeNo(info.getOutTradeNo());
			}
		}
	}
	
	//根据后台交易号的查询
	public void exePayedOrderByOutTradeNo(String outTradeNo){
		//处理已支付的订单 根据outTradeNo
		if(StringHelper.isNotNull(outTradeNo)){
			BizOrderPayInfo info  = bizOrderPayInfoMapper.getByOutTradeNo(outTradeNo);
			if(info != null){
				Integer type = info.getPayType();
				if(type == Constants.PAY_TYPE_ALIPAY){
					//支付宝处理
					exeAli(outTradeNo,info);
				}else if(type == Constants.PAY_TYPE_WXPAY){
					//微信处理
					exeWx(outTradeNo,info);
				}
			}
		}
	}
	
	@Override
	public String cancelRefundService(Integer orderId,Integer userId){
		String error = null;
		BizOrder order = bizOrderMapper.selectByPrimaryKey(orderId);
		if(order == null){
			error = "订单号错误";
			return error;
		}
		if(!order.getUserId() .equals(userId)){
			error = "只能取消自己的订单";
			return error;
		}
		if(order.getState() != OrderUtil.ORDER_STATE_BACK){
			error = "必须是退货订单才能取消退货服务";
			return error;
		}
		BizOrderRefund checkBean = bizOrderRefundMapper.getByOrderId(orderId);
		if(checkBean == null){
			error = "该订单尚未申请退货";
			return error;
		}
		if(!checkBean.getState().equals(BizOrderRefund.STATE_INIT)){
			error = "该订单退货服务不能取消";
			return error;
		}
		
		order.setState(OrderUtil.ORDER_STATE_RECEIVE);
		bizOrderMapper.updateByPrimaryKey(order);
		
		BizOrderHandlerLog bizOrderHandlerLog = new BizOrderHandlerLog();
		bizOrderHandlerLog.setFinOrderState(OrderUtil.ORDER_STATE_RECEIVE);
		bizOrderHandlerLog.setOrderId(order.getId());
		bizOrderHandlerLog.setRecordTime(new Date());
		bizOrderHandlerLog.setUserId(userId);
		bizOrderHandlerLog.setPreOrderState(OrderUtil.ORDER_STATE_BACK);
		bizOrderHandlerLogMapper.insert(bizOrderHandlerLog);
		
		checkBean.setIsValid(0);
		checkBean.setState(BizOrderRefund.STATE_CANCEL);
		bizOrderRefundMapper.updateByPrimaryKey(checkBean);
		return error;
		
	}
	
	/**
	 * 申请退款
	 */
	public String applyRefund(List<String> imgUrls,BizOrderRefund orderRefund){
		String error = null;
		BizOrder order = bizOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
		if(order == null){
			error = "订单号错误";
			return error;
		}
		if(order.getState() != OrderUtil.ORDER_STATE_RECEIVE){
			error = "必须是已收货订单才能退货";
			return error;
		}
		BizOrderRefund checkBean = bizOrderRefundMapper.getByOrderId(orderRefund.getOrderId());
		if(checkBean != null){
			error = "该订单已申请退货";
			return error;
		}
		order.setState(OrderUtil.ORDER_STATE_BACK);
		bizOrderMapper.updateByPrimaryKey(order);
		BizOrderHandlerLog bizOrderHandlerLog = new BizOrderHandlerLog();
		bizOrderHandlerLog.setFinOrderState(OrderUtil.ORDER_STATE_BACK);
		bizOrderHandlerLog.setOrderId(order.getId());
		bizOrderHandlerLog.setRecordTime(new Date());
		bizOrderHandlerLog.setUserId(orderRefund.getCreateId());
		bizOrderHandlerLog.setPreOrderState(OrderUtil.ORDER_STATE_RECEIVE);
		bizOrderHandlerLogMapper.insert(bizOrderHandlerLog);
		
		orderRefund.setRefundAmount(order.getTotalPrice());
		bizOrderRefundMapper.insert(orderRefund);
		if(imgUrls != null && imgUrls.size() > 0){
			for(String img:imgUrls){
				if(StringHelper.isNotNull(img)){
					BasImage image = new BasImage();
					image.setCreateId(orderRefund.getCreateId());
					image.setCreateTime(new Date());
					image.setType(BasImage.IMAGE_TYPE_REFUND);
					image.setImagePath(img);
					image.setRefundId(orderRefund.getId());
					image.setSuffix(StringHelper.getSuffix(img));
					basImageMapper.insert(image);
				}
			}
		}
		return error;
	}
	
	@Override
	public BizOrderRefund getRefundInfoByOrderId(Integer orderId){
		return bizOrderRefundMapper.getByOrderId(orderId);
	}
	
	//退款
	public String refund(Integer orderId,Integer userId){
		//TODO
		BizOrder order = bizOrderMapper.selectByPrimaryKey(orderId);
		//只有订单在支付后的状态才能退款
		if(order != null && order.getState() >= OrderUtil.ORDER_STATE_PAYED 
				&& order.getState() != OrderUtil.ORDER_STATE_REFUND //已经退款
				&& order.getState() != OrderUtil.ORDER_STATE_FINISH){
			BizOrderPayInfo info  = bizOrderPayInfoMapper.getByOutTradeNo(order.getOutTradeNo());
			if(info != null){
				int type = info.getPayType();
				double amount = order.getTotalPrice();
				double totalFee = info.getTotalAmount();
				String outTradeNo = info.getOutTradeNo();
				String outRequestNo = "";//TODO
				if(type == Constants.PAY_TYPE_ALIPAY){
					//支付宝退款
					refundAli(amount, outTradeNo, outRequestNo);
				}else if(type == Constants.PAY_TYPE_WXPAY){
					//微信退款
					try {
						refundWx(amount, outTradeNo, outRequestNo, totalFee);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
			}
		}
		return null;
	}
	
	//微信支付操作
	private void exeWx(String outTradeNo,BizOrderPayInfo info){
		WxPayData wd = new WxPayData();
		wd.setValue("out_trade_no", outTradeNo);
		try {
			WxPayData wdResult = WxPayApi.orderQuery(wd);
			if("SUCCESS".equals( wdResult.getValue("return_code") )
					&& "SUCCESS".equals( wdResult.getValue("result_code") ) ){
				String tradeNo = (String)wdResult.getValue("transaction_id");
				String payAppUserId = (String)wdResult.getValue("openid");
				exeOrder(info, info.getOrderIds(), tradeNo, payAppUserId);
			}
		} catch (WxPayException e) {
			e.printStackTrace();
		}
	}
	
	//支付宝支付操作
	private void exeAli(String outTradeNo,BizOrderPayInfo info){
		AlipayQueryParam param = new AlipayQueryParam();
		param.setOut_trade_no(outTradeNo);
		String result = alipayService.query(param);
		JsonParser parse =new JsonParser();
		JsonObject obj =  (JsonObject)parse.parse(result);
		JsonObject response = (JsonObject)obj.get("alipay_trade_query_response");
		String resultCode = response.get("code").getAsString();
		if("10000".equals(resultCode)){
			String status = response.get("trade_status").getAsString();
			if("TRADE_SUCCESS".equals(status)){
				String tradeNo = response.get("trade_no").getAsString();
				String payAppUserId = response.get("buyer_user_id").getAsString();
				exeOrder(info, info.getOrderIds(), tradeNo, payAppUserId);
			}
		}else{
			String errorStr = response.get("sub_msg").getAsString();
		}
	}
	
	//支付成功后的订单处理
	private void exeOrder(BizOrderPayInfo info,String orderIds,String tradeNo,String payAppUserId){
		List<Integer> orderIdList = ListHelper.getIdList(orderIds);
		for(Integer orderId:orderIdList){
			BizOrder order = bizOrderMapper.selectByPrimaryKey(orderId);
			if(order != null && order.getState() == OrderUtil.ORDER_STATE_INIT ){
				order.setState(OrderUtil.ORDER_STATE_PAYED);
				order.setPayTime(new Date());
				bizOrderMapper.updateByPrimaryKeySelective(order);
				BizOrderHandlerLog log = new BizOrderHandlerLog();
				log.setFinOrderState(OrderUtil.ORDER_STATE_PAYED);
				log.setOrderId(orderId);
				log.setPreOrderState(OrderUtil.ORDER_STATE_INIT);
				log.setRecordTime(new Date());
				log.setUserId(order.getUserId());
				bizOrderHandlerLogMapper.insertSelective(log);
				bizOrderItemMapper.updateStateByOrderId(order.getId(), OrderUtil.ORDER_STATE_PAYED);
			}
			info.setTradeNo(tradeNo);
			info.setPayAppUserId(payAppUserId);
			info.setState(BizOrderPayInfo.PAY_STATE_PAYED);
			info.setPayedTime(new Date());
			bizOrderPayInfoMapper.updateByPrimaryKey(info);
		}
	}
	
	//支付宝退款
	private void refundAli(Double amount,String outTradeNo,String outRequestNo){
		AlipayRefundParam param = new AlipayRefundParam();
		param.setRefund_amount(amount);
		param.setOut_trade_no(outTradeNo);
		param.setOut_request_no(outRequestNo);
		String result = alipayService.refund(param);
		//TODO
		System.out.println(result);
	}
	
	//微信退款
	private void refundWx(Double amount,String outTradeNo,String outRequestNo,Double totalFee) throws Exception{
		WxPayData wd = new WxPayData();
		wd.setValue("out_trade_no", outTradeNo);
		wd.setValue("out_refund_no", outRequestNo);
		wd.setValue("total_fee", (int)(totalFee * 100));
		wd.setValue("refund_fee", (int)(amount * 100));
		try {
			WxPayData wdResult = WxPayApi.refund(wd);
			if("SUCCESS".equals( wdResult.getValue("return_code") )
					&& "SUCCESS".equals( wdResult.getValue("result_code") ) ){
				//TODO
				System.out.println(wdResult.toXml());
			}
		} catch (WxPayException e) {
			e.printStackTrace();
		}
	}
	
}
