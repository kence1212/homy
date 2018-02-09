package com.quantum.engine.homy.action.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizPaint;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.service.BizPaintService;

@Controller
@RequestMapping("/ampaint")
public class AdminPaintAct extends BaseAct {
	
	@Autowired
	private BizPaintService bizPaintService;
	
	@RequestMapping("/index.html")
	public String index(Model model){
		List<BizPaint> list = bizPaintService.getAll();
		model.addAttribute("list", list);
		return jspUrl("list");
	}
	
	@RequestMapping("/toAdd.html")
	public String toAdd(){
		return jspUrl("add");
	}
	
	@RequestMapping("/toEdit.html")
	public String toEdit(Integer id,Model model){
		BizPaint paint = bizPaintService.getById(id);
		model.addAttribute("bean", paint);
		return jspUrl("edit");
	}
	
	@RequestMapping("/add.html")
	public String add(BizPaint bizPaint,HttpServletRequest request){
		bizPaint.setCreateId(RequestUtil.getCurrentUserId(request));
		bizPaint.setCreateTime(new Date());
		bizPaintService.add(bizPaint);
		return redirectIndex();
	}
	
	@RequestMapping("/edit.html")
	public String edit(BizPaint bizPaint,HttpServletRequest request){
		bizPaint.setModfiyId(RequestUtil.getCurrentUserId(request));
		bizPaintService.update(bizPaint);
		return redirectIndex();
	}

	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/goods_paint/" + htmlUrl;
	}
}
