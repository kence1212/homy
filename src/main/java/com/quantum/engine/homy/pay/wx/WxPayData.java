package com.quantum.engine.homy.pay.wx;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.StringHelper;

public class WxPayData {
    
    private Map<String, Object> values = new HashMap<String, Object>();
    
    private String key = WxPayConfig.KEY;
    
    public void setKey(String key){
    	this.key = key;
    }
    
    public String getKey(){
    	return key;
    }
    
    public void setValue(String key , Object value){
        values.put(key, value);
    }
    
    public Object getValue(String key){
        return values.get(key);
    }
    
    public boolean haveKey(String key)
    {
        Object o = null;
        o = values.get(key);
        if (null != o)
            return true;
        else
            return false;
    }
    
    public String toXml()
    {
        //数据为空时不能转化为xml格式
        if (0 == values.size())
        {
            //Log.Error(this.GetType().ToString(), "WxPayData数据为空!");
            //throw new WxPayException("WxPayData数据为空!");
        }

        StringBuffer xml = new StringBuffer("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><xml>");
        for(String key:values.keySet())
        {
            //字段值不能为null，会影响后续流程
            Object value = getValue(key);
            if (getValue(key)==null)
            {
                //Log.Error(this.GetType().ToString(), "WxPayData内部含有值为null的字段!");
                //throw new WxPayException("WxPayData内部含有值为null的字段!");
            }
            xml.append("<").append(key).append(">");
            xml.append("<![CDATA[" + value + "]]>");
            xml.append("</").append(key).append(">");
            {
                //Log.Error(this.GetType().ToString(), "WxPayData字段数据类型错误!");
                //throw new WxPayException("WxPayData字段数据类型错误!");
            }
        }
        xml.append( "</xml>");
        try {
			return new String(xml.toString().getBytes("utf-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return "";
    }
    
    public void fromXml(String xml) throws DocumentException{
        Document document = DocumentHelper.parseText(xml); 
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for(Element e:elements){
            values.put(e.getName(), e.getData());
        }
    }
    
    public String toUrl(){
        StringBuffer sb = new StringBuffer();
        TreeSet<String> set = new TreeSet<String>(values.keySet());
        for(String key:set){
            Object value = getValue(key);
            if(!"sign".equals(key) && value != null && !"".equals(value)){
                sb.append("&");
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
        }
        return sb.substring(1);
    }
    
    public String makeSign()
    {
        //转url格式
        String str = toUrl();
        //在string后加入API KEY
        str += "&key=" + key;
        //MD5加密
        return MD5FileUtil.getMD5String(str).toUpperCase();
    }
    
    public String generateNonceStr(){
        return MD5FileUtil.getMD5String(StringHelper.getRandomStr(8));
    }
    
    public String generateTimeStamp(){
        return System.currentTimeMillis()/1000+"";
    }
    
    public boolean checkSign() throws WxPayException{
        if(!haveKey("sign")){
            throw new WxPayException("WxPayData签名不存在!");
        }else if(getValue("sign") == null || getValue("sign").toString().equals(""))
        {
            throw new WxPayException("WxPayData签名存在但不合法!");
        }
        String return_sign = getValue("sign").toString();
        String cal_sign = makeSign();
        return return_sign.equals(cal_sign);
    }
    
    public Map<String, Object> getValues(){
        return values;
    }
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(String key:values.keySet()){
            sb.append("'"+key+"':" + values.get(key)+",");
        }
        sb.append("}");
        return sb.toString();
    }
    
    public static void main(String[] args) throws DocumentException, WxPayException {
        WxPayData wd = new WxPayData();
        /*wd.setValue("nonce_str", wd.generateNonceStr());
        wd.setValue("product_id", "88888888");
        wd.setValue("appid", WxPayConfig.APP_ID);
        wd.setValue("mch_id", "1264411301");
        wd.setValue("time_stamp", System.currentTimeMillis()/1000);
        wd.setValue("sign", wd.makeSign());
        System.out.println(wd.getValue("sign"));
        System.out.println(wd.toUrl());*/
        String xml = "<xml>" + 
                "<return_code><![CDATA[SUCCESS]]></return_code>" + 
                "<return_msg><![CDATA[OK]]></return_msg>" + 
                "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>" + 
                "<mch_id><![CDATA[10000100]]></mch_id>" + 
                "<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" + 
                "<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" + 
                "<result_code><![CDATA[SUCCESS]]></result_code>" + 
                "<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" + 
                "<trade_type><![CDATA[JSAPI]]></trade_type>" + 
                "</xml> ";
        wd.fromXml(xml);
        System.out.println(wd.getValue("sign"));
        System.out.println(wd.makeSign());
        System.out.println(wd.checkSign());
        System.out.println(wd);
    }
    
}
