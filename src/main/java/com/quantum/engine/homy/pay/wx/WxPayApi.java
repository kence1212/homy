package com.quantum.engine.homy.pay.wx;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.dom4j.DocumentException;

import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.StringHelper;


public class WxPayApi {
    /**
     * 统一下单
     * @param data
     * @return
     * @throws WxPayException
     */
   public static WxPayData unifiedOrder(WxPayData data) throws WxPayException{
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        
        if (!data.haveKey("out_trade_no"))
        {
            throw new WxPayException("缺少统一支付接口必填参数out_trade_no！");
        }
        else if (!data.haveKey("body"))
        {
            throw new WxPayException("缺少统一支付接口必填参数body！");
        }
        else if (!data.haveKey("total_fee"))
        {
            throw new WxPayException("缺少统一支付接口必填参数total_fee！");
        }
        else if (!data.haveKey("trade_type"))
        {
            throw new WxPayException("缺少统一支付接口必填参数trade_type！");
        }

        //关联参数
        if (data.getValue("trade_type").toString().equals("JSAPI") && !data.haveKey("openid"))
        {
            throw new WxPayException("统一支付接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数！");
        }
        if (data.getValue("trade_type").toString().equals("NATIVE") && !data.haveKey("product_id"))
        {
            throw new WxPayException("统一支付接口中，缺少必填参数product_id！trade_type为JSAPI时，product_id为必填参数！");
        }
        
        //异步通知url未设置，则使用配置文件中的url
        if (!data.haveKey("notify_url"))
        {
            data.setValue("notify_url", WxPayConfig.NOTIFY_URL);//异步通知url
        }
        
        if(!data.haveKey("appid")){
        	data.setValue("appid", WxPayConfig.APP_ID);//公众账号ID
        }
        if(!data.haveKey("mch_id")){
        	data.setValue("mch_id", WxPayConfig.MCHID);//商户号
        }
        data.setValue("spbill_create_ip", WxPayConfig.IP);//终端ip            
        data.setValue("nonce_str", MD5FileUtil.getMD5String(StringHelper.getRandomStr(8)));//随机字符串

        //签名
        String sign = data.makeSign();
        data.setValue("sign", sign);
        String xml = data.toXml();
        String rstr = PostUtil.postpay(url, xml);
        WxPayData wp = new WxPayData();
        wp.setKey(data.getKey());
        try {
            wp.fromXml(rstr);
            System.out.println(wp.getValue("return_msg"));
        } catch (DocumentException e) {
            throw new WxPayException("解析错误");
        }
        if(!wp.checkSign()){
            throw new WxPayException("WxPayData签名验证错误!");
        }
        return wp;
    }
    
    /***
     * 查询订单
     * @param inputObj
     * @return
     * @throws WxPayException
     */
    public static WxPayData orderQuery(WxPayData inputObj) throws WxPayException
    {
        //检测必填参数
        if (!inputObj.haveKey("out_trade_no") && !inputObj.haveKey("transaction_id"))
        {
            throw new WxPayException("订单查询接口中，out_trade_no、transaction_id至少填一个！");
        }
        inputObj.setValue("appid", WxPayConfig.APP_ID);//公众账号ID
        inputObj.setValue("mch_id", WxPayConfig.MCHID);//商户号
        WxPayData wp = justOrderQuery(inputObj);
        return wp;
    }
    
    public static WxPayData justOrderQuery(WxPayData inputObj) throws WxPayException {
    	String url = "https://api.mch.weixin.qq.com/pay/orderquery";
    	if (!inputObj.haveKey("out_trade_no") && !inputObj.haveKey("transaction_id"))
        {
            throw new WxPayException("订单查询接口中，out_trade_no、transaction_id至少填一个！");
        }
    	inputObj.setValue("nonce_str", inputObj.generateNonceStr());//随机字符串
        inputObj.setValue("sign", inputObj.makeSign());//签名
        String xml = inputObj.toXml();
        String rstr = PostUtil.postpay(url, xml);
        //将xml格式的数据转化为对象以返回
        WxPayData wp = new WxPayData();
        wp.setKey(inputObj.getKey());
        System.out.println(rstr);
        try {
            wp.fromXml(rstr);
        } catch (DocumentException e) {
            throw new WxPayException("解析错误");
        }
        if(!wp.checkSign()){
            throw new WxPayException("WxPayData签名验证错误!");
        }
        return wp;
    }
    
    //退款
    public static WxPayData refund(WxPayData inputObj) throws Exception {
    	String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    	//加参数
    	if (!inputObj.haveKey("out_trade_no") && !inputObj.haveKey("transaction_id"))
        {
            throw new WxPayException("订单退款接口中，out_trade_no、transaction_id至少填一个！");
        }
    	if(!inputObj.haveKey("out_refund_no")){
    		throw new WxPayException("订单退款接口中必须有商户退款单号！");
    	}
    	if(!inputObj.haveKey("total_fee")){
    		throw new WxPayException("订单退款接口中必须有订单总额！");
    	}
    	if(!inputObj.haveKey("refund_fee")){
    		throw new WxPayException("订单退款接口中必须有退款金额！");
    	}
    	inputObj.setValue("appid", WxPayConfig.APP_ID);//公众账号ID
        inputObj.setValue("mch_id", WxPayConfig.MCHID);//商户号
        inputObj.setValue("nonce_str", inputObj.generateNonceStr());//随机字符串
        inputObj.setValue("sign", inputObj.makeSign());//签名
        String xml = inputObj.toXml();
        //String rstr = PostUtil.postpay(url, xml);
        
        String rstr = PostUtil.requestOnce(url, xml, 10000, 10000, true);
        //将xml格式的数据转化为对象以返回
        WxPayData wp = new WxPayData();
        System.out.println(rstr);
        wp.setKey(inputObj.getKey());
        try {
            wp.fromXml(rstr);
        } catch (DocumentException e) {
            throw new WxPayException("解析错误");
        }
        if(!wp.checkSign()){
            throw new WxPayException("WxPayData签名验证错误!");
        }
        return wp;
    	
    }
    
    //退款查询
    public static WxPayData refundquery(WxPayData inputObj) throws WxPayException {
    	String url = "https://api.mch.weixin.qq.com/pay/refundquery";
    	if (!inputObj.haveKey("out_trade_no") 
    			&& !inputObj.haveKey("transaction_id")
    			&& !inputObj.haveKey("out_trade_no")
    			&& !inputObj.haveKey("refund_id")
    			)
        {
            throw new WxPayException("订单退款查询接口中，out_trade_no、transaction_id、out_trade_no、refund_id至少填一个！");
        }
    	inputObj.setValue("appid", WxPayConfig.APP_ID);//公众账号ID
        inputObj.setValue("mch_id", WxPayConfig.MCHID);//商户号
        inputObj.setValue("nonce_str", inputObj.generateNonceStr());//随机字符串
        inputObj.setValue("sign", inputObj.makeSign());//签名
        String xml = inputObj.toXml();
        String rstr = PostUtil.postpay(url, xml);
        //将xml格式的数据转化为对象以返回
        WxPayData wp = new WxPayData();
        wp.setKey(inputObj.getKey());
        try {
            wp.fromXml(rstr);
        } catch (DocumentException e) {
            throw new WxPayException("解析错误");
        }
        if(!wp.checkSign()){
            throw new WxPayException("WxPayData签名验证错误!");
        }
        return wp;
    }
    
    
    public static String generateOutTradeNo()
    {
        String result =  WxPayConfig.MCHID + DateUtils.formatDate(new Date(),"yyyyMMddHHmmss") + StringHelper.getRandomNum(3);
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(generateOutTradeNo());
    }
    

}
