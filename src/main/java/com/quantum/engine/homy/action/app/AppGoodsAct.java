package com.quantum.engine.homy.action.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.BizGoodsReview;
import com.quantum.engine.homy.model.BizGoodsSkuStock;
import com.quantum.engine.homy.model.dto.GetBrandGoodsDto;
import com.quantum.engine.homy.model.dto.GetCityDto;
import com.quantum.engine.homy.model.dto.GetColorDto;
import com.quantum.engine.homy.model.dto.GetFilterTextureDto;
import com.quantum.engine.homy.model.dto.GetGoodsDetailDto;
import com.quantum.engine.homy.model.dto.GetSizeDto;
import com.quantum.engine.homy.model.dto.GetTextureDto;
import com.quantum.engine.homy.model.dto.SearchGoodsDto;
import com.quantum.engine.homy.model.dto.SkuPageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsReviewExt;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.GoodsAppShowResult;
import com.quantum.engine.homy.model.result.GoodsListItemResult;
import com.quantum.engine.homy.model.result.IdName;
import com.quantum.engine.homy.model.result.Option;
import com.quantum.engine.homy.model.result.OptionList;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizGoodsCategoryService;
import com.quantum.engine.homy.service.BizGoodsReviewService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.BizGoodsSkuService;
import com.quantum.engine.homy.service.GoodsBrandService;
import com.quantum.engine.homy.util.StringHelper;

@Controller
@RequestMapping("/appgs")
public class AppGoodsAct extends BaseAct {

	@Autowired
	private BizGoodsService bizGoodsService;
	@Autowired
	private BizGoodsSkuService bizGoodsSkuService;
	@Autowired
	private GoodsBrandService goodsBrandService;
	@Autowired
	private BizGoodsCategoryService bizGoodsCategoryService;
	@Autowired
	private BizGoodsReviewService bizGoodsReviewService;
	
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;

	@RequestMapping("/getGoodsDetail.do")
	public void getGoodsDetail(GetGoodsDetailDto dto,HttpServletResponse response) {
		Result result = dto.validate();
		Integer goodsId = dto.getGoodsId();
		BizGoodsBase goods = bizGoodsService.getById(dto.getGoodsId());
		if(goods != null && goods.getGoodsState() == BizGoodsBase.GOODS_STATE_NORMAL){
			result.success("获取商品");
			result.putValue("goodsName", goods.getGoodsName() );
			result.putValue("goodsDesc", goods.getGoodsName() );
			result.putValue("goodsIcon", wrapImgPath(goods.getGoodsIcon(), baseUrl));
			
			BasGoodsBrand brand  = goodsBrandService.getById(goods.getBrandId());
			if(brand != null){
				result.putValue("brandId", brand.getBrandId());
				result.putValue("brandName", brand.getBrandName() );
			}
			//评论数
			result.putValue("reviewCount", 0 );
			List<OptionList> goodsOptionList = new ArrayList<OptionList>(); 
			List<Option> goodsAttrs = bizGoodsService.getAttrsByGoodsId(goodsId); 
			result.putValue("attrs", goodsAttrs);
			GetSizeDto sizeDto = new GetSizeDto();
			sizeDto.setGoodsId(goodsId);
			List<Map<String,Object>> sizeList = bizGoodsService.getSizeList(sizeDto);
			goodsOptionList.add(new OptionList("规格", sizeList));
			GetColorDto colorDto = new GetColorDto();
			colorDto.setGoodsId(goodsId);
			List<Map<String,Object>> colorList = bizGoodsService.getColorList(colorDto);
			goodsOptionList.add(new OptionList("颜色", colorList));
			GetTextureDto textureDto = new GetTextureDto();
			textureDto.setGoodsId(goodsId);
			List<Map<String,Object>> textureList = bizGoodsService.getTextureList(textureDto);
			goodsOptionList.add(new OptionList("材质", textureList));
			result.putValue("goodsExt", goodsOptionList);
			result.putValue("price", goods.getDefaultPrice());
			result.putValue("goodsId", goods.getId());
			List<BasImage> imageList = bizGoodsService.getByGoodsId(goodsId);
			List<String> goodsImgs = new ArrayList<String>();
			List<String> goodsDescImgs = new ArrayList<String>();
			if(imageList != null && imageList.size() > 0){
				for(BasImage img:imageList){
					if(img.getType() == BasImage.IMAGE_TYPE_BANNER){
						goodsImgs.add(wrapImgPath(img.getImagePath(), baseUrl));
					}else{
						goodsDescImgs.add(wrapImgPath(img.getImagePath(), baseUrl));
					}
				}
			}
			result.putValue("goodsImgs", goodsImgs);
			result.putValue("goodsDescImgs", goodsDescImgs);
			
			List<BizGoodsSkuStockExt> skuStocks =  bizGoodsSkuService.getByGoodsIdAndCity(goodsId,dto.getCityId());
			for(BizGoodsSkuStockExt skss:skuStocks ){
				String skuIcon = skss.getSkuIcon(); 
				if(StringHelper.isNotNull(skuIcon) && !skuIcon.startsWith("http")){
					skss.setSkuIcon(baseUrl+ skuIcon); 
				}
			}
			result.putValue("skus", skuStocks);
			
			//评论 TODO
			int reviewCount = bizGoodsReviewService.countByGoodsId(goodsId);
			BizGoodsReviewExt review = bizGoodsReviewService.getLastestReviewByGoodsId(goodsId);
			result.putValue("reviewCount", reviewCount);
			List<BizGoodsReviewExt> toshow = new ArrayList<>();
			if(review != null){
				BizGoodsReviewExt toshow2 = BizGoodsReviewExt.toshow(review, baseUrl);
				toshow.add(toshow2);
			}
			result.putValue("review", toshow);
			
		}else{
			result.wrong("未找到商品");
		}
		writeJson(result.getValues(), response);
	}

	@RequestMapping("/getSizeList.do")
	public void getSizeList(GetSizeDto dto,HttpServletResponse response) {
		// 根据GOODSID(必须) textureId(非必须) colorId(非必须) cityId(非必须)
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<Map<String,Object>> sizeList = bizGoodsService.getSizeList(dto);
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		
		br.setObj(sizeList);
		
		writeJson(br, response);

	}

	@RequestMapping("/getColorList.do")
	public void getColorList(GetColorDto dto,HttpServletResponse response) {
		// 根据GOODSID(必须) textureId(非必须) sizeId(非必须) cityId(非必须)
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<Map<String,Object>> sizeList = bizGoodsService.getColorList(dto);
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		
		br.setObj(sizeList);
		
		writeJson(br, response);

	}

	@RequestMapping("/getTextureList.do")
	public void getTextureList(GetTextureDto dto, HttpServletResponse response) {
		// 根据GOODSID(必须) colorId(非必须) sizeId(非必须) cityId(非必须)
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<Map<String,Object>> sizeList = bizGoodsService.getTextureList(dto);
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		
		br.setObj(sizeList);
		
		writeJson(br, response);

	}
	
	@RequestMapping("/getCityList.do")
	public void getCityList(GetCityDto dto, HttpServletResponse response) {
		// 根据GOODSID(必须) colorId(非必须) sizeId(非必须) textureId(非必须)
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<Map<String,Object>> sizeList = bizGoodsService.getCityList(dto);
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		
		br.setObj(sizeList);
		
		writeJson(br, response);
		
	}
	
	/**
	 * 搜索联想
	 * @return
	 */
	@RequestMapping("/searchAsso.do")
	public void searchAsso(SearchGoodsDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		BaseResponse br = BaseResponse.getSuccess();
		List<String> list = bizGoodsService.searchAsso(dto.getKey());
		br.setObj(list);
		writeJson(br, response);
	}
	
	/**
	 * 热门搜索
	 */
	@RequestMapping("/hotSearch.do")
	public void hotSearch(SearchGoodsDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		BaseResponse br = BaseResponse.getSuccess();
		List<String> list = bizGoodsService.searchAsso("");
		br.setObj(list);
		writeJson(br, response);
	}
	
	/**
	 * 搜索结果
	 * @param dto
	 * @param response
	 */
	@RequestMapping("/search.do")
	public void search(SearchGoodsDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto != null && StringHelper.isNotNull(dto.getKey())){
			params.put("key", dto.getKey());
		}
		if(dto !=null && dto.getCategoryId() != null){
			params.put("categoryId", dto.getCategoryId());
		}
		if(dto !=null && dto.getStyleId() != null){
			params.put("styleId", dto.getStyleId());
		}
		if(dto !=null && dto.getMinPrice() != null){
			params.put("minPrice", dto.getMinPrice());
		}
		if(dto !=null && dto.getMaxPrice() != null){
			params.put("maxPrice", dto.getMaxPrice());
		}
		if(dto != null && StringHelper.isNotNull(dto.getTexture()) ){
			params.put("texture", dto.getTexture());
		}
		if(dto !=null && dto.getOrderby() != null){
			if(dto.getOrderby().equals("pas")){
				params.put("orderby", "minPrice");
			}else if(dto.getOrderby().equals("pds")){
				params.put("orderby", "minPrice desc");
			}
		}
		Integer page = dto.getPage();
		if(page == null || page < 1){
			page = 1;
		}
		params.put("page", page);  
		params.put("size", 10);
		List<GoodsAppShowResult> resultList = new ArrayList<GoodsAppShowResult>();
		List<GoodsListItemResult> list = bizGoodsService.searchGoods(params);
		if(list != null && list.size() > 0){
			for(GoodsListItemResult gli:list){
				String icon = gli.getGoodsIcon();
				if(StringHelper.isNotNull(icon) && !icon.startsWith("http")){
					gli.setGoodsIcon(baseUrl + icon);
				}
				resultList.add(gli.toShow());
			}
		}
		result.success("获取成功");
		result.putValue("obj", resultList);
		
		List<Map<String, Object>> filters = new ArrayList<Map<String,Object>>(); 
		List<String> textures = bizGoodsService.searchTexture(params);
		Map<String, Object> texturesFilters = new HashMap<String, Object>();
		texturesFilters.put("filterName", "材质");
		List<Option> textureList = new ArrayList<Option>();
		for(String texture:textures){
			textureList.add(new Option(texture));
		}
		texturesFilters.put("filterValue", textureList);
		filters.add(texturesFilters);
		result.putValue("filters", filters);
		
		
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/brandCategorys.do")
	public void brandCategorys(GetBrandGoodsDto dto,HttpServletResponse response){
		Result result = new Result();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//获取分类 
		List<BizGoodsCategory> spaceList = bizGoodsCategoryService.getByTypeAndBrandId(BizGoodsCategory.TYPE_SPACE,dto.getBrandId());
		List<IdName> categoryList = new ArrayList<IdName>();
		categoryList.add(new IdName(0, "全部"));
		if(spaceList != null && spaceList.size() > 0){
			for(BizGoodsCategory cate : spaceList){
				if(cate.getPid() == null || cate.getPid() == 0){
					categoryList.add(new IdName(cate.getId(), cate.getCategoryName()));
				}
			}
		}
		result.success("获取成功");
		result.putValue("categoryList", categoryList);
		
		BasGoodsBrand brand =  goodsBrandService.getById(dto.getBrandId());
		if(brand != null){
			result.putValue("brandId", brand.getBrandId());
			result.putValue("brandName", brand.getBrandName());
			result.putValue("appClientIcon", brand.getAppClientIcon());
			result.putValue("brandDesc", brand.getBrandDesc());
		}
		writeJson(result.getValues(), response);
	}
	
	/**
	 * 获取品牌商品列表
	 * @param dto
	 */
	@RequestMapping("/brandGoods.do")
	public void brandGoods(GetBrandGoodsDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//获取商品
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("brandId", dto.getBrandId());
		if(dto.getCategoryId() != null && dto.getCategoryId() != 0){
			params.put("brandCategoryId", dto.getCategoryId());
		}
		if(dto.getCityId() != null){
			params.put("cityId", dto.getCityId());
		}
		
		Integer page = dto.getPage();
		if(page == null || page.intValue() < 0){
			page = 1;
		}
		params.put("page", page);
		params.put("size", 10);
		List<GoodsAppShowResult> resultList = new ArrayList<GoodsAppShowResult>();
		List<GoodsListItemResult> list = bizGoodsService.searchGoods(params);
		if(list != null && list.size() > 0){
			for(GoodsListItemResult gli:list){
				String icon = gli.getGoodsIcon();
				if(StringHelper.isNotNull(icon) && !icon.startsWith("http")){
					gli.setGoodsIcon(baseUrl + icon);
				}
				resultList.add(gli.toShow());
			}
		}
		result.putValue("goods", resultList);
		result.success("获取成功");
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/getFilterTexture.do")
	public void getFilterTexture(GetFilterTextureDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		BaseResponse br = BaseResponse.getSuccess();
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getCategoryId() != null){
			params.put("categoryId", dto.getCategoryId());
		}
		List<String> list = bizGoodsService.searchTexture(params);
		br.setObj(list);
		writeJson(br, response);
	}
	
}
