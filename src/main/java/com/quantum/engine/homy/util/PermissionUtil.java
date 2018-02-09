package com.quantum.engine.homy.util;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

	private static List<String> permissions = new ArrayList<>();
	
	static{
		permissions.add("woyouquanxian");
	}
	
   public static Boolean  hasPermission(String permission){  
	   //以后再session中获取该用户的权限集合进行判断 TODO
	   if(permission == null || "".equals(permission) ){
		   return false;
	   }else {
		   if(permissions.contains(permission)){
			   return true;
		   }else{
			   return false;
		   }
	   }
    }
}
