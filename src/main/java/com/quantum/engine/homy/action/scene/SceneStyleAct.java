package com.quantum.engine.homy.action.scene;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.dto.GetDto;
import com.quantum.engine.homy.model.ext.BizSceneStyleExt;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.BizSceneStyleService;

@Controller
@RequestMapping("/scenestyle")
public class SceneStyleAct extends BaseAct {
	
	@Autowired
	private BizSceneStyleService bizSceneStyleService;
	
	@RequestMapping("list.do")
	public void list(GetDto dto,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		//列表 获取上架状态的 场景风格 根据sort升序排列 TODO
		List<BizSceneStyleExt> list = bizSceneStyleService.getStyleList();
		BaseResponse success = BaseResponse.getSuccess("获取成功");
		success.setObj(list);
		writeJson(success, response);
	}

}
