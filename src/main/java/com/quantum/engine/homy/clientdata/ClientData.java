package com.quantum.engine.homy.clientdata;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: PhoneData 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月3日 下午7:02:57 
 *
 */
public class ClientData {
	
	private Map<String, Object> values = new HashMap<String, Object>();
    
    private String key = Constants.HOMY_SIGN_KEY;
    
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
	
	public String toUrl(){
        StringBuffer sb = new StringBuffer();
        TreeSet<String> set = new TreeSet<String>(values.keySet());
        for(String key:set){
            Object value = getValue(key);
            if(!"sign".equals(key) && value != null){
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
    
    public boolean checkSign() throws ClientDataException{
        if(!haveKey("sign")){
            throw new ClientDataException("签名不存在!");
        }else if(getValue("sign") == null || getValue("sign").toString().equals(""))
        {
            throw new ClientDataException("签名存在但不合法!");
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

}
