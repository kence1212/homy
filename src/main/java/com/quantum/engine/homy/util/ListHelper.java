package com.quantum.engine.homy.util;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
    
    public static boolean isNotNull(List<?> list) {
        if (list != null && list.size() > 0) {
            for(int i = 0 ;i < list.size();i++){
                if(list.get(i) == null)
                    list.remove(list.get(i));
            }
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 把Id数组转换为ids字符�?
     * List<Id or String> 转换格式为（id,id,id�?
     * @param list（需要转换的数组�?
     * @return 成功返回String
     */
    public static String listIdToString(List<String> list) {
        return listIdToString( list,",");
    }
    public static String listIdToString(List<String> list,String linkSign) {
        StringBuffer ids = new StringBuffer();
        if(isNotNull(list)){
            for(int i = 0 ; i < list.size(); i++){
                String id = list.get(i);
                if(StringHelper.isNotNull(id)){
                    if(i == 0){
                        ids.append(id);
                    }else{
                        ids.append(linkSign).append(id);
                    }
                }
            }
        }
        return ids.toString();
    }
    /**
     * 
     * @param list
     * @return
     */
    public static Object[] listToObjectArray(List<Object> list) {
        if(isNotNull(list)){
            Object[] objArray = new Object[list.size()];
            for(int i = 0 ; i < list.size(); i++){
                objArray[i]= list.get(i);
            }
                return objArray;
        }
        return null;
    }
    
    /**
     * 获取图片格式集合
     * @return
     */
    public static List<String> getImageTypeList(){
    	List<String> fileTypes = new ArrayList<String>();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif");  
        fileTypes.add("png");  
        return fileTypes;
    }
    
    /**
     * 
     * @author fzz
     * @date 2017年12月14日
     * @description 判断是否   1,2,3 格式的字符串
     */
    public static boolean isNotNumStr(String s){
		
		boolean result = true;
		String[] split = s.split(",");
		for (String string : split) {
			try{
				Integer.parseInt(string);
			}catch(Exception e){
				result = false;
			}
		}
		return !result;
	}
    
    /**
     * 
     * @author fzz
     * @date 2017年12月14日
     * @description 获取id字符窜的数组
     */
    public static List<Integer> getIdList(String id){
    	
    	String[] split = id.split(",");
    	List<Integer> idList = new ArrayList<>();
    	for (String s : split) {
    		idList.add(Integer.parseInt(s));
		}
    	return idList;
    	
    }
}
