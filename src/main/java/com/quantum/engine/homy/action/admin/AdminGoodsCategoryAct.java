package com.quantum.engine.homy.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizGoodsCategory;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BizGoodsCategoryService;

@Controller
@RequestMapping("/amgoodscate")
public class AdminGoodsCategoryAct extends BaseAct {
	
	@Autowired
	private BizGoodsCategoryService goodsCategoryService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BizGoodsCategory> categoryList = goodsCategoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		return jspUrl("list");
	} 
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		List<BizGoodsCategory> categoryList = goodsCategoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		return jspUrl("add");
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsCategory category = goodsCategoryService.getById(id);
		if(category == null){
			return redirectIndex();
		}
		model.addAttribute("bean", category);
		List<BizGoodsCategory> categoryList = goodsCategoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		return jspUrl("edit");
	}
	
	@RequestMapping("add.html")
	public String add(BizGoodsCategory category,HttpServletRequest request){
		category.setCreateId(RequestUtil.getCurrentUserId(request));
		goodsCategoryService.add(category);
		return redirectIndex();
	}
	
	@RequestMapping("edit.html")
	public String edit(BizGoodsCategory category,HttpServletRequest request){
		category.setCreateId(RequestUtil.getCurrentUserId(request));
		goodsCategoryService.update(category);
		return redirectIndex();
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = goodsCategoryService.delete(id);
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
	
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/goods_category/" + htmlUrl;
	}
	
	

}
