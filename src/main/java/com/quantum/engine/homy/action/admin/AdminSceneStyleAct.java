package com.quantum.engine.homy.action.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizSceneCategory;
import com.quantum.engine.homy.model.BizSceneInit;
import com.quantum.engine.homy.model.BizSceneStyle;
import com.quantum.engine.homy.model.dto.BizSceneStyleDto;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.ext.BizSceneStyleExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizSceneCategoryService;
import com.quantum.engine.homy.service.BizSceneInitService;
import com.quantum.engine.homy.service.BizSceneStyleService;

@Controller
@RequestMapping("/amscenestyle")
public class AdminSceneStyleAct extends BaseAct {
	

	
	@Autowired
	private BizSceneStyleService bizSceneStyleService;
	
	@Autowired
	private BizSceneInitService bizSceneInitService;
	
	@Autowired
	BizSceneCategoryService bizSceneCategoryService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		return jspUrl("list");
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizSceneStyleExt> getList(PageListQueryDto dto, HttpServletResponse response){
		
		if(!dto.validate()){
			return Page.getNullPage();
		}
		
		Page<BizSceneStyleExt> page =  bizSceneStyleService.getList(dto.getKeyword(),dto.getPage(),dto.getPageSize());
		return page;
		
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model){
		
		List<BizSceneInit> sceneInits = bizSceneInitService.getList();
		Page<BizSceneCategory> sceneCategory = bizSceneCategoryService.getList("", 0, 0);
		model.addAttribute("sceneCategory", sceneCategory.getList());
		model.addAttribute("sceneInits", sceneInits);
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	public void add(BizSceneStyleDto dto,HttpServletRequest request,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		BizSceneStyle bizSceneStyle = new BizSceneStyle();
		bizSceneStyle.setTitle(dto.getTitle());
		bizSceneStyle.setCreateTime(new Date());
		bizSceneStyle.setIcon(dto.getIcon());
		bizSceneStyle.setSceneDesc(dto.getSceneDesc());
		bizSceneStyle.setSort(dto.getSort());
		bizSceneStyle.setState(BizSceneStyle.STATE_NORMAL);
		bizSceneStyle.setCreateId(RequestUtil.getCurrent(request).getUserId());
		bizSceneStyle.setSceneCategoryId(dto.getSceneCategoryId());
		
		 bizSceneStyleService.add(bizSceneStyle,dto.getSceneInitId());
		 BaseResponse br = BaseResponse.getSuccess("新增成功");
		 writeJson(br, response);
		 
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		List<BizSceneInit> sceneInits = bizSceneInitService.getList();
		BizSceneStyle bizSceneStyle = bizSceneStyleService.getById(id);
		if(bizSceneStyle == null){
			return redirectIndex();
		}
		Page<BizSceneCategory> sceneCategory = bizSceneCategoryService.getList("", 0, 0);
		model.addAttribute("sceneCategory", sceneCategory.getList());
		model.addAttribute("sceneInits", sceneInits);
		model.addAttribute("bean", bizSceneStyle);
		return jspUrl("edit");
		
	}
	
	@RequestMapping("edit.html")
	public void edit(BizSceneStyleDto dto,HttpServletRequest request,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		BizSceneStyle bizSceneStyleInDB = bizSceneStyleService.getById(dto.getId());
		if(bizSceneStyleInDB == null){
			result.wrong("无此ID");
			writeJson(result.getValues(), response);
			return;
		}
		bizSceneStyleInDB.setIcon(dto.getIcon());
		bizSceneStyleInDB.setSceneDesc(dto.getSceneDesc());
		bizSceneStyleInDB.setSort(dto.getSort());
		bizSceneStyleInDB.setTitle(dto.getTitle());
		bizSceneStyleInDB.setSceneCategoryId(dto.getSceneCategoryId());
		
		 bizSceneStyleService.update(bizSceneStyleInDB,dto.getSceneInitId());
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
			int result = bizSceneStyleService.delete(id);
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
		return "jsp/admin/scene_style/" + htmlUrl;
	}
	
	@RequestMapping("up.html")
	public void up(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = bizSceneStyleService.setState(id,BizSceneStyle.STATE_NORMAL);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else {
				json = Json.genSuccess("上架成功");
			}
		}
		writeJson(json, response);
	}
	
	@RequestMapping("down.html")
	public void down(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = bizSceneStyleService.setState(id,BizSceneStyle.STATE_DOWN);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else {
				json = Json.genSuccess("下架成功");
			}
		}
		writeJson(json, response);
	}

}
