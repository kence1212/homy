package com.quantum.engine.homy.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.request.RequestUtil;

/**
 * 
 * @author nicya
 * @date 2017年11月22日 下午6:14:45
 */
public class AdminFilter implements Filter{
	
	private String[] checkUris = {"/qem/","/ambrand/","/amgoods/",
			"/amgoodscate/","/ammodel/","/amstyle/","/ampaint/",
			"/amstore/","/aduser/"};

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest)req;
    	String uri = (request).getRequestURI();
    	String contextPath = request.getContextPath();
    	if(contextPath != null && contextPath.length() > 0){
    		uri = uri.substring(contextPath.length());
    	}
    	
        if(mustLogin(uri)){
            User user = RequestUtil.getCurrent(request);
            if(user != null && user.getGrade() == ConstantsKey.USER_GRADE_MANAGER){
                chain.doFilter(req, resp);
            }else{
            	req.getRequestDispatcher("/qem/login.html").forward(req, resp);
            }
        }else{
        	chain.doFilter(req, resp);
        }
    }
    
    private boolean mustLogin(String uri){
    	boolean result = false;
    	for(String checkUri:checkUris){
    		if(uri.startsWith(checkUri)){
    			result = true;
    		}
    	}
    	if(uri.equals("/qem/login.html") 
    			|| uri.equals("/qem/logins.html")){
    		result = false;
    	}
    	return result;
    }
    
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}