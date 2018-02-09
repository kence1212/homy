package com.quantum.engine.homy.action.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.constants.ConstantsKey;
import com.quantum.engine.homy.model.User;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.CheckTokenDto;
import com.quantum.engine.homy.model.dto.FindPasswordDto;
import com.quantum.engine.homy.model.dto.LoginDto;
import com.quantum.engine.homy.model.dto.RegisterDto;
import com.quantum.engine.homy.model.dto.SendCodeDto;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.sms.Sms;
import com.quantum.engine.homy.util.IPs;
import com.quantum.engine.homy.util.PasswordUtil;
import com.quantum.engine.homy.util.StringHelper;

/**
 * 
 * @ClassName: UserLoginAct 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月3日 下午7:02:47 
 *
 */
@Controller
@RequestMapping("/mu")
public class UserLoginAct extends BaseAct {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/sendCode.do")
	public void sendCode(SendCodeDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		String code = StringHelper.getRandomNum(6);
		try {
			SendSmsResponse smsResponse = null;
			if(dto.getType() != null && dto.getType() == 2){
				smsResponse = Sms.sendFindSms(code,dto.getPhone());
			}else{
				smsResponse = Sms.sendSms(code,dto.getPhone());
			}
			if(smsResponse.getCode() != null && smsResponse.getCode().equals("OK")){
				userService.savePhoneCode(dto.getPhone(), code);
				result.putValue("resultCode", "10000" );
				result.putValue("msg", "发送成功");
			}else{
				result.putValue("resultCode", "40000" );
				result.putValue("errorMsg",smsResponse.getMessage());
			}
		} catch (ClientException e) {
			result.putValue("resultCode", "40000" );
			result.putValue("errorMsg","发送错误");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/register.do")
	public void register(RegisterDto dto,HttpServletResponse response,HttpServletRequest request){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		User user = userService.getByPhone(dto.getPhone());
		if(user == null){
			result.wrong("40000", "验证码错误");
		}
		boolean checkCode = dto.checkPhoneCode(user.getPhoneCode(), dto.getCode());
		if(checkCode){
			if(user.getUserState() == 0){
				String salt = StringHelper.getRandomStr(6);
				String enPass = PasswordUtil.encrypt(dto.getPassword(), salt);
				user.setSalt(salt);
				user.setPassword(enPass);
				user.setGrade(dto.getGrade());
				user.setUserState(ConstantsKey.USER_STATE_REGISTER);
				user.setRegisterIp(IPs.getIpAddr(request));
				user.setRegisterTime(new Date());
				user.setPhoneCode("");
				if(dto.getGrade() != null 
						&& ConstantsKey.USER_GRADE_COMPANY == dto.getGrade()){
					user.setCompany(dto.getCompany());
					user.setCompanyAddr(dto.getCompanyAddr());
					user.setJob(dto.getJob());
				}
				userService.update(user);
				result.success("注册成功");
			}else{
				result.wrong("40000", "用户已注册");
			}
		}else{
			result.wrong("40000", "验证码错误");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/login.do")
	public void login(LoginDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		User user = userService.getByPhone(dto.getPhone());
		if(user != null && user.getUserState() != null 
				&& user.getUserState() != 0
				&& PasswordUtil.checkPassword(dto.getPassword(), user.getSalt(), user.getPassword())){
			result.success("登录成功");
			String token = userService.generateToken(user.getUserId());
			result.putValue("token", token);
			result.putValue("userId", user.getUserId());
		}else{
			result.wrong("40000","用户名或者密码错误");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/findPassword.do")
	public void findPassword(FindPasswordDto dto , HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		User user = userService.getByPhone(dto.getPhone());
		if(user == null){
			result.wrong("40000", "验证码错误");
		}
		boolean checkCode = dto.checkPhoneCode(user.getPhoneCode(), dto.getCode());
		if(checkCode){
			if(user.getUserState() != ConstantsKey.USER_STATE_INIT){
				String salt = StringHelper.getRandomStr(6);
				String enPass = PasswordUtil.encrypt(dto.getPassword(), salt);
				user.setSalt(salt);
				user.setPassword(enPass);
				userService.update(user);
				result.success("修改成功");
			}else{
				result.wrong("40000", "用户未注册");
			}
		}else{
			result.wrong("40000", "验证码错误");
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/checkToken.do")
	public void checkToken(CheckTokenDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return;
		}
		UserToken userToken = userService.getTokenByUserId(dto.getUserId());
		if(userToken == null 
				|| !userToken.getToken().equals(dto.getToken())
				|| !dto.checkLimitTime(userToken)){
			result.wrong("40000","token错误");
			writeJson(result.getValues(), response);
			return;
		}
		result.success("token正确");
		writeJson(result.getValues(), response);
	}
	
}
