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
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.model.dto.AppGetCategoryDto;
import com.quantum.engine.homy.model.dto.GetIndexDto;
import com.quantum.engine.homy.model.ext.BizGoodsBoutiqueExt;
import com.quantum.engine.homy.model.ext.BizRecommendSilderExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.AppIndexBrandResult;
import com.quantum.engine.homy.model.result.GoodsAppShowResult;
import com.quantum.engine.homy.model.result.AppIndexResult;
import com.quantum.engine.homy.model.result.AppIndexSliderImgResult;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.CategoryAppResult;
import com.quantum.engine.homy.model.result.GoodsListItemResult;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.model.result.ResultCode;
import com.quantum.engine.homy.service.BasRegionService;
import com.quantum.engine.homy.service.BizGoodsCategoryService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsBoutiqueService;
import com.quantum.engine.homy.service.GoodsBrandService;
import com.quantum.engine.homy.service.GoodsStyleService;
import com.quantum.engine.homy.service.RecommendSilderService;
import com.quantum.engine.homy.util.StringHelper;

@Controller
@RequestMapping("/app")
public class AppBaseAct extends BaseAct {
	
	@Autowired
	private GoodsStyleService goodsStyleService;
	@Autowired
	private GoodsBrandService goodsBrandService;
	@Autowired
	private BizGoodsCategoryService bizGoodsCategoryService;
	@Autowired
	private RecommendSilderService recommendSilderService;
	@Autowired
	private GoodsBoutiqueService goodsBoutiqueService;
	@Autowired
	private BizGoodsService goodsService;
	@Autowired
	private BasRegionService regionService;
	
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("/getIndex.do")
	@ResponseBody
	public AppIndexResult index(GetIndexDto dto){
		AppIndexResult result = new AppIndexResult();
		Result r = dto.validate();
		if(r.getKey("errorMsg") != null){
			result.setResultCode(ResultCode.RESULT_CODE_WRONG);
			result.setErrorMsg((String)r.getKey("errorMsg"));
			return result;
		}
		result.setResultCode(ResultCode.RESULT_CODE_SUCESS);
		result.setMsg("成功获取");
		//轮播图三张 类型（广告、商品）、 图片、地址 、排序
		List<AppIndexSliderImgResult> banner = new ArrayList<AppIndexSliderImgResult>();
		List<BizRecommendSilderExt>  silders = recommendSilderService.listAllWithGood();
		if(silders != null && silders.size() > 0){
			for(BizRecommendSilderExt silder:silders){
				AppIndexSliderImgResult sir = new AppIndexSliderImgResult();
				sir.setImageUrl(baseUrl + silder.getImgPath());
				sir.setType(silder.getType());
				if(silder.getType() == 1){
					sir.setTarget(silder.getGoodsId() + "");
					sir.setGoodsId(silder.getGoodsId());
				}else if(silder.getType() == 2){
					sir.setTarget(silder.getTargetUrl());
				}
				banner.add(sir);
			}
		}
		result.setBanner(banner);
		//大牌制造
		List<AppIndexBrandResult> brands = new ArrayList<AppIndexBrandResult>();
		List<BasGoodsBrand> brandList = goodsBrandService.listAll();
		if(brandList != null && brandList.size() > 0){
			for(int i = 0 ; i < brandList.size() ; i ++){
				if(i < 4){
					BasGoodsBrand brand = brandList.get(i);
					AppIndexBrandResult brt = new AppIndexBrandResult();
					String img = null;
					if(brand.getAppClientIcon() != null){
						img = baseUrl + brand.getAppClientIcon();
					}
					brt.setImageUrl(img);
					brt.setType(0);
					brt.setTarget(brand.getBrandId() + "");
					brt.setBrandId(brand.getBrandId());
					brt.setBrandName(brand.getBrandName());
					//brt.setAppClientIcon(img);
					//brt.setBrandDesc(brand.getBrandDesc());
					brands.add(brt);
				}
			}
		}
		result.setBrands(brands);
		//每周小物  图片  原价  现价  风格 排序
		List<GoodsAppShowResult> week = new ArrayList<GoodsAppShowResult>();
		List<BizGoodsBoutiqueExt> litterList =  goodsBoutiqueService.listByType(2);
		if(litterList != null && litterList.size() > 0){
			for(BizGoodsBoutiqueExt litter:litterList){
				GoodsAppShowResult litterGoods = new GoodsAppShowResult();
				litterGoods.setDescription(litter.getDesc());
				litterGoods.setId(litter.getGoodsId());
				litterGoods.setImageUrl(baseUrl + litter.getImgPath());
				litterGoods.setName(litter.getBizGoodsBase().getGoodsName());
				litterGoods.setOriginalPrice(litter.getBizGoodsBase().getDefaultPrice() + 10);
				litterGoods.setMinPrice(litter.getBizGoodsBase().getDefaultPrice());
				week.add(litterGoods);
			}
		}
		result.setWeek(week);
		return result;
	}
	
	@RequestMapping("/getHotGoods.do")
	@ResponseBody
	public BaseResponse getHotGoods(Integer page,Integer cityId){
		Integer size = 10;
		if(page == null || page <= 0){
			page = 1;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("size", size);
		if(cityId != null){
			params.put("cityId", cityId);
		}
		List<GoodsListItemResult> list = goodsService.searchGoods(params);
		
		List<GoodsAppShowResult> hots = new ArrayList<GoodsAppShowResult>();
		if(list != null && list.size() > 0){
			for(GoodsListItemResult goods:list){
				GoodsAppShowResult hot = new GoodsAppShowResult();
				hot.setDescription(goods.getGoodsName());
				String icon = goods.getGoodsIcon();
				if(StringHelper.isNotNull(icon) && !icon.startsWith("http")){
					hot.setImageUrl(baseUrl + icon);
				}
				hot.setName(goods.getGoodsName());
				hot.setId(goods.getGoodsId());
				hots.add(hot);
			}
		}
		BaseResponse result = BaseResponse.getSuccess();
		result.setObj(hots);
		return result;
	}
	
	@RequestMapping("/getCategory.do")
	public void getCategory(AppGetCategoryDto dto,HttpServletResponse response){
		int type = dto.getType() == null?1:dto.getType().intValue();
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		switch (type) {
		case 1:
			//获取风格
			List<BizGoodsStyle> list = goodsStyleService.listAll();
			br.setObj(CategoryAppResult.styleListTrans(list,baseUrl));
			break;
		case 2:
			//获取空间分类
			List<BizGoodsCategory> spaceList = bizGoodsCategoryService.getByType(BizGoodsCategory.TYPE_SPACE);
			br.setObj(CategoryAppResult.treeCategoryTrans(spaceList,baseUrl));
			break;
		case 3:
			//获取品类 分类
			List<BizGoodsCategory> categoryList = bizGoodsCategoryService.listAll();
			List<CategoryAppResult> result = new ArrayList<CategoryAppResult>();
			if(categoryList != null && categoryList.size() > 0){
				for(BizGoodsCategory bgc:categoryList){
					CategoryAppResult cart = new CategoryAppResult();
					cart.setCode(bgc.getCategoryCode());
					String icon = bgc.getAppClientIcon();
					cart.setIcon(icon .startsWith("htpp")?icon:(baseUrl+icon));
					cart.setId(bgc.getId());
					cart.setName(bgc.getCategoryName());
					result.add(cart);
				}
			}
			br.setObj(result);
			break;
		case 4:
			//获取品牌 
			List<AppIndexBrandResult> brands = new ArrayList<AppIndexBrandResult>();
			List<BasGoodsBrand> brandList = goodsBrandService.listAll();
			if(brandList != null && brandList.size() > 0){
				for(int i = 0 ; i < brandList.size() ; i ++){
					BasGoodsBrand brand = brandList.get(i);
					AppIndexBrandResult brt = new AppIndexBrandResult();
					brt.setImageUrl(baseUrl + brand.getAppClientIcon());
					brt.setType(0);
					brt.setTarget(brand.getBrandId() + "");
					brt.setBrandId(brand.getBrandId());
					brt.setBrandName(brand.getBrandName());
					brands.add(brt);
				}
			}
			br.setObj(brands);
			break;
		default:
			break;
		}
		writeJson(br, response);
	}
	
	@RequestMapping("/getBrand.do")
	public void getBrand(HttpServletResponse response){
			//获取风格
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		List<BasGoodsBrand> brandList = goodsBrandService.listAll();
		br.setObj(CategoryAppResult.brandListTrans(brandList,baseUrl));
		writeJson(br, response);
	}
	
	@RequestMapping("/getCity.do")
	public void getCity(String cityName, HttpServletResponse response){
		
		BaseResponse br = regionService.getCityByName(cityName);
		writeJson(br, response);
		
	}
	
}
