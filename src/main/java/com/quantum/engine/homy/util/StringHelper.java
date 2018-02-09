package com.quantum.engine.homy.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * String帮助类，处理系统中所有的字符
 */
public final class StringHelper {
	
	public static boolean FLAG = false;//并发
	//验证码所出现的数字与字母
	public static char[] code = {'a','b','c','d','e','f','g','h','i','j','k','m','n','p','r','s','t','u','v',
		'w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','Q','R','S','T','U','V','W','X','Y',
		'Z','2','3','4','5','6','7','8','9'};
	public static char[] numCode = {'1','2','3','4','5','6','7','8','9','0'};
	
	/**
	 * 判断字符串是null对象或空字符串,或字符串中只存在空白字
	 * @param s（需要判断的字符串）
	 * @return 成功返回true
	 */
	public static boolean isNull(String s) {
		if(!hasLength(s)){
		    return true;
		}
		int strLen = s.length();
		for(int i = 0 ; i < strLen ; i++){
		    if(!Character.isWhitespace(s.charAt(i))){
		        return false;
		    }
		}
		return true;
	}
	
	public static boolean hasLength(String str){
	    return str != null && str.length() > 0 ;
	}
	
	public static String  toNotNullString(String [] s) {
		StringBuffer newStr = new StringBuffer();
		if(s != null && s.length > 0 ){
			for(String str:s){
				if(StringHelper.isNotNull(str))
					newStr.append(",").append(str);
			}
			if(newStr.length()>1)
				return newStr.substring(1);
			else
				return newStr.toString();
		}else{
			if(s != null)
				return s.toString();
			else
				return null;
		}
		
	}
	public static int  subStrCount(String allString,String subStr) {
		
		StringBuffer newStr = new StringBuffer(allString);
		 int count = 0;
		   int m = newStr.indexOf(subStr);
		   while (m != -1) {
		    m = newStr.indexOf(subStr, m + 1);
		    count++;
		   }
		   return count;
	}
	public static String[]  toStringArray(String  s,String regex) {
		if(s != null && s.indexOf(regex) != -1 ){
			 return s.split(regex);
		}else{
			if(s != null)
				return new String[]{s};
			else
				return null;
		}
		
	}

	
	public static boolean isNotNull(String s) {
		FLAG = false;
		if (!(s == null || "".equals(s) || "null".equalsIgnoreCase(s))) {
			FLAG = true;
		}else{
			FLAG = false;
		}
		return FLAG;
	}

	/**
	 * 判断字符串是32位ID
	 * @param s（需要判断的字符串）
	 * @return 成功返回true
	 */
	public static boolean isId(String s) {
		FLAG = false;
		if (isNotNull(s)) {
			String str = s.trim();
			if (str.length() == 32) {
				FLAG = true;
			}else{
				FLAG = false;
			}
		}else{
			FLAG = false;
		}
		return FLAG;
	}
	
	public static String nullToString(Object object){
		if(object == null){
			return "";
		}
		if("null".equalsIgnoreCase(object.toString())){
			return "";
		}
		return object.toString();
	}
	public static String nullToString(Object object,String initVal){
		if(object == null){
			return initVal;
		}
		if("null".equalsIgnoreCase(object.toString())){
			return initVal;
		}
		return object.toString();
	}
	
	public static String getRandomStr(int length) {
		StringBuffer psd = new StringBuffer();
		char c;
		for (int j = 0; j < length; j++) {
			c = code[(int)(Math.random()*code.length)/1];
			psd.append(c);
		}
		return psd.toString();
	}
	
	public static String getRandomNum(int length) {
        StringBuffer psd = new StringBuffer();
        char c;
        for (int j = 0; j < length; j++) {
            c = numCode[(int)(Math.random()*numCode.length)/1];
            psd.append(c);
        }
        return psd.toString();
    }
	
	public static ArrayList<String> stringToArrayList(String strIn, String strDim) {
		strIn = nullToString(strIn);
		strDim = nullToString(strDim);
		ArrayList<String> strList = new ArrayList<String>();
		for (StringTokenizer strtoken = new StringTokenizer(strIn, strDim, false); strtoken.hasMoreTokens(); 
			strList.add(strtoken.nextToken()));
		strList.remove("");
		return strList;
	}
	
	public static String getExtensionName(String fileName){
		String extensionName = "";
		if(fileName.lastIndexOf(".") != -1){
			extensionName = fileName.substring(fileName.lastIndexOf("."));
		}
		return extensionName;
	}
	
	public static String replaceString(String strSource, String strFrom, String strTo) {
		if (strSource == null)
			return null;
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char cSrc[] = strSource.toCharArray();
			char cTo[] = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j;
			for (j = i; (i = strSource.indexOf(strFrom, i)) > 0; j = i) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		} else {
			return strSource;
		}
	}
	
	public static String formatMutiIds(String ids) {
		StringBuffer sb = new StringBuffer("");
		if(isNotNull(ids) &&  ids.indexOf(",")>-1){
			@SuppressWarnings("rawtypes")
			ArrayList listOfId = stringToArrayList(ids,",");
			for (int i = 0; i < listOfId.size(); i++) {
				String id = nullToString(String.valueOf(listOfId.get(i))).trim();
				if(i==0){
					sb.append("'").append(id).append("'");
				}else{
					sb.append(",'").append(id).append("'");
				}
			}
		}else{
			sb.append("'").append(ids).append("'");
		}
		
		return sb.toString();
	}
	public static String formatMutiIds(String... ids) {
		StringBuffer sb = new StringBuffer("");
		if(ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				String id = nullToString(String.valueOf(ids[i])).trim();
				if(i==0){
					sb.append("'").append(id).append("'");
				}else{
					sb.append(",'").append(id).append("'");
				}
			}
		}else{
			sb.append("'").append(ids).append("'");
		}
		return sb.toString();
	}
	
	public static String formatMutiIds(List<String> list) {
		StringBuffer ids = new StringBuffer();
		if(ListHelper.isNotNull(list)){
			for(int i = 0 ; i < list.size(); i++){
				String id = list.get(i);
				if(StringHelper.isNotNull(id)){
					if(i == 0){
						ids.append("'").append(id).append("'");
					}else{
						ids.append(",").append("'").append(id).append("'");
					}
				}
			}
		}
		return ids.toString();
		
	}
	
	public static String formatDate(Date date,String formula){
		if(date == null){
			return "";
		}
		if(isNull(formula)){
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(formula);
		String dateStr =sf.format(date);
		return dateStr;
	}
	
	public static String removeRepeatStr(String params){
		String[] array = params.split(",");
		StringBuffer strs = new StringBuffer();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (!list.contains(array[i])) {
				list.add(array[i]);
				if(isNotNull(strs.toString())){
					strs.append(",").append(array[i]);
				}else{
					strs.append(array[i]);
				}
			}
		}   
		return strs.toString();  
	}
	
	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		}
		return true;
	}
	public static String trim(String str){
		if(str != null){
			return str.trim();
		}
		return null;
	}
	
	public static String getRand(){
		Random random = new Random();
		   String sRand = "";
		   for(int i= 0;i<6;i++){
			   if(i%2==0){
				   int itmp = random.nextInt(26) + 65;//A~Z中间的字
				    char ctmp = (char) itmp;
				    sRand += String.valueOf(ctmp);
			   }else{
				   int itmp = random.nextInt(10) + 48; // 生成0~9的数
					 char ctmp = (char) itmp;
					 sRand += String.valueOf(ctmp);
			   }
		   }
           return sRand;
	}
	/**
	 * 产生流水号，�?��时间仔细看一下，暂时截取毫秒的组�?
	 * @return
	 */
	public static String getSerialNumber(){
//	    String a = (String.valueOf(System.currentTimeMillis())).substring(3, 13);
//      String d = (String.valueOf(Math.random())).substring(2, 8);
		String a = (String.valueOf(System.currentTimeMillis())).substring(10, 13);
        String d = (String.valueOf(Math.random())).substring(5, 8);
        return Long.parseLong(a + d)+"";
	}
	
	
	public static boolean beyongLength(String code , int limitLength){
	    if(code != null && code.length() > limitLength){
            return true;
        }
        return false;
	}
	
	public static boolean beyongLength(int intt , int limitLength){
	    String code = Integer.toString(intt);
        if(code != null && code.length() > limitLength){
            return true;
        }
        return false;
    }
	
	public static String collectionToStr(Collection<String> strc){
		StringBuffer sb = new StringBuffer();
		sb.append(",");
		if(strc != null){
			for(String str:strc){
				sb.append(str);
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	public static String timePlayCountIdentification(){
		String code = System.currentTimeMillis() + getRandomStr(6) ;
		return GetBigFileMD5.MD5(code);
	}
	
	
	public static String fillNullString(String str){
		return str == null?"":str;
	}
	
	public static String getSuffix(String path){
		if(isNotNull(path) && path.indexOf(".") > 0){
			return path.substring(path.lastIndexOf("."));
		}else{
			return "";
		}
	}
	
	public static void main(String[] args) {
	    
	    System.out.println(getSuffix("abc.png"));
	    
	}
}
