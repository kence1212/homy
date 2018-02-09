package com.quantum.engine.homy.action.scene;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.CreateSceneDto;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizSceneInitService;
import com.quantum.engine.homy.service.UserService;

@Controller
@RequestMapping("/sceneinit")
public class SceneAct extends BaseAct {
	
	@Autowired
	private BizSceneInitService bizSceneInitService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/create.do")
	public void create(CreateSceneDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizSceneInitService.create(dto.getName(),dto.getSceneInfo(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("创建成功");
		writeJson(br, response);
		
		
	}
	
	public void list(HttpServletResponse response){
		//当前用户的列表 TODO 
	} 

}
