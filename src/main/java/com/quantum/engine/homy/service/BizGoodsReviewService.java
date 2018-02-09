package com.quantum.engine.homy.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.quantum.engine.homy.model.ext.BizGoodsReviewExt;

public interface BizGoodsReviewService {

	String review(String content, Integer goodsItemId, Integer score, Integer unShowName,
			Integer userId, List<String> flieUrls);

	List<BizGoodsReviewExt> getReviewList(Integer goodsId, Integer page, Integer pageSize);
	
	int countByGoodsId(Integer goodsId);
	
	public BizGoodsReviewExt getLastestReviewByGoodsId(Integer goodsId);

}
