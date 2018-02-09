package com.quantum.engine.homy.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.model.dto.GetBrandDto;
import com.quantum.engine.homy.model.dto.GetCategoryDto;
import com.quantum.engine.homy.model.dto.GetDto;
import com.quantum.engine.homy.model.dto.GetModelDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.GoodsBrandResult;
import com.quantum.engine.homy.model.result.GoodsCategoryResult;
import com.quantum.engine.homy.model.result.GoodsModelResult;
import com.quantum.engine.homy.model.result.GoodsStyleResult;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizGoodsCategoryService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsBrandService;
import com.quantum.engine.homy.service.GoodsModelService;
import com.quantum.engine.homy.service.GoodsStyleService;
import com.quantum.engine.homy.util.StringHelper;

@Controller
@RequestMapping("/cg")
public class GoodsAct extends BaseAct {
	
	@Autowired
	private BizGoodsCategoryService categoryService;
	@Autowired
	private GoodsBrandService goodsBrandService;
	@Autowired
	private GoodsStyleService goodsStyleService;
	@Autowired
	private BizGoodsService goodsService;
	@Autowired
	private GoodsModelService goodsModelService;
	
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("/getBrandList.do")
	public void brandList(GetBrandDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getCategoryId() != null && dto.getCategoryId().intValue() > 0){
			params.put("categoryId", dto.getCategoryId());
		}
		
		List<GoodsBrandResult> list = new ArrayList<GoodsBrandResult>();
		List<BasGoodsBrand> brandList = goodsBrandService.listHave3d(params);
		if(brandList != null){
			for(BasGoodsBrand brand:brandList){
				GoodsBrandResult brandResult = GoodsBrandResult.init(brand,baseUrl);
				list.add(brandResult);
			}
		}
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(list);
		writeJson(br, response);
	}
	
	@RequestMapping("/getStyleList.do")
	public void styleList(GetDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		List<GoodsStyleResult> list = new ArrayList<GoodsStyleResult>();
		List<BizGoodsStyle> styleList = goodsStyleService.listAll();
		if(styleList != null && styleList.size() > 0){
			for(BizGoodsStyle style:styleList){
				GoodsStyleResult styleResult = GoodsStyleResult.init(style,baseUrl);
				list.add(styleResult);
			}
		}
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(list);
		writeJson(br, response);
	}
	
	@RequestMapping("/getCategoryList.do")
	public void getCategoryByBrand(GetCategoryDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		List<GoodsCategoryResult> list = new ArrayList<GoodsCategoryResult>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getBrandId() != null && dto.getBrandId().intValue() > 0){
			params.put("brandId", dto.getBrandId());
		}
		List<BizGoodsCategory> categoryList = categoryService.listHave3d(params);
		if(categoryList != null){
			for(BizGoodsCategory cate:categoryList){
				GoodsCategoryResult categoryResult = GoodsCategoryResult.init(cate,baseUrl);
				list.add(categoryResult);
			}
		}
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(list);
		writeJson(br, response);
	}
	
	@RequestMapping("/getModelById.do")
	public void getModelById(GetModelDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		BaseResponse br = null;
		BizGoodsModelExt model = goodsModelService.getById(dto.getModelId());
		if(model == null){
			br = BaseResponse.getWrong("获取失败");
		}else{
			br = BaseResponse.getSuccess("获取成功");
			BizGoodsBase goods = goodsService.getDetailById(model.getGoodsId());
			GoodsModelResult goodsModel = new GoodsModelResult();
			goodsModel.setBrandName(goods.getBrandName());
			goodsModel.setGoodsCategory(goods.getGoodsCategoryName());
			goodsModel.setGoodsDesc(goods.getGoodsDesc());
			goodsModel.setGoodsName(goods.getGoodsName());
			goodsModel.setId(model.getId());
			goodsModel.setInformation("基本信息");
			goodsModel.setModelHeight(model.getModelHeight());
			goodsModel.setModelIcon(baseUrl + model.getModelIcon());
			goodsModel.setModelLength(model.getModelLength());
			goodsModel.setModelPath(baseUrl + model.getModelPath());
			goodsModel.setModeltag(model.getModelTag());
			goodsModel.setModelVersion(model.getModelVersion());
			goodsModel.setModelWidth(model.getModelWidth());
			goodsModel.setPrice(goods.getDefaultPrice());
			goodsModel.setShop(goods.getBrandName());
			goodsModel.setType(model.getType());
			goodsModel.setStyleId(goods.getGoodsStyleId());
			goodsModel.setCategoryId(goods.getGoodsCategoryId());
			goodsModel.setBrandId(goods.getBrandId());
			br.setObj(goodsModel);
		}
		writeJson(br, response);
	}
	
	@RequestMapping("/getModelList.do")
	public void getModelByCategory(GetModelDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getBrandId()  != null && dto.getBrandId().intValue() > 0){
			params.put("brandId",dto.getBrandId());
		}
		if(dto.getCategoryId() != null && dto.getCategoryId().intValue() > 0){
			params.put("goodsCategoryId",dto.getCategoryId());
		}
		if(dto.getStyleId() != null && dto.getStyleId().intValue() > 0){
			params.put("styleId",dto.getStyleId());
		}
		if(StringHelper.isNotNull(dto.getKey())){
			params.put("key", dto.getKey());
		}
		params.put("have3d", 1);
		Integer page = dto.getPage();
		Integer size = dto.getSize();
		if(page == null || page < 1 ){
			page = 1;
		}
		if(size == null || size < 1){
			size = 20;
		}
		Page<BizGoodsBase> pageList = goodsService.page(page , size , params);
		List<BizGoodsBase> goodsList = pageList.getList();
		List<GoodsModelResult> list = new ArrayList<GoodsModelResult>();
		if(goodsList != null && goodsList.size() > 0){
			for(BizGoodsBase goods:goodsList){
				List<BizGoodsModel> modelList = goodsModelService.getByGoodsId(goods.getId());
				for(BizGoodsModel model:modelList){
					GoodsModelResult goodsModel = new GoodsModelResult();
					goodsModel.setBrandName(goods.getBrandName());
					goodsModel.setGoodsCategory(goods.getGoodsCategoryName());
					goodsModel.setGoodsDesc(goods.getGoodsDesc());
					goodsModel.setGoodsName(goods.getGoodsName());
					goodsModel.setId(model.getId());
					goodsModel.setInformation("基本信息");
					goodsModel.setModelHeight(model.getModelHeight());
					goodsModel.setModelIcon(model.getModelIcon().startsWith("http")?model.getModelIcon():(baseUrl + model.getModelIcon()));
					goodsModel.setModelLength(model.getModelLength());
					goodsModel.setModelPath(model.getModelPath().startsWith("http")?model.getModelPath():(baseUrl + model.getModelPath()));
					goodsModel.setModeltag(model.getModelTag());
					goodsModel.setModelVersion(model.getModelVersion());
					goodsModel.setModelWidth(model.getModelWidth());
					goodsModel.setPrice(goods.getDefaultPrice());
					goodsModel.setShop(goods.getBrandName());
					goodsModel.setType(model.getType());
					goodsModel.setStyleId(goods.getGoodsStyleId());
					goodsModel.setCategoryId(goods.getGoodsCategoryId());
					goodsModel.setBrandId(goods.getBrandId());
					list.add(goodsModel);
				}
			}
		}
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(list);
		writeJson(br, response);
	}

}
