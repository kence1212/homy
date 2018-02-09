package com.quantum.engine.homy.action.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BasAppSoft;
import com.quantum.engine.homy.model.dto.CheckVersionDto;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BasAppSoftService;

@Controller
@RequestMapping("/cc")
public class ClientCommonAct extends BaseAct{
	
	@Autowired
	private BasAppSoftService basAppSoftService;
	
	@RequestMapping("/checkVersion.do")
	public void checkVersion(CheckVersionDto dto , HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		BasAppSoft basAppSoft = new BasAppSoft();
		basAppSoft.setAppCode(dto.getAppCode());
		basAppSoft.setAppType(dto.getAppType());
		BasAppSoft maxVersion = basAppSoftService.selectMaxVersion(basAppSoft);
		if(maxVersion!=null && maxVersion.getAppVersion().intValue() > dto.getAppVersion().intValue()){
			result.success("需要更新");
			result.putValue("state", 1);
			result.putValue("downloadUrl", maxVersion.getDownloadUrl());
			result.putValue("appCode", maxVersion.getAppCode());
			result.putValue("appVersionStr", maxVersion.getAppVersionStr());
			result.putValue("appVersion", maxVersion.getAppVersion());
			result.putValue("appInfo", maxVersion.getAppInfo());
			result.putValue("compelUpdate", maxVersion.getCompelUpdate());
		}else{
			result.success("不需要更新");
			result.putValue("state", 0);
		}
		writeJson(result.getValues(), response);
	} 

}
