package com.quantum.engine.homy.pay.ali;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.Gson;
import com.quantum.engine.homy.pay.ali.param.AlipayAppPayParam;
import com.quantum.engine.homy.pay.ali.param.AlipayQueryParam;
import com.quantum.engine.homy.pay.ali.param.AlipayRefundParam;
import com.quantum.engine.homy.pay.ali.param.AlipayRefundQueryParam;
import com.quantum.engine.homy.pay.ali.response.AlipayQueryResponse;

@Service("aliPayService")
public class AliPayService{ 
	
	private AlipayClient alipayClient = 
			new DefaultAlipayClient(
					"https://openapi.alipay.com/gateway.do", 
					AliConfig.APP_ID, 
					AliConfig.APP_PRIVATE_KEY, 
					"json", 
					AliConfig.CHARSET, 
					AliConfig.ALIPAY_PUBLIC_KEY, 
					"RSA2");
	
	public String getAppPay(AlipayAppPayParam param){
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(param.getBody());
		model.setSubject(param.getSubject());
		model.setOutTradeNo(param.getOutTradeNo());
		model.setTimeoutExpress(param.getTimeoutExpress());
		model.setTotalAmount(param.getTotalAmount());
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AliConfig.NOTIFY_URL);
		try {
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        String result =  response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
	        return result;
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	        return null;
		}
	}
	
	/**
	 * 查询
	 * @param param
	 * @return
	 */
	public String query(AlipayQueryParam param){
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
		request.setBizContent(new Gson().toJson(param));//设置业务参数
		AlipayTradeQueryResponse response;
		try {
			response = alipayClient.execute(request);
			String result = response.getBody();
			//AlipayQueryResponse res = new Gson().fromJson(result, AlipayQueryResponse.class); 
			return result;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 退款
	 * @param param
	 * @return
	 */
	public String refund(AlipayRefundParam param){
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
		request.setBizContent(new Gson().toJson(param));//设置业务参数
		AlipayTradeRefundResponse response;
		try {
			response = alipayClient.execute(request);
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 退款查询接口
	 * @return
	 */
	public String refundQuery(AlipayRefundQueryParam param){
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizContent(new Gson().toJson(param));//设置业务参数
		AlipayTradeFastpayRefundQueryResponse response;
		try {
			response = alipayClient.execute(request);
			if(response.isSuccess()){
				return response.getBody();
			}else{
				return null;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
