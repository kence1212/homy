package com.quantum.engine.homy.action.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.SysPermission;
import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.ext.SysPermissionExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.SysPermissionService;

@Controller
@RequestMapping("/adpermission")
public class AdminSysPermissionAct extends BaseAct{

	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping("getSecondModel.html")
	@ResponseBody
	public List<Map<String, Object>> getSecondModel(Integer pid){
		
		if(pid == null || pid <= 0){
			return null;
		}
		
		return sysPermissionService.getSecondModel(pid);
		
	}
	
	@RequestMapping("index.html")
	public String index(Model model,Integer modelId){
		model.addAttribute("modelId", modelId);
		return jspUrl("list");
	}
	
	@RequestMapping("getList.html")
	@ResponseBody
	public Page<SysPermissionExt> getList(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "")String keyword, Integer modelId){
		
		if(modelId == null || modelId <= 0){
			modelId = null;
		}
		
		if(page <=0 || pageSize <=0){
			
			return Page.getNullPage();
			
		}
		return sysPermissionService.getList(page, pageSize, keyword, modelId);
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model,Integer modelId){
		
		/*List<Map<String,Object>> models =  sysPermissionService.getTopPermissionModel();
		model.addAttribute("models", models);*/
		model.addAttribute("modelId", modelId);
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public void add(SysPermission sysPermission,HttpServletRequest request,HttpServletResponse response){
		
		sysPermission.setCreateId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = sysPermissionService.add(sysPermission);
		writeJson(baseResponse, response);
		
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		SysPermission permission = sysPermissionService.getById(id);
		/*List<Map<String,Object>> models =  sysPermissionService.getTopPermissionModel();*/
		
		if(permission == null){
			redirectIndex();
		}
		model.addAttribute("bean", permission);
	/*	model.addAttribute("models", models);*/
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public void edit(SysPermission sysPermission,HttpServletRequest request,HttpServletResponse response){
		sysPermission.setModifyId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = sysPermissionService.update(sysPermission);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = sysPermissionService.delete(id);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else {
				json = Json.genSuccess("删除成功");
			}
		}
		writeJson(json, response);
	}
	
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/sys_permission/" + htmlUrl;
	}
	
}
