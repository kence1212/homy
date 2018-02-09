package com.quantum.engine.homy.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.StringHelper;

@Controller
@RequestMapping("aduser")
public class AdminUserAct extends BaseAct {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index.html")
	public String index(Model model,String searchKey){
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringHelper.isNotNull(searchKey)){
			params.put("searchKey", searchKey);
			model.addAttribute("searchKey", searchKey);
		}
		
		List<User> userList = userService.listRegisterUser(params);
		model.addAttribute("userList", userList);
		return jspUrl("list");
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = userService.delete(id);
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
		return "jsp/admin/user/" + htmlUrl;
	}

}
