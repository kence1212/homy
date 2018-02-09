package com.quantum.engine.homy.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.region.Bean;
import com.quantum.engine.homy.region.FormatUtil;
import com.quantum.engine.homy.service.BasRegionService;

@Controller
public class IndexAct extends BaseAct {
	
	@Autowired
	private BasRegionService regionService;
	
	@RequestMapping("/index.html")
	@ResponseBody
	public String index(HttpServletResponse response){
		return "hello homy";
	}
	
	@RequestMapping("/getCity.do")
	@ResponseBody
	public List<Bean> getCity(){
		List<BasRegion> all = regionService.getAll();
		return FormatUtil.parseRegion(all);
	}
	
}
