package com.quantum.engine.homy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
	public static final SimpleDateFormat TIMESTAMP_SDF = new SimpleDateFormat("yyyyddMMHHmmss");
	
	public static String getTimestamp(){
		String result = null;
		result = TIMESTAMP_SDF.format(new Date());
		return result;
	}
	
	public static String dateToString(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if(date == null){
			return null;
		}
		String result = null;
		try{
			result = sdf.format(date);
		}catch(Exception e){}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.print(getTimestamp());
	}
	

}
