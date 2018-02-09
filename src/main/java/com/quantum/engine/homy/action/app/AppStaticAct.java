package com.quantum.engine.homy.action.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/static")
public class AppStaticAct {
	
	@RequestMapping("/{code}.html")
	public String protocol(@PathVariable("code")String code){
		return "jsp/app/static/" + code;
	}

}
