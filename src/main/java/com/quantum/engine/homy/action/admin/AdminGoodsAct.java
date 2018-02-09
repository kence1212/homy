/**
 * 
 */
package com.quantum.engine.homy.action.admin;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizGoodsAttrExt;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.BizGoodsColor;
import com.quantum.engine.homy.model.BizGoodsSize;
import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.model.BizGoodsTexture;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.AddGoodsReq;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.model.result.Option;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizGoodsCategoryService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsBrandService;
import com.quantum.engine.homy.service.GoodsStyleService;
import com.quantum.engine.homy.util.StringHelper;

/**
 * @author nicya
 * @date 2017年11月16日 下午3:35:46
 */
@Controller
@RequestMapping("/amgoods")
public class AdminGoodsAct extends BaseAct{
	
	@Autowired
	private BizGoodsCategoryService bizCategoryService;
	@Autowired
	private BizGoodsService goodsService;
	@Autowired
	private GoodsBrandService goodsBrandService;
	@Autowired
	private GoodsStyleService goodsStyleService;
	
	@RequestMapping("imgEditor.html")
	public String imgEditor(){
		return jspUrl("imgEditor");
	} 
	
	@RequestMapping("getJson.html")
	public void getJson(int id,HttpServletResponse response){
		 BizGoodsBase byId = goodsService.getById(id);
		 writeJson(byId, response);
	}
	
	@RequestMapping("index.html")
	public String index(Model model){
		/*List<BizGoodsBase> list = goodsService.listAllExt();
		model.addAttribute("goodsList", list);*/
		return jspUrl("list");
	}
	
	@RequestMapping("getList.html")
	@ResponseBody
	public Page<BizGoodsBase> list(Integer page,Integer pageSize,String key){
		if(page == null || page <= 0){
			page = 1;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = 10;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringHelper.isNotNull(key)){
			params.put("key", key);
		}
		Page<BizGoodsBase> pageList = goodsService.page(page, pageSize, params);
		return pageList;
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		List<BizGoodsCategory> categoryList = bizCategoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		List<BasGoodsBrand> brandList = goodsBrandService.listAll();
		model.addAttribute("brandList", brandList);
		List<BizGoodsStyle> styleList = goodsStyleService.listAll();
		model.addAttribute("styleList", styleList);
		return jspUrl("add");
	} 
	
	@RequestMapping("add.html")
	public String add(AddGoodsReq addGoods,HttpServletRequest req){
		Integer attrCount = 0;
		try{
			attrCount = Integer.parseInt((String)req.getParameter("attrInputCount"));
		}catch(Exception e){}
		List<BizGoodsAttrExt> list = null;
		if(attrCount != null && attrCount > 0){
			list = new ArrayList<BizGoodsAttrExt>();
			for(int i = 1 ; i <= attrCount ; i++){
				String otherAttrKey = req.getParameter("otherAttrKey" + i);
				String otherAttrValue = req.getParameter("otherAttrValue" + i);
				if(StringHelper.isNotNull(otherAttrKey) && StringHelper.isNotNull(otherAttrValue)){
					BizGoodsAttrExt ext = new BizGoodsAttrExt();
					ext.setAttrName(otherAttrKey);
					ext.setAttrValue(otherAttrValue);
					list.add(ext);
				}
			}
		}
		goodsService.addGoods(addGoods.toGoodsBase(),addGoods.getGoodsDescImg()
				, addGoods.getGoodsImg(), addGoods.getGoodsTextures()
				, addGoods.getGoodsSizes(),addGoods.getGoodsColors(),list);
		return redirectIndex();
	}
	
	@RequestMapping("toEdit.html")
	public String toAdd(Model model,Integer id){
		BizGoodsBase bizGoodsBase = goodsService.getById(id);
		model.addAttribute("goodsBase", bizGoodsBase);
		List<BizGoodsCategory> categoryList = bizCategoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		List<BasGoodsBrand> brandList = goodsBrandService.listAll();
		model.addAttribute("brandList", brandList);
		List<BizGoodsStyle> styleList = goodsStyleService.listAll();
		model.addAttribute("styleList", styleList);
		return jspUrl("edit");
	} 
	
	@RequestMapping("edit.html")
	public String edit(BizGoodsBase base,HttpServletRequest request){
		BizGoodsBase source = goodsService.getById(base.getId());
		source.setGoodsName(base.getGoodsName());
		source.setDefaultPrice(base.getDefaultPrice());
		source.setGoodsTypeNumber(base.getGoodsTypeNumber());
		source.setGoodsIcon(base.getGoodsIcon());
		source.setGoodsCategoryId(base.getGoodsCategoryId());
		source.setBrandId(base.getBrandId());
		source.setGoodsStyleId(base.getGoodsStyleId());
		source.setModifyId(RequestUtil.getCurrentUserId(request));
		source.setModifyTime(new Date());
		source.setGoodsDesc(base.getGoodsDesc());
		goodsService.update(source);
		return redirectIndex();
	}
	
	@RequestMapping("toEditGoodsSize.html")
	public String toEditGoodsSize(Integer goodsId,Model model){
		List<Map<String, Object>> list = goodsService.getSizeById(goodsId);
		model.addAttribute("sizeList", list);
		model.addAttribute("goodsId", goodsId);
		return jspUrl("edit_goods_size");
	}
	
	@RequestMapping("addGoodsSize.do")
	public void addGoodsSize(Integer goodsId,String name,HttpServletRequest request,
			HttpServletResponse response){
		BizGoodsSize size = new BizGoodsSize();
		size.setCreateId(RequestUtil.getCurrentUserId(request));
		size.setCreateTime(new Date());
		size.setGoodsId(goodsId);
		size.setIsValid((short)1);
		size.setName(name);
		goodsService.addGoodsSize(size);
		Result result = new Result();
		result.success("新增成功");
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("deleteGoodsSize.do")
	public void deleteGoodsSize(Integer id,HttpServletResponse response){
		Result result = new Result();
		if(id != null){
			String error = goodsService.deleteGoodsSize(id);
			if(error != null){
				result.wrong(error);
			}else{
				result.success("删除成功");
			}
		}else{
			result.wrong("请选择规格");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("toEditGoodsColor.html")
	public String toEditGoodsColor(Integer goodsId,Model model){
		List<Map<String, Object>> list = goodsService.getColorById(goodsId);
		model.addAttribute("colorList", list);
		model.addAttribute("goodsId", goodsId);
		return jspUrl("edit_goods_color");
	}
	
	@RequestMapping("addGoodsColor.do")
	public void addGoodsColor(Integer goodsId,String name,HttpServletRequest request,
			HttpServletResponse response){
		BizGoodsColor size = new BizGoodsColor();
		size.setCreateId(RequestUtil.getCurrentUserId(request));
		size.setCreateTime(new Date());
		size.setGoodsId(goodsId);
		size.setName(name);
		goodsService.addGoodsColor(size);
		Result result = new Result();
		result.success("新增成功");
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("deleteGoodsColor.do")
	public void deleteGoodsColor(Integer id,HttpServletResponse response){
		Result result = new Result();
		if(id != null){
			String error = goodsService.deleteGoodsColor(id);
			if(error != null){
				result.wrong(error);
			}else{
				result.success("删除成功");
			}
		}else{
			result.wrong("请选择颜色");
		}
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("toEditGoodsTexture.html")
	public String toEditGoodsTexture(Integer goodsId,Model model){
		List<Map<String, Object>> list = goodsService.getTextureById(goodsId);
		model.addAttribute("textureList", list);
		model.addAttribute("goodsId", goodsId);
		return jspUrl("edit_goods_texture");
	}
	
	@RequestMapping("addGoodsTexture.do")
	public void addGoodsTexture(Integer goodsId,String name,HttpServletRequest request,
			HttpServletResponse response){
		BizGoodsTexture size = new BizGoodsTexture();
		size.setCreateId(RequestUtil.getCurrentUserId(request));
		size.setCreateTime(new Date());
		size.setGoodsId(goodsId);
		size.setTextureName(name);
		goodsService.addGoodsTexture(size);
		Result result = new Result();
		result.success("新增成功");
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("deleteGoodsTexture.do")
	public void deleteGoodsTexture(Integer id,HttpServletResponse response){
		Result result = new Result();
		if(id != null){
			String error = goodsService.deleteGoodsTexture(id);
			if(error != null){
				result.wrong(error);
			}else{
				result.success("删除成功");
			}
		}else{
			result.wrong("请选择材质");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("toEditGoodsImg.html")
	public String toEditGoodsImg(Integer goodsId,Model model){
		List<BasImage> imageList = goodsService.getImageByGoodsId(goodsId);
		model.addAttribute("imageList", imageList);
		model.addAttribute("goodsId", goodsId);
		return jspUrl("edit_goods_img");
	}
	
	@RequestMapping("addGoodsImage.do")
	public void addGoodsImage(Integer goodsId,String path,Short type,HttpServletRequest request,
			HttpServletResponse response){
		//TODO
		Result result = new Result();
		if(StringHelper.isNull(path)){
			result.wrong("请上传商品图片");
		}else if(goodsId == null){
			result.wrong("请选择商品");
		}else{
			BasImage img = new BasImage();
			img.setCreateId(RequestUtil.getCurrentUserId(request));
			img.setCreateTime(new Date());
			img.setGoodsId(goodsId);
			img.setImagePath(path);
			img.setType(type);
			img.setSuffix(StringHelper.getSuffix(path));
			goodsService.addImage(img);
			result.success("新增成功");
		}
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("toEditGoodsAttr.html")
	public String toEditGoodsAttr(Integer goodsId,Model model){
		List<Option> list = goodsService.getAttrsByGoodsId(goodsId);
		model.addAttribute("attrList", list);
		model.addAttribute("goodsId", goodsId);
		return jspUrl("edit_goods_attr");
	}
	
	@RequestMapping("deleteGoodsAttr.do")
	public void deleteGoodsAttr(Integer id,HttpServletResponse response){
		goodsService.deleteGoodsAttr(id);
		Result result = new Result();
		result.success("删除成功");
		writeJson(result.getValues(), response);
	} 
	
	@RequestMapping("addGoodsAttr.do")
	public void addGoodsAttr(Integer goodsId,String attrKey,String attrVal ,HttpServletRequest request,
			HttpServletResponse response){
		BizGoodsAttrExt attr = new BizGoodsAttrExt();
		attr.setAttrName(attrKey);
		attr.setAttrValue(attrVal);
		attr.setGoodsId(goodsId);
		goodsService.addGoodsAttr(attr);
		Result result = new Result();
		result.success("新增成功");
		writeJson(result.getValues(), response);
		
	}
	
	@RequestMapping("deleteGoodsImg.do")
	public void deleteGoodsImg(Integer id,HttpServletResponse response){
		goodsService.deleteImage(id);
		Result result = new Result();
		result.success("删除成功");
		writeJson(result.getValues(), response);
	} 
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletRequest request,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			
			String webParentPath = new File(request.getSession().getServletContext().getRealPath("/")).getAbsolutePath();
			int result = goodsService.delete(id,webParentPath);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else{
				json = Json.genSuccess("删除成功");
			}
		}
		writeJson(json, response);
	}
	
	@RequestMapping("getGoodsColor.html")
	@ResponseBody
	public List<Map<String,Object>> getGoodsColor(Integer goodsId){
		
		if(goodsId == null || goodsId <= 0){
			return null;
		}
		return goodsService.getColorById(goodsId);
		
	}
	
	@RequestMapping("getGoodsSize.html")
	@ResponseBody
	public List<Map<String,Object>> getGoodsSize(Integer goodsId){
		
		if(goodsId == null || goodsId <= 0){
			return null;
		}
		return goodsService.getSizeById(goodsId);
		
	}
	
	@RequestMapping("getGoodsTexture.html")
	@ResponseBody
	public List<Map<String,Object>> getGoodsTexture(Integer goodsId){
		
		if(goodsId == null || goodsId <= 0){
			return null;
		}
		return goodsService.getTextureById(goodsId);
		
	}
	
	//商品下架
	@RequestMapping("goodsDown.html")
	public void goodsDown(Integer id,HttpServletResponse response){
		Result result = new Result();
		String error = goodsService.downGoods(id);
		if(error == null){
			result.success("下架成功");
		}else{
			result.wrong(error);
		}
		writeJson(result.getValues(), response);
	} 
	
	//商品上架
	@RequestMapping("goodsUp.html")
	public void goodsUp(Integer id,HttpServletResponse response){
		Result result = new Result();
		String error = goodsService.upGoods(id);
		if(error == null){
			result.success("删除成功");
		}else{
			result.wrong(error);
		}
		writeJson(result.getValues(), response);
	} 
	
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/goods/" + htmlUrl;
	}

}
