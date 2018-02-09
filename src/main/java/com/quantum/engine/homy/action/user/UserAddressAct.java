package com.quantum.engine.homy.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.AddrDto;
import com.quantum.engine.homy.model.dto.UserAddrDto;
import com.quantum.engine.homy.model.dto.UserAddrEditDto;
import com.quantum.engine.homy.model.dto.UserAddrListDto;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.UserAddressService;
import com.quantum.engine.homy.service.UserService;

@Controller
@RequestMapping("/ad")
public class UserAddressAct extends BaseAct{

	
	@Autowired
	UserService userService;
	
	@Autowired
	UserAddressService userAddressService;
	
	
	@RequestMapping("/getAddr.do")
	public void getAddr(AddrDto dto,HttpServletResponse response){
		
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
		
		
		UserAddress userAddress = userAddressService.getAddr(dto.getAddrId());
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(userAddress);
		writeJson(br, response);
		
	}
	
	@RequestMapping("/editUserAddr.do")
	public void editUserAddr(UserAddrEditDto dto,HttpServletResponse response,HttpServletRequest request){
		
		//验证
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
		
		UserAddress userAddress = new UserAddress();
		userAddress.setConsignee(dto.getConsignee());
		userAddress.setPhone(dto.getPhone());
		userAddress.setProvinceId(dto.getProvinceId());
		userAddress.setCityId(dto.getCityId());
		userAddress.setAreaId(dto.getAreaId());
		userAddress.setAddressDetail(dto.getAddressDetail());
		userAddress.setIsDefault(dto.getIsDefault());
		userAddress.setUserId(dto.getUserId());
		userAddress.setId(dto.getAddrId());
		
		userAddressService.editUserAddr(userAddress);
		BaseResponse br = BaseResponse.getSuccess("编辑成功");
		writeJson(br, response);
		
	}
	
	@RequestMapping("/deleteAddr.do")
	public void deleteAddr(AddrDto dto,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		
		userAddressService.deleteAddr(dto.getAddrId(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("删除成功");
		writeJson(br, response);
		
	}
	
	
	@RequestMapping("/getUserAddrList.do")
	public void getUserAddrList(UserAddrListDto dto,HttpServletResponse response){
		//验证
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		Page page =userAddressService.getUserAddrList(dto.getPage(),dto.getPageSize(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(page);
		writeJson(br, response);
		
	}
	
	@RequestMapping("/saveUserAddr.do")
	public void saveUserAddr(UserAddrDto dto,HttpServletResponse response,HttpServletRequest request){
		
		
		
		//验证
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
		
		
		UserAddress userAddress = new UserAddress();
		userAddress.setConsignee(dto.getConsignee());
		userAddress.setPhone(dto.getPhone());
		userAddress.setProvinceId(dto.getProvinceId());
		userAddress.setCityId(dto.getCityId());
		userAddress.setAreaId(dto.getAreaId());
		userAddress.setAddressDetail(dto.getAddressDetail());
		userAddress.setIsDefault(dto.getIsDefault());
		userAddress.setUserId(dto.getUserId());
		
		BaseResponse br = userAddressService.saveUserAddr(userAddress);
		
		writeJson(br, response);
		
	}
	
	@RequestMapping("/setDefaultAddr.do")
	public void setDefaultAddr(AddrDto dto,HttpServletResponse response){
		
		//验证
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
		
		userAddressService.setDefaultAddr(dto.getAddrId(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("设置成功");
		writeJson(br, response);
		
	}
}
