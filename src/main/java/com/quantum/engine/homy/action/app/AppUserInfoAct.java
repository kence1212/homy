package com.quantum.engine.homy.action.app;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.http.HttpResponse;
import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.BizUserCompany;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.CompanyUpdateDto;
import com.quantum.engine.homy.model.dto.GetDto;
import com.quantum.engine.homy.model.dto.PersonUpdateDto;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.StringHelper;

@Controller
@RequestMapping("/uinfo")
public class AppUserInfoAct extends BaseAct {
	
	@Autowired
	private UserService userService;
	
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("/personUpdate.do")
	public void personUpdate(@RequestParam(value = "img", required = false) MultipartFile file,PersonUpdateDto dto, HttpServletRequest request,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		//校验token
		UserToken userToken = userService.getTokenByUserId(dto.getUserId());
		if(userToken == null 
				|| !userToken.getToken().equals(dto.getToken())
				|| !dto.checkLimitTime(userToken)){
			result.wrong("40000","token错误");
			writeJson(result.getValues(), response);
			return;
		}
		
		User user = userService.getById(dto.getUserId());
		if(user == null){
			result.wrong("40000","用户未找到");
			writeJson(result.getValues(), response);
			return;
		}
		
		if(file != null){
			String path = request.getSession().getServletContext().getRealPath("upload");  
			String fileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())); 
			File targetFile = new File(path, fileName);  
			if(!targetFile.exists()){  
				targetFile.mkdirs();  
			}  
			//保存  
			try {  
				file.transferTo(targetFile);  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			String fileUrl = "/upload/" + fileName;
			user.setPortrait(fileUrl);
		}
        user.setSex(dto.getSex());
        user.setNickname(dto.getNickname());
        userService.update(user);
        result.success("更新成功");
        writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/companyUpdate.do")
	public void companyUpdate(@RequestParam(value = "img", required = false) MultipartFile file,CompanyUpdateDto dto, HttpServletRequest request,HttpServletResponse response){
		//TODO
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		//校验token
		UserToken userToken = userService.getTokenByUserId(dto.getUserId());
		if(userToken == null 
				|| !userToken.getToken().equals(dto.getToken())
				|| !dto.checkLimitTime(userToken)){
			result.wrong("40000","token错误");
			writeJson(result.getValues(), response);
			return;
		}
		
		BizUserCompany company = userService.getCompanyByUserId(dto.getUserId());
		if(company == null){
			result.wrong("40000","用户未找到");
			writeJson(result.getValues(), response);
			return;
		}
		if(file != null){
			String path = request.getSession().getServletContext().getRealPath("upload");  
			String fileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())); 
			File targetFile = new File(path, fileName);  
			if(!targetFile.exists()){  
				targetFile.mkdirs();  
			}  
			//保存  
			try {  
				file.transferTo(targetFile);  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			String fileUrl = "/upload/" + fileName;
			company.setPortrait(fileUrl);
		}
		company.setOperatingYears(dto.getOperatingYears());
		company.setOperatingProjects(dto.getOperatingProjects());
		company.setEmployeeNum(dto.getEmployeeNum());
		company.setCompanyPhone(dto.getCompanyPhone());
		userService.updateCompany(company);
		result.success("更新成功");
        writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/getUserInfo.do")
	public void getUserInfo(GetDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		User user = userService.getByToken(dto.getToken());
		if(user != null ){
			result.success("获取成功");
			if(StringHelper.isNotNull(user.getPortrait())){
				if(user.getPortrait().startsWith("http")){
					result.putValue("portrait", user.getPortrait());
				}else{
					result.putValue("portrait", baseUrl + user.getPortrait());
				}
			}
			result.putValue("phone", user.getPhone());
			result.putValue("userId", user.getUserId());
			result.putValue("grade", user.getGrade());
			if(user.getGrade() == ConstantsKey.USER_GRADE_COMPANY){
				BizUserCompany company = (BizUserCompany)user;
				result.putValue("company", company.getCompany());
				result.putValue("companyAddr", company.getCompanyAddr());
				result.putValue("operatingProjects", company.getOperatingProjects());
				result.putValue("employeeNum", company.getEmployeeNum());
				result.putValue("companyPhone", company.getCompanyPhone());
				result.putValue("operatingYears", company.getOperatingYears());
			}else{
				result.putValue("sex", user.getSex());
				result.putValue("nickname", user.getNickname());
			}
		}else{
			result.wrong("token错误");
		}
		writeJson(result.getValues(), response);
	}

}
