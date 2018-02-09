package com.quantum.engine.homy.action.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.MD5FileUtil;
import com.quantum.engine.homy.util.PasswordUtil;

@Controller
@RequestMapping("/qem")
public class AdminLoginAct extends BaseAct {
	
	@Autowired
	private UserService userService;
	
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("login.html")
	public String login(){
		return jspUrl("login");
	}
	
	@RequestMapping("logins.html")
	public String logins(String username,String password,HttpServletRequest request){
		User user = userService.getByUsername(username);
		if(user != null){
			String enPassword = MD5FileUtil.getMD5String(password).toUpperCase();
			if(PasswordUtil.checkPassword(enPassword, user.getSalt(), user.getPassword())
					&& ConstantsKey.USER_GRADE_MANAGER.equals(user.getGrade())
					){
				RequestUtil.setCurrent(request, user);
				return redirectIndex();
			}
		}
		return jspUrl("login");
	}
	
	@RequestMapping("index.html")
	public String index(){
		return jspUrl("index");
	}
	
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/" + htmlUrl;
	}

}
