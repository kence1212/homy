package com.quantum.engine.homy.action.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.ListReviewDto;
import com.quantum.engine.homy.model.dto.ReviewDto;
import com.quantum.engine.homy.model.ext.BizGoodsReviewExt;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.GoodsAppShowResult;
import com.quantum.engine.homy.model.result.GoodsListItemResult;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizGoodsReviewService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.UserService;

@Controller
@RequestMapping("/app_review")
public class AppGoodsReviewAct extends BaseAct{
	
	@Autowired
	private BizGoodsReviewService bizGoodsReviewService;
	@Autowired
	private BizGoodsService bizGoodsService;
	@Autowired
	private UserService userService;
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("review.do")
	public void review(@RequestParam(value = "imgs", required = false) MultipartFile[] imgs,ReviewDto dto, HttpServletResponse response, HttpServletRequest request){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<String> flieUrls = new ArrayList<>();
		
		if(imgs != null && imgs.length > 0){
			for(MultipartFile file : imgs){
				String path = request.getSession().getServletContext().getRealPath("upload");  
				String fileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())); 
				File targetFile = new File(path, fileName);                                                                                                                                                     
				if(!targetFile.exists()){  
					targetFile.mkdirs();  
				}  
				//保存  
				try {  
					file.transferTo(targetFile);  
				} catch (Exception e) {  
					e.printStackTrace();  
				}  
				String fileUrl = "/upload/" + fileName;
				flieUrls.add(fileUrl);
			}
		}
		
		//发表评论
		String review = bizGoodsReviewService.review(dto.getContent(),dto.getGoodsItemId(),dto.getScore(),dto.getUnShowName(),dto.getUserId(),flieUrls);
		
		if("".equals(review)){
			result.success("发表成功");
		} else{
			result.wrong("40000",review);
		}
		
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("listReview.do")
	public void listReview(ListReviewDto dto, HttpServletResponse response){
		
		Result validate = dto.validate();
		if(validate.getKey("errorMsg") != null){
			writeJson(validate.getValues(), response);
			return;
		}
		
		//获取商品信息
		 GoodsListItemResult byGoodsIdAndCityId = bizGoodsService.getByGoodsIdAndCityId(dto.getGoodsId(),dto.getCityId());
		 GoodsAppShowResult goods = byGoodsIdAndCityId.toReviewListShow(baseUrl);
		
		//获取评价列表 
		List<BizGoodsReviewExt> reviews =  bizGoodsReviewService.getReviewList(dto.getGoodsId(),dto.getPage(),dto.getPageSize());
		
		List<BizGoodsReviewExt> toshow = new ArrayList<>();
		for (BizGoodsReviewExt bizGoodsReviewExt : reviews) {
			BizGoodsReviewExt toshow2 = BizGoodsReviewExt.toshow(bizGoodsReviewExt, baseUrl);
			toshow.add(toshow2);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("goods", goods);
		map.put("reviews", toshow);
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(map);
		writeJson(br, response);
		
	}
	
}
