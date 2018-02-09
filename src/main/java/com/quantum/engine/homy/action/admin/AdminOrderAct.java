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
import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderHandlerLogExt;
import com.quantum.engine.homy.model.ext.BizOrderListExt;
import com.quantum.engine.homy.model.ext.UserAddressExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.BizOrderHandlerLogService;
import com.quantum.engine.homy.service.BizOrderService;
import com.quantum.engine.homy.service.UserAddressService;
import com.quantum.engine.homy.util.OrderUtil;

@Controller
@RequestMapping("/amorder")
public class AdminOrderAct extends BaseAct {
	
	@Autowired
	private BizOrderService orderService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	BizOrderHandlerLogService bizOrderHandlerLogService;
	@RequestMapping("index.html")
	public String index(Model model){
		return jspUrl("list");
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizOrderListExt> getList(@RequestParam(defaultValue = "1")Integer page, 
			@RequestParam(defaultValue = "10")Integer pageSize,
			String orderNo, String outTradeNo, Integer state){
		
		if(page <= 0 || pageSize <= 0){
			 Page<BizOrderListExt> pageData = new Page<>(0, 0, 0);
			 pageData.setList(new ArrayList<BizOrderListExt>());
			 return pageData;
		}
		
		return orderService.getList(page,pageSize,orderNo,outTradeNo,state);
		
	}
	
	@RequestMapping("/confirmDelivery.html")
	public void confirmDelivery(Integer id,HttpServletResponse response,HttpServletRequest request){
		
		Json json = new Json();
		if(id == null || id <= 0){
			json.setSuccess(false);
			json.setMsg("ID不能为空");
			writeJson(json, response);
			return;
		}
		Integer currentUserId = RequestUtil.getCurrentUserId(request);
		int count = orderService.confirmDelivery(id,OrderUtil.ORDER_STATE_SENDED, currentUserId);
		if(count == 1){
			json.setSuccess(true);
			json.setMsg("确认成功");
		} else{
			json.setSuccess(false);
			json.setMsg("确认失败");
		}
		writeJson(json, response);
		
	}
	
	@RequestMapping("toDetail.html")
	public String toDetail(Integer id, Model model){
		if(id == null){
			return redirectIndex();
		}
		BizOrderExt order = orderService.getDetail(id);
		order.setStateStr();
		UserAddressExt addr = userAddressService.getAddrExt(order.getAddressId());
		List<BizOrderHandlerLogExt> logs = bizOrderHandlerLogService.getLogByOrderId(id);
		for (BizOrderHandlerLogExt bizOrderHandlerLogExt : logs) {
			bizOrderHandlerLogExt.setFinOrderStateStr(OrderUtil.getStateStr(bizOrderHandlerLogExt.getFinOrderState()));
		}
		model.addAttribute("order", order);
		model.addAttribute("address", addr);
		model.addAttribute("logs", logs);
		return jspUrl("detail");
	}
	
	
	/*@RequestMapping("classify.html")
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
		BaseResponse baseResponse = orderService.add(goodsModel,request);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsModelExt goodsModel = orderService.getById(id);
		if(goodsModel == null){
			redirectIndex();
		}
		model.addAttribute("bean", goodsModel);
		return jspUrl("edit");
	}
	
	@RequestMapping("edit.html")
	public void edit(BizGoodsModel goodsModel,HttpServletRequest request,HttpServletResponse response){
		goodsModel.setModifyId(RequestUtil.getCurrentUserId(request));
		BaseResponse baseResponse = orderService.update(goodsModel,request);
		writeJson(baseResponse, response);
	}
	
	@RequestMapping("up.do")
	public void up(Integer id,HttpServletResponse response){
		Json json = new Json();
		BizGoodsModel model = orderService.getById(id);
		if(model == null){
			json.setSuccess(false);
			json.setMsg("模型ID错误");
		}else if(model.getState() != null && model.getState() == BizGoodsModel.MODEL_STATE_NORMAL){
			json.setSuccess(false);
			json.setMsg("模型已经是上架状态");
		}else{
			model.setState(BizGoodsModel.MODEL_STATE_NORMAL);
			orderService.updateState(model);
			json.setSuccess(true);
			json.setMsg("上架成功");
		}
		writeJson(json, response);
	}
	
	@RequestMapping("down.do")
	public void down(Integer id,HttpServletResponse response){
		Json json = new Json();
		BizGoodsModel model = orderService.getById(id);
		if(model == null){
			json.setSuccess(false);
			json.setMsg("模型ID错误");
		}else if(model.getState() != null && model.getState() == BizGoodsModel.MODEL_STATE_DOWN){
			json.setSuccess(false);
			json.setMsg("模型已经是下架状态");
		}else{
			model.setState(BizGoodsModel.MODEL_STATE_DOWN);
			orderService.updateState(model);
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
			int result = orderService.delete(id);
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
	*/

	protected String jspUrl(String htmlUrl){
		return "jsp/admin/biz_order/" + htmlUrl;
	}
}
