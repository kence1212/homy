package com.quantum.engine.homy.model.result;

/**
 * 
 * @ClassName: ResultCode 
 * @Description: 结果码 
 * @author nicya
 * @date 2017年11月6日 下午6:59:59 
 *
 */
public class ResultCode {
	
	/**返回正常*/
	public static final String RESULT_CODE_SUCESS = "10000";
	/**返回错误*/
	public static final String RESULT_CODE_WRONG = "40000";
	/**签名为空*/
	public static final String RESULT_CODE_SIGN_NULL = "40003";
	/**签名错误*/
	public static final String RESULT_CODE_SIGN_WRONG = "40004";

}
