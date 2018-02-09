package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.dao.BasImageMapper;
import com.quantum.engine.homy.dao.BizGoodsReviewMapper;
import com.quantum.engine.homy.dao.BizOrderItemMapper;
import com.quantum.engine.homy.dao.BizOrderMapper;
import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizGoodsReview;
import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.ext.BizGoodsReviewExt;
import com.quantum.engine.homy.service.BizGoodsReviewService;
import com.quantum.engine.homy.util.OrderUtil;

@Service
public class BizGoodsReviewServiceImpl implements BizGoodsReviewService {

	@Autowired
	private BizGoodsReviewMapper mapper;
	@Autowired
	private BasImageMapper basImageMapper;
	@Autowired
	private BizOrderMapper bizOrderMapper;
	@Autowired
	private BizOrderItemMapper bizOrderItemMapper;
	
	
	@Override
	public String review(String content, Integer goodsItemId, Integer score, Integer unShowName,
			Integer userId, List<String> flieUrls) {
		
		//查询订单状态是否为已收货未收货不能评价  
		BizOrder bizOrder = bizOrderMapper.getByOrderItemId(goodsItemId);
		if(bizOrder == null){
			return "无此订单";
		}
		if(bizOrder.getState() != OrderUtil.ORDER_STATE_RECEIVE){
			return "未收货不能评价";
		}
		//查询是否评论过
		BizGoodsReview bizGoodsReviewInDB = mapper.getByUserIdAndGoodsItemId(userId,goodsItemId);
		if(bizGoodsReviewInDB == null){
			
			
			//保存发布的评价
			BizGoodsReview bizGoodsReview = new BizGoodsReview();
			bizGoodsReview.setContent(content);
			bizGoodsReview.setGoodsItemId(goodsItemId);
			bizGoodsReview.setIsValid(Constants.VALID);
			bizGoodsReview.setReviewTime(new Date());
			bizGoodsReview.setScore(score);
			bizGoodsReview.setUnShowName(unShowName);
			bizGoodsReview.setUserId(userId);
			mapper.insert(bizGoodsReview);
			
			if(flieUrls != null && flieUrls.size() > 0){
				//生成图片记录
				for (String fileUrl : flieUrls) {
					BasImage basImage = new BasImage();
					basImage.setCreateId(userId);
					basImage.setCreateTime(new Date());
					basImage.setImagePath(fileUrl);
					basImage.setReviewId(bizGoodsReview.getId());
					basImage.setType(BasImage.IMAGE_TYPE_REVIEW);
					basImageMapper.insert(basImage);
				}
			}
			
			//查询订单所有明细是否已评价，如果是就修改订单状态 
			List<Integer> itemIds = bizOrderItemMapper.getIdByOrderId(bizOrder.getId());
			int reviews = mapper.getCountByItemIds(itemIds);
			if(itemIds != null && itemIds.size() == reviews){
				//全部订单明细已评价设置订单状态和订单明细状态
				bizOrderMapper.setState(bizOrder.getId(),OrderUtil.ORDER_STATE_REPLYED);
				bizOrderItemMapper.setStateByOrderId(bizOrder.getId(), OrderUtil.ORDER_STATE_REPLYED);
			}
			
			return "";
			
		} else{
			
			return "该用户已评论";
			
		}
	}


	@Override
	public List<BizGoodsReviewExt> getReviewList(Integer goodsId, Integer page, Integer pageSize) {
		
		if(page == null || page <= 0){
			page = 0;
			pageSize = 10;
		}
		
		return mapper.getReviewList(goodsId,page,(page-1)*pageSize,pageSize);
		
	}
	
	@Override
	public int countByGoodsId(Integer goodsId){
		return mapper.countByGoodsId(goodsId);
	}
	
	@Override
	public BizGoodsReviewExt getLastestReviewByGoodsId(Integer goodsId){
		return mapper.getLastestReviewByGoodsId(goodsId);
	}

}
