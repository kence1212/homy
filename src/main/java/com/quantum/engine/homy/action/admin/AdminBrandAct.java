package com.quantum.engine.homy.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.GoodsBrandService;

@Controller
@RequestMapping("/ambrand")
public class AdminBrandAct extends BaseAct {
	
	@Autowired
	private GoodsBrandService goodsBrandService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BasGoodsBrand> brandList = goodsBrandService.listAll();
		model.addAttribute("brandList", brandList);
		return jspUrl("list");
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(){
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public String add(BasGoodsBrand brand,HttpServletRequest request){
		brand.setCreateId(RequestUtil.getCurrentUserId(request));
		goodsBrandService.add(brand);
		return "redirect:index.html";
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BasGoodsBrand brand = goodsBrandService.getById(id);
		if(brand == null){
			redirectIndex();
		}
		model.addAttribute("bean", brand);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public String edit(BasGoodsBrand brand,HttpServletRequest request){
		brand.setModifyId(RequestUtil.getCurrentUserId(request));
		goodsBrandService.update(brand);
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
			int result = goodsBrandService.delete(id);
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
		return "jsp/admin/brand/" + htmlUrl;
	}
	
}
