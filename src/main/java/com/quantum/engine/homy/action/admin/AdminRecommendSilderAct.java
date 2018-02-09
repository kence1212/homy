package com.quantum.engine.homy.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizRecommendSilder;
import com.quantum.engine.homy.model.ext.BizRecommendSilderExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.RecommendSilderService;

@Controller
@RequestMapping("/silder")
public class AdminRecommendSilderAct extends BaseAct {
	
	@Autowired
	private RecommendSilderService recommendSilderService;
	
	@Autowired
	private BizGoodsService bizGoodsService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BizRecommendSilderExt> listAll = recommendSilderService.listAllWithGood();
		model.addAttribute("listAll", listAll);
		return jspUrl("list");
	}
	@RequestMapping("classify.html")
	public String turnToClassify(){
		return jspUrl("formpoup"); 
	}
	
	@RequestMapping("pageJson.html")
	public void getPageJson(HttpServletResponse response,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="")String keyword){
		Page<BizGoodsBase> pageRes = bizGoodsService.queryList(page,pageSize,keyword);
		writeJson(pageRes, response);
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public String add(BizRecommendSilder recommendSilder,HttpServletRequest request){
		recommendSilder.setCreateId(RequestUtil.getCurrentUserId(request));
		recommendSilderService.add(recommendSilder);
		return "redirect:index.html";
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizRecommendSilder recommendSilder = recommendSilderService.getById(id);
		if(recommendSilder == null){
			redirectIndex();
		}
		model.addAttribute("bean", recommendSilder);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public String edit(BizRecommendSilder recommendSilder,HttpServletRequest request){
		recommendSilder.setModifyId(RequestUtil.getCurrentUserId(request));
		recommendSilderService.update(recommendSilder);
		return "redirect:index.html";
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = recommendSilderService.delete(id);
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
		return "jsp/admin/recommend_silder/" + htmlUrl;
	}
	
}
