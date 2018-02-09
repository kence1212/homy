package com.quantum.engine.homy.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsBoutique;
import com.quantum.engine.homy.model.ext.BasRegionExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BasRegionService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsBoutiqueService;

@Controller
@RequestMapping("/amregion")
public class AdminRegionAct extends BaseAct{

	@Autowired
	BasRegionService basRegionService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BasRegionExt> listAll = basRegionService.hotCitylist();
		model.addAttribute("listAll", listAll);
		return jspUrl("list");
	}
	
	@RequestMapping("classify.html")
	public String turnToClassify(){
		return jspUrl("formpoup"); 
	}
	
	@RequestMapping("pageJson.html")
	public void getPageJson(HttpServletResponse response,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5")int pageSize,@RequestParam(defaultValue="")String keyword){
		Page<BasRegionExt> pageRes = basRegionService.queryList(page,pageSize,keyword);
		writeJson(pageRes, response);
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public String add(Integer id ,HttpServletRequest request){
		if(id == null || id <= 0){
			return redirectIndex();
		}
		basRegionService.setHotCityById(id);
		return "redirect:index.html";
	}
	
	/*@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsBoutique bizGoodsBoutique = basRegionService.getById(id);
		if(bizGoodsBoutique == null){
			redirectIndex();
		}
		model.addAttribute("bean", bizGoodsBoutique);
		return jspUrl("edit");
	}*/
	
	/*@RequestMapping("edit.html")
	public String edit(BizGoodsBoutique bizGoodsBoutique,HttpServletRequest request){
		bizGoodsBoutique.setModifyId(RequestUtil.getCurrentUserId(request));
		basRegionService.update(bizGoodsBoutique);
		return "redirect:index.html";
	}*/
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = basRegionService.removeHotCityById(id);
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
		return "jsp/admin/hot_region/" + htmlUrl;
	}
	
	
	@RequestMapping("getRegion.do")
	@ResponseBody
	public List<BasRegion> getRegion(@RequestParam(defaultValue="0")int pid){
		return basRegionService.getRegion(pid);
	}
	
	@RequestMapping("getParentRegionByPid.do")
	@ResponseBody
	public BasRegion getParentRegionByPid(Integer id){
		
		if(id == null || id <= 0){
			return null;
		}
		
		return basRegionService.getParentRegionById(id);
		
		
	}
	
	
}
