package com.quantum.engine.homy.action.app;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.BizModelShoppingCartDto;
import com.quantum.engine.homy.model.dto.BizShoppingCartDto;
import com.quantum.engine.homy.model.dto.ClearSCartDto;
import com.quantum.engine.homy.model.dto.GetSCartDto;
import com.quantum.engine.homy.model.dto.ListDeleteDto;
import com.quantum.engine.homy.model.dto.UpdateShoppingCartDto;
import com.quantum.engine.homy.model.ext.BizGoodsSkuExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuGroupByBrandExt;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.model.result.ShoppingCartBrandResult;
import com.quantum.engine.homy.service.BizShoppingCartService;

@Controller
@RequestMapping("/cart")
public class AppShoppingCartAct extends BaseAct {
	
	@Autowired
	private BizShoppingCartService bizShoppingCartService;
	
	
	@RequestMapping("/addModelShoppingCart.do")
	public void addModelShoppingCart(BizModelShoppingCartDto dto,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizShoppingCartService.addModelShoppingCart(dto.getModelIds(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("保存成功");
		writeJson(br, response);
		
	}
	
	@RequestMapping("/addShoppingCart.do")
	public void addShoppingCart(BizShoppingCartDto dto,HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		
		BizShoppingCartExt bizShoppingCartExt = new BizShoppingCartExt();
		
		bizShoppingCartExt.setColorId(dto.getColorId());
		bizShoppingCartExt.setCreateTime(new Date());
		bizShoppingCartExt.setGoodsId(dto.getGoodsId());
		bizShoppingCartExt.setIsValid(1);
		bizShoppingCartExt.setNum(dto.getNum());
		bizShoppingCartExt.setSizeId(dto.getSizeId());
		bizShoppingCartExt.setTextureId(dto.getTextureId());
		bizShoppingCartExt.setUserId(dto.getUserId());
		
		bizShoppingCartService.addShoppingCart(bizShoppingCartExt);
		BaseResponse br = BaseResponse.getSuccess("保存成功");
		writeJson(br, response);
		
		
	}
	
	@RequestMapping("/deleteShoppingCart.do")
	public void deleteShoppingCart(ListDeleteDto dto, HttpServletResponse response){
		
		//删除购物车某个商品 
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizShoppingCartService.delete(dto.getId(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("删除成功");
		writeJson(br, response);
		
	}
	
	@RequestMapping("/updateShoppingCart.do")
	public void updateShoppingCart(UpdateShoppingCartDto dto, HttpServletResponse response ){
		
		//修改购物车某个商品 TODO
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizShoppingCartService.updateShoppingCart(dto.getId(),dto.getUserId(),dto.getNum());
		BaseResponse br = BaseResponse.getSuccess("编辑成功");
		writeJson(br, response);
		
	}
	
	@RequestMapping("/clearShoppingCart.do")
	public void clearShoppingCart(ClearSCartDto dto, HttpServletResponse response){
		
		//清空购物车 
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizShoppingCartService.clearShoppingCart(dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("清空成功");
		writeJson(br, response);
		
	}
	
	@RequestMapping("/listShoppingCart.do")
	public void listShoppingCart(GetSCartDto dto, HttpServletResponse response){
		
		//获取购物车列表 
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<ShoppingCartBrandResult> listShoppingCart = bizShoppingCartService.listShoppingCart(dto.getUserId(),null,dto.getCityId());
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(listShoppingCart);
		writeJson(br, response);
		
	}
	
}
