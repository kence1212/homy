package com.quantum.engine.homy.action.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizStore;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.service.BizStoreService;

@Controller
@RequestMapping("/amstore")
public class AdminStoreAct extends BaseAct {
	
	@Autowired
	private BizStoreService bizStoreService;
	
	@RequestMapping("/index.html")
	public String index(Model model){
		List<BizStore> list = bizStoreService.getAll();
		model.addAttribute("list", list);
		return jspUrl("list");
	}
	
	@RequestMapping("/toAdd.html")
	public String toAdd(){
		return jspUrl("add");
	}
	
	@RequestMapping("/add.html")
	public String add(BizStore store,HttpServletRequest request){
		store.setCreateId(RequestUtil.getCurrentUserId(request));
		store.setCreateTime(new Date());
		bizStoreService.add(store);
		return redirectIndex();
	}
	
	@RequestMapping("/toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizStore store = bizStoreService.getById(id);
		if(store == null){
			return redirectIndex();
		}
		model.addAttribute("bean", store);
		return jspUrl("edit");
	}
	
	@RequestMapping("/edit.html")
	public String edit(BizStore store,HttpServletRequest request){
		store.setModifyId(RequestUtil.getCurrentUserId(request));
		bizStoreService.update(store);
		return redirectIndex();
	}
	
	protected String jspUrl(String url) {
		return "jsp/admin/store/" + url;
	}

}
