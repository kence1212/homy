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
import com.quantum.engine.homy.model.SysPermissionModel;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.SysPermissionModelService;

@Controller
@RequestMapping("/ampmodel")
public class AdminPermissionModelAct extends BaseAct {
	
	@Autowired
	private SysPermissionModelService permissionModelService;
	
	@RequestMapping("getById.html")
	@ResponseBody
	public SysPermissionModel getById(Integer id){
		if(id == null || id <=0 ){
			return null;
		}
		return permissionModelService.getById(id);
	}
	
	@RequestMapping("index.html")
	public String index(Model model){
		
		/*List<SysPermissionModelExt> list = permissionModelService.listAll();
		model.addAttribute("list", list);*/
		return jspUrl("list");
		
	}
	
	@RequestMapping("getList.html")
	@ResponseBody
	public Page<SysPermissionModelExt> getList(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "")String keyword){
		
		if(page <=0 || pageSize <=0){
			
			return Page.getNullPage();
			
		}
		
		return permissionModelService.getList(page, pageSize, keyword);
		
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		List<Map<String,Object>> pModels = permissionModelService.getPModels(null);
		model.addAttribute("pModels", pModels);
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public void add(SysPermissionModel permissionModel,HttpServletRequest request,HttpServletResponse response){
		permissionModel.setCreateId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = permissionModelService.add(permissionModel);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		SysPermissionModel permissionModel = permissionModelService.getById(id);
		List<Map<String,Object>> pModels = permissionModelService.getPModels(id);
		
		if(permissionModel == null){
			redirectIndex();
		}
		model.addAttribute("bean", permissionModel);
		model.addAttribute("pModels", pModels);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public void edit(SysPermissionModel permissionModel,HttpServletRequest request,HttpServletResponse response){
		permissionModel.setModifyId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = permissionModelService.update(permissionModel);
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
			int result = permissionModelService.delete(id);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else if (result == 2){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("该模块还有子模块不能删除");
			}else {
				json = Json.genSuccess("删除成功");
			}
		}
		writeJson(json, response);
	}
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/sys_permission_model/" + htmlUrl;
	}
	
}
