package com.quantum.engine.homy.action.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.dto.BindPhoneDto;
import com.quantum.engine.homy.model.dto.OtherLoginDto;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizOtherLoginService;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.IPs;

@Controller
@RequestMapping("/other_login")
public class OtherLoginAct extends BaseAct {

	@Autowired
	BizOtherLoginService bizOtherLoginService;
	@Autowired
	UserService userService;
	
	@RequestMapping("/login.do")
	public void login(OtherLoginDto dto, HttpServletResponse response) {
		
		// openid , unionid , type (0.微信 ,1.qq) , portrait , nickname
		Result validate = dto.validate();
		if (validate.getKey("errorMsg") != null) {
			Map<String, Object> values = validate.getValues();
			writeJson(values, response);
			return ;
		}

		// 登录
		Map<String, Object> msg = bizOtherLoginService.login(dto.getOpenid(),dto.getUnionid(),dto.getType(),dto.getPortrait(),dto.getNickname());
		BaseResponse br = BaseResponse.getSuccess("登录成功");
		Map<String, Object> map = new HashMap<>();
		int state = 0;
		if(msg.get("loginToken") != null){
			//未绑定用户
			map.put("loginToken", msg.get("loginToken"));
			map.put("otherLoginId", msg.get("otherLoginId"));
			state = 0;//未绑定用户  需要绑定手机号
			
		} else{
			//已绑定用户返回userId和token
			map.put("userId", msg.get("userId"));
			map.put("token", msg.get("token"));
			state = 1;//绑定用户 有手机号
		}
		map.put("state", state);
		br.setObj(map);
		writeJson(br, response);
	}

	@RequestMapping("/bindPhone.do")
	public void bindPhone(BindPhoneDto dto,HttpServletRequest request ,HttpServletResponse response) {
		
		Result validate = dto.validate();
		if (validate.getKey("errorMsg") != null) {
			Map<String, Object> values = validate.getValues();
			writeJson(values, response);
			return ;
		}
		
		//绑定手机号
		String ip = IPs.getIpAddr(request);
		 Map<String, Object> msg = bizOtherLoginService.bindPhone(dto.getPhone(),dto.getCode(),dto.getLoginToken(),dto.getOtherLoginId(),ip);
		if(msg.get("errorMsg") != null){
			validate.wrong("40000", (String)msg.get("errorMsg"));
			writeJson(validate.getValues(), response);
			return ;
		}
		
		BaseResponse br = BaseResponse.getSuccess("绑定成功");
		String generateToken = userService.generateToken((int)msg.get("userId"));
		Map<String, Object> map = new HashMap<>();
		map.put("token", generateToken);
		map.put("userId", (int)msg.get("userId"));
		br.setObj(map);
		writeJson(br, response);
		
	}

}
