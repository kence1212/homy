package com.quantum.engine.homy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: PhoneUtil 
 * @Description: 手机工具类 
 * @author nicya
 * @date 2017年11月6日 下午5:30:17 
 *
 */
public class PhoneUtil {
	
	/**
	 * 校验是否手机号码
	 * @param phone
	 * @return
	 */
	public static boolean checkPhoneNo(String phone){
		if(phone == null || "".equals(phone)){
			return false;
		}
		String regExp = "^1\\d{10}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(phone);  
        return m.matches();  
	}

}
