package com.quantum.engine.homy.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.SysRole;
import com.quantum.engine.homy.model.dto.SysRoleDto;
import com.quantum.engine.homy.model.ext.SysPermissionModelExt;
import com.quantum.engine.homy.model.ext.SysRoleExt;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.SysPermissionModelService;
import com.quantum.engine.homy.service.SysRoleService;

@Controller
@RequestMapping("/amrole")
public class AdminSysRoleAct extends BaseAct {
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPermissionModelService sysPermissionModelService;
	@RequestMapping("index.html")
	public String index(Model model){
		List<SysRole> list = sysRoleService.listAll();
		model.addAttribute("list", list);
		return jspUrl("list");
	}
	
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		
		List<SysPermissionModelExt> modelList = sysPermissionModelService.getModelList();
		model.addAttribute("modelList", modelList);
		return jspUrl("add");
		
	}
	
	@RequestMapping("add.html")
	public String add(SysRoleDto dto,HttpServletRequest request){
		
		SysRole sysRole = new SysRole();
		sysRole.setCreateId(RequestUtil.getCurrentUserId(request));
		sysRole.setRoleCode(dto.getRoleCode());
		sysRole.setRoleName(dto.getRoleName());
		sysRoleService.add(sysRole,dto.getPermissions());
		return "redirect:index.html";
		
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		SysRoleExt sysRole = sysRoleService.getById(id);
		if(sysRole == null){
			redirectIndex();
		}
		List<SysPermissionModelExt> modelList = sysPermissionModelService.getModelList();
		model.addAttribute("modelList", modelList);
		model.addAttribute("bean", sysRole);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public String edit(SysRoleDto dto,HttpServletRequest request){
		
		SysRole sysRole = new SysRole();
		sysRole.setId(dto.getId());
		sysRole.setModifyId(RequestUtil.getCurrentUserId(request));
		sysRole.setRoleCode(dto.getRoleCode());
		sysRole.setRoleName(dto.getRoleName());
		sysRoleService.update(sysRole,dto.getPermissions());
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
			int result = sysRoleService.delete(id);
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
		return "jsp/admin/sys_role/" + htmlUrl;
	}
	
}
