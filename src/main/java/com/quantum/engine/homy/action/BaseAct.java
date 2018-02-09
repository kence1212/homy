package com.quantum.engine.homy.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: BaseAct 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月3日 下午7:02:38 
 *
 */
public class BaseAct {
    
    private static final Logger logger = Logger.getLogger(BaseAct.class);
    
    protected void writeJson(Object jsonObj,HttpServletResponse response){
        Gson gson = new Gson();
        this.writeJson(gson, jsonObj, response);
    }
    
    protected void writeJson(Gson gson,Object jsonObj,HttpServletResponse response){  
        response.setContentType("application/json;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        try {  
            PrintWriter pw = response.getWriter();  
            //将Java对象转换为JSON字符
            String gsonStr = gson.toJson(jsonObj); 
            if(logger.isDebugEnabled()){
                logger.debug("gson输出:" + gsonStr);
            }
            //输出JSON字符 
            pw.print(gsonStr);  
            pw.flush();  
            pw.close();   
        } catch (IOException e) {  
            logger.info("输出GSON出错:", e);
        }  
    }
    
    protected  String redirectIndex(){
		return "redirect:index.html";
	}
    
    protected String jspUrl(String htmlUrl){
		return "jsp/admin/" + htmlUrl;
	}
    
    protected String wrapImgPath(String url,String baseUrl){
    	if(StringHelper.isNull(url)){
    		return null;
    	}else{
    		return baseUrl + url;
    	}
    	
    }

}

