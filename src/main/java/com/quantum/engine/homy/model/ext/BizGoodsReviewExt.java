package com.quantum.engine.homy.model.ext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quantum.engine.homy.model.BizGoodsReview;
import com.quantum.engine.homy.util.DateUtil;

public class BizGoodsReviewExt extends BizGoodsReview{
	
	private String userName;
	private String createTime;
	private List<String> imgs;
	private String portrait;
	private List<Map<String,Object>> imgsToShow;
	
	public static BizGoodsReviewExt toshow (BizGoodsReviewExt ext, String baseUrl){
		BizGoodsReviewExt bizGoodsReviewExt = new BizGoodsReviewExt();
		bizGoodsReviewExt.setPortrait(baseUrl + ext.getPortrait());
		if(BizGoodsReview.UNSHOWNAME == ext.getUnShowName()){
			//匿名
			if(ext.getUserName().length() <= 2){
				//如果长度<=2
				if(ext.getUserName().length() == 1){
					bizGoodsReviewExt.setUserName("*****");
				} else{
					String userName = ext.getUserName();
					bizGoodsReviewExt.setUserName(userName.substring(0, 1)+"****");
				}
			}else{
				//如果长度>2
				String userName = ext.getUserName();
				bizGoodsReviewExt.setUserName(userName.substring(0,1)+"***"+userName.substring(userName.length()-1,userName.length()));
			}
		} else{
			//不匿名
			bizGoodsReviewExt.setUserName(ext.getUserName());
		}
		bizGoodsReviewExt.setCreateTime(DateUtil.dateToString(ext.getReviewTime(), "yyyy-MM-dd"));
		bizGoodsReviewExt.setContent(ext.getContent());
		List<Map<String,Object>> imgsToShow = new ArrayList<>();
		List<String> imgs2 = ext.getImgs();
		for (String string : imgs2) {
			Map<String,Object> map = new HashMap<>();
			map.put("url", baseUrl + string);
			imgsToShow.add(map);
		}
		bizGoodsReviewExt.setImgsToShow(imgsToShow);
		bizGoodsReviewExt.setScore(ext.getScore());
		return bizGoodsReviewExt;
	}
	
	
	
	public List<Map<String, Object>> getImgsToShow() {
		return imgsToShow;
	}
	public void setImgsToShow(List<Map<String, Object>> imgsToShow) {
		this.imgsToShow = imgsToShow;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	
}
