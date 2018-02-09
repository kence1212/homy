package com.quantum.engine.homy.action.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.GoodsStyleService;

@Controller
@RequestMapping("/amstyle")
public class AdminGoodsStyleAct extends BaseAct {
	
	@Autowired
	private GoodsStyleService goodsBrandService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BizGoodsStyle> goodsStyleList = goodsBrandService.listAll();
		model.addAttribute("goodsStyleList", goodsStyleList);
		return jspUrl("list");
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		
		List<Map<String,Object>> options = goodsBrandService.getOption();
		model.addAttribute("options",options);
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public String add(BizGoodsStyle goodsStyle,HttpServletRequest request){
		goodsStyle.setCreateId(RequestUtil.getCurrentUserId(request));
		goodsBrandService.add(goodsStyle);
		return "redirect:index.html";
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsStyle goodsStyle = goodsBrandService.getById(id);
		List<Map<String,Object>> options = goodsBrandService.getOptionWithOutSelf(id);
		if(goodsStyle == null){
			redirectIndex();
		}
		model.addAttribute("bean", goodsStyle);
		model.addAttribute("options", options);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public String edit(BizGoodsStyle goodsStyle,HttpServletRequest request){
		goodsStyle.setModifyId(RequestUtil.getCurrentUserId(request));
		goodsBrandService.update(goodsStyle);
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
		return "jsp/admin/goods_style/" + htmlUrl;
	}
	
}
