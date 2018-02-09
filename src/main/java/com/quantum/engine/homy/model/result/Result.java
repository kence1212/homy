package com.quantum.engine.homy.model.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: Result 
 * @Description: 返回结果基础类 
 * @author nicya
 * @date 2017年11月6日 下午3:22:45 
 *
 */
public class Result implements Serializable {
	

	public static final String SUCCESS_RESULT_CODE = "10000";
	
	private Map<String, Object> values = new HashMap<String, Object>();
	
	public Map<String, Object> getValues(){
		return values;
	}
	
	public Object getKey(String key){
		return values.get(key);
	}
	
	public void putValue(String key ,Object value){
		values.put(key, value);
	}
	
	public void removeValue(String key){
		Object value = values.get(key);
		if(value != null){
			values.remove(key);
		}
	}
	
	public void wrong(String errorCode,String errorMsg){
		values.put("resultCode", errorCode );
		values.put("errorMsg",errorMsg);
	}
	
	public void wrong(String errorMsg){
		wrong("40000",errorMsg);
	}
	
	public void success(String msg){
		values.put("resultCode", ResultCode.RESULT_CODE_SUCESS );
		values.put("msg",msg);
	}
	
}
