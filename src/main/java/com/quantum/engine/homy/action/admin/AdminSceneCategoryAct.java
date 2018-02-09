package com.quantum.engine.homy.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.model.BizSceneCategory;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BizSceneCategoryService;

@Controller
@RequestMapping("/amscenecategory")
public class AdminSceneCategoryAct extends BaseAct {
	

	
	@Autowired
	private BizSceneCategoryService bizSceneCategoryService;
	
	
	@RequestMapping("index.html")
	public String index(Model model){
		return jspUrl("list");
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizSceneCategory> getList(PageListQueryDto dto, HttpServletResponse response){
		
		if(!dto.validate()){
			return Page.getNullPage();
		}
		
		Page<BizSceneCategory> page =  bizSceneCategoryService.getList(dto.getKeyword(),dto.getPage(),dto.getPageSize());
		return page;
		
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public void add(BizSceneCategory bizSceneCategory,HttpServletRequest request,HttpServletResponse response){
		
		
		User current = RequestUtil.getCurrent(request);
		bizSceneCategory.setCreateId(current.getUserId());
		bizSceneCategory.setCreateTime(new Date());
		bizSceneCategory.setIsValid(Constants.VALID);
		 bizSceneCategoryService.add(bizSceneCategory);
		 BaseResponse br = BaseResponse.getSuccess("新增成功");
		 writeJson(br, response);
		 
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizSceneCategory bizSceneCategory = bizSceneCategoryService.getById(id);
		if(bizSceneCategory == null){
			return redirectIndex();
		}
		model.addAttribute("bean", bizSceneCategory);
		return jspUrl("edit");
		
	}
	
	@RequestMapping("edit.html")
	public void edit(BizSceneCategory bizSceneCategory,HttpServletRequest request,HttpServletResponse response){
		
		User current = RequestUtil.getCurrent(request);
		bizSceneCategory.setModifyId(current.getUserId());
		bizSceneCategory.setMoidfyTime(new Date());
		 bizSceneCategoryService.update(bizSceneCategory);
		 BaseResponse br = BaseResponse.getSuccess("编辑成功");
		 writeJson(br, response);
		 
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = bizSceneCategoryService.delete(id);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else if(result == 1){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("还有场景风格关联此分类无法删除");
			}else {
				json = Json.genSuccess("删除成功");
			}
		}
		writeJson(json, response);
	}
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/scene_category/" + htmlUrl;
	}
	
	
}
