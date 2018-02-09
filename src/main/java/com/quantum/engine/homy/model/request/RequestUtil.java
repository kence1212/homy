package com.quantum.engine.homy.model.request;

import java.net.URLDecoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.User;

public class RequestUtil {
	
	public static final String CURRENT_USER_SESSION_KEY = "current_user";
	
	public static User getCurrent(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(CURRENT_USER_SESSION_KEY);
		return user;
	}
	
	public static void setCurrent(HttpServletRequest request,User user){
		if(user != null){
			request.getSession().setAttribute(CURRENT_USER_SESSION_KEY, user);
		}
	}
	
	public static boolean isManager(HttpServletRequest request){
		User user = getCurrent(request);
		return isManager(user); 
	}
	
	public static boolean isManager(User user){
		return user != null && user.getGrade() != null && user.getGrade() == ConstantsKey.USER_GRADE_MANAGER;
	}
	
	public static Integer getCurrentUserId(HttpServletRequest request){
		User user = getCurrent(request);
		if(user == null){
			return null;
		}else{
			return user.getUserId();
		}
	}
	
	public static String getContent(HttpServletRequest request){
    	try{
            int contentLength = request.getContentLength();
            byte[] source = new byte[contentLength];
            ServletInputStream in = request.getInputStream();
            in.read(source, 0, contentLength);
            in.close();
            String a = new String(source,"UTF-8");
            String sourceDate = URLDecoder.decode(a, "UTF-8");
            return sourceDate;
        }catch(Exception e){
            return null;
        }
    }

}
