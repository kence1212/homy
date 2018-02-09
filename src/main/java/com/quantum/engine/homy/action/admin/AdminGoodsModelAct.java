package com.quantum.engine.homy.action.admin;

import java.util.ArrayList;
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
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsModelService;

@Controller
@RequestMapping("/ammodel")
public class AdminGoodsModelAct extends BaseAct {
	
	@Autowired
	private GoodsModelService goodsModelService;
	
	@Autowired
	private BizGoodsService bizGoodsService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		return jspUrl("list");
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizGoodsModelExt> getList(PageListQueryDto dto){
		
		boolean validate = dto.validate();
		if(!validate){
			 Page<BizGoodsModelExt> page = new Page<>(0, 0, 0);
			 page.setList(new ArrayList<BizGoodsModelExt>());
			 return page;
		}
		
		return goodsModelService.getList(dto);
		
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
	public void add(BizGoodsModel goodsModel,HttpServletRequest request,HttpServletResponse response){
		goodsModel.setCreateId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = goodsModelService.add(goodsModel,request);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsModelExt goodsModel = goodsModelService.getById(id);
		if(goodsModel == null){
			redirectIndex();
		}
		model.addAttribute("bean", goodsModel);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public void edit(BizGoodsModel goodsModel,HttpServletRequest request,HttpServletResponse response){
		goodsModel.setModifyId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = goodsModelService.update(goodsModel,request);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("up.do")
	public void up(Integer id,HttpServletResponse response){
		Json json = new Json();
		BizGoodsModel model = goodsModelService.getById(id);
		if(model == null){
			json.setSuccess(false);
			json.setMsg("模型ID错误");
		}else if(model.getState() != null && model.getState() == BizGoodsModel.MODEL_STATE_NORMAL){
			json.setSuccess(false);
			json.setMsg("模型已经是上架状态");
		}else{
			model.setState(BizGoodsModel.MODEL_STATE_NORMAL);
			goodsModelService.updateState(model);
			json.setSuccess(true);
			json.setMsg("上架成功");
		}
		writeJson(json, response);
	}
	
	@RequestMapping("down.do")
	public void down(Integer id,HttpServletResponse response){
		Json json = new Json();
		BizGoodsModel model = goodsModelService.getById(id);
		if(model == null){
			json.setSuccess(false);
			json.setMsg("模型ID错误");
		}else if(model.getState() != null && model.getState() == BizGoodsModel.MODEL_STATE_DOWN){
			json.setSuccess(false);
			json.setMsg("模型已经是下架状态");
		}else{
			model.setState(BizGoodsModel.MODEL_STATE_DOWN);
			goodsModelService.updateState(model);
			json.setSuccess(true);
			json.setMsg("上架成功");
		}
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
			int result = goodsModelService.delete(id);
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
		return "jsp/admin/goods_model/" + htmlUrl;
	}
	
}
