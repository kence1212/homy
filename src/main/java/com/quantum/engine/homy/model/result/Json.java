package com.quantum.engine.homy.model.result;

public class Json {
	
	private Boolean success;
	private String  msg;
	private Object  obj;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public static Json genSuccess(String msg){
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg(msg);
		return json;
	}
	
	public static Json genSuccess(String msg , Object obj){
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg(msg);
		json.setObj(obj);
		return json;
	}

}
