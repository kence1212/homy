package com.quantum.engine.homy.util;
/**
 * 密码工具类 
 * @author nicya
 * @date 2015年9月1日 下午7:38:55
 */
public class PasswordUtil {
    
    /**
     * 密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String password,String salt){
        return MD5FileUtil.getMD5String(password + salt);
    }
    
    /**
     * 密码校验
     * @param password 明文密码
     * @param salt  明文盐
     * @param md5pwd    加密后的密码
     * @return
     */
    public static boolean checkPassword(String password,String salt,String md5pwd){
        String encryptPwd = encrypt(password, salt);
        return encryptPwd.equals(md5pwd);
    }
    
    public static void main(String[] args) {
    	String abc = MD5FileUtil.getMD5String("123456").toUpperCase();
    	String password = encrypt(abc, "abcdef");
    	System.out.println(password);
	}
    
}
