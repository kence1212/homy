package com.quantum.engine.homy.action.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.service.BizOrderRefundService;

@Controller
@RequestMapping("/amorderRefund")
public class AdminOrderRefundAct extends BaseAct {
	
	@Autowired
	private BizOrderRefundService bizOrderRefundService;
	
	@RequestMapping("index.html")
	public String index(Model model){
		return jspUrl("list");
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizOrderRefund> getList(@RequestParam(defaultValue = "1")Integer page, 
			@RequestParam(defaultValue = "10")Integer pageSize){
		Page<BizOrderRefund> pageData = new Page<BizOrderRefund>(0, 0, 0);
		if(page <= 0 || pageSize <= 0){
			pageData.setList(new ArrayList<BizOrderRefund>());
		}else{
			pageData = bizOrderRefundService.page(page,pageSize);
		}
		return pageData;
		
	}
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/biz_order_refund/" + htmlUrl;
	}

}
