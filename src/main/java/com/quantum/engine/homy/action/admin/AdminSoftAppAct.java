package com.quantum.engine.homy.action.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasAppSoft;
import com.quantum.engine.homy.model.BizGoodsBoutique;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BasAppSoftService;

/**
 * 
 * @author nicya
 * @date 2017年11月25日 上午10:18:25
 */
@Controller
@RequestMapping("/adapp")
public class AdminSoftAppAct extends BaseAct {
	
	@Autowired
	private BasAppSoftService basAppSoftService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		List<BasAppSoft> list = basAppSoftService.getAll();
		model.addAttribute("list", list);
		return jspUrl("list");
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(){
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public void add(BasAppSoft soft,HttpServletRequest request,HttpServletResponse response){
		
		soft.setCreateId(RequestUtil.getCurrentUserId(request));
		soft.setCreateTime(new Date());
		
		Json json =	basAppSoftService.add(soft);
		writeJson(json, response);
		
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = basAppSoftService.delete(id);
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
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null || id <= 0){
			return redirectIndex();
		}
		BasAppSoft basAppSoft = basAppSoftService.getById(id);
		if(basAppSoft == null){
			redirectIndex();
		}
		model.addAttribute("bean", basAppSoft);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public void edit(BasAppSoft soft,HttpServletResponse response){
		Json json = basAppSoftService.update(soft);
		writeJson(json, response);
	}
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/app_soft/" + htmlUrl;
	}

}
