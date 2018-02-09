package com.quantum.engine.homy.model.result;

import java.io.Serializable;

public class BaseResponse implements Serializable{
	
	public static BaseResponse getSuccess(String msg){
		BaseResponse br = new BaseResponse();
		br.setResultCode(ResultCode.RESULT_CODE_SUCESS);
		br.setMsg(msg);
		return br;
	} 
	
	public static BaseResponse getSuccess(){
		return getSuccess(null);
	} 
	
	public static BaseResponse getWrong(String msg){
		BaseResponse br = new BaseResponse();
		br.setResultCode(ResultCode.RESULT_CODE_WRONG);
		br.setErrorMsg(msg);
		return br;
	} 
	
	private String resultCode;
	private String msg;
	private String errorMsg;
	private Object obj;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}

}
