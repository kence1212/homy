package com.quantum.engine.homy.action.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.clientdata.ClientDataRuntimeException;
import com.quantum.engine.homy.model.BizShoppingCart;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.ByNowDto;
import com.quantum.engine.homy.model.dto.ConfirmOrderDto;
import com.quantum.engine.homy.model.dto.ListOrderDto;
import com.quantum.engine.homy.model.dto.OrderDto;
import com.quantum.engine.homy.model.dto.SubmitOrderDto;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderItemShowExt;
import com.quantum.engine.homy.model.ext.BizOrderShowExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartExt;
import com.quantum.engine.homy.model.ext.UserAddressExt;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.model.result.ShoppingCartBrandResult;
import com.quantum.engine.homy.service.BizGoodsSkuService;
import com.quantum.engine.homy.service.BizOrderService;
import com.quantum.engine.homy.service.BizShoppingCartService;
import com.quantum.engine.homy.service.UserAddressService;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.OrderUtil;

@Controller
@RequestMapping("/app_order")
public class AppOrderAct extends BaseAct{
	
	@Autowired
	private BizOrderService bizOrderService;
	@Autowired
	private BizShoppingCartService bizShoppingCartService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private UserService userService;
	@Autowired
	private BizGoodsSkuService bizGoodsSkuService;
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	
	
	//立即购买接口
	@RequestMapping("/byNow.do")
	public void byNow(ByNowDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		//判断库存是否足够
		int count = bizGoodsSkuService.getStockByCityIdAndAttrs(dto.getCityId(),dto.getGoodsId(),dto.getColorId(),dto.getSizeId(),dto.getTextureId());
		if(count < dto.getNum()){
			result.wrong("40000","库存不足，当前库存只有"+count+"件");
			writeJson(result.getValues(), response);
			return ;
		}
		
		//库存足够
		//2、返回商品信息和总计 
		BizGoodsSkuStockExt bizGoodsSkuStockExt = bizGoodsSkuService.getGoodsByGoodsIdAndCity(dto.getGoodsId(), dto.getCityId(),dto.getColorId(),dto.getSizeId(),dto.getTextureId());
		//3、生成一条购物车项如果购物车中有同条sku那么直接把数量修改成立即购买的数量
		BizShoppingCartExt bizShoppingCartExt = new BizShoppingCartExt();
		bizShoppingCartExt.setColorId(dto.getColorId());
		bizShoppingCartExt.setSizeId(dto.getSizeId());
		bizShoppingCartExt.setTextureId(dto.getTextureId());
		bizShoppingCartExt.setGoodsId(dto.getGoodsId());
		bizShoppingCartExt.setUserId(dto.getUserId());
		bizShoppingCartExt.setNum(dto.getNum());
		int cartId = bizShoppingCartService.addByNowShopCart(bizShoppingCartExt);
		
		//返回数据
		if(cartId == 0){
			BaseResponse br = BaseResponse.getWrong("无此商品"); 
			writeJson(br, response);
			return;
		}
		ArrayList<Integer> cartIdsList = new ArrayList<>();
		cartIdsList.add(cartId);
		
		List<ShoppingCartBrandResult> listShoppingCart = bizShoppingCartService.listShoppingCart(dto.getUserId(), cartIdsList, dto.getCityId());
		UserAddressExt defaultAddr = userAddressService.getDefaultAddrExt(dto.getUserId());
		Double totalPrice = 0D;
		for (ShoppingCartBrandResult shoppingCartBrandResult : listShoppingCart) {
			totalPrice += shoppingCartBrandResult.getBrandTotalPrice();
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("orders", listShoppingCart.get(0));
		map.put("defaultAddr", defaultAddr);
		map.put("totalPrice", totalPrice);
		
		BaseResponse br = BaseResponse.getSuccess("确认成功");
		br.setObj(map);
		writeJson(br, response);
		
	}
	
	//确认收货
	@RequestMapping("/confirmReceive.do")
	public void confirmReceive(OrderDto dto, HttpServletResponse response){
		       
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		int i = bizOrderService.confirmReceive(dto.getId(),dto.getUserId(),OrderUtil.ORDER_STATE_RECEIVE);
		BaseResponse br = null;
		if(i == 1){
		    br = BaseResponse.getSuccess("确认成功");
		} else{
			br = BaseResponse.getWrong("确认失败");
		}
		writeJson(br, response);
		
	}
	
	//删除订单
	@RequestMapping("/deleteOrder.do")
	public void deleteOrder(OrderDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		int i = bizOrderService.deleteOrder(dto.getId(),dto.getUserId());
		BaseResponse br = null;
		if(i == 1){
			 br = BaseResponse.getSuccess("删除成功");
		} else{
			br = BaseResponse.getWrong("删除失败");
		}
		writeJson(br, response);
	}
	
	//获取订单明细信息
	@RequestMapping("orderItems.do")
	public void orderItems(OrderDto dto, HttpServletResponse response){
	
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		List<Integer> orderIds = new ArrayList<>();
		orderIds.add(dto.getId());
		List<BizOrderExt> listOrder = bizOrderService.listOrder(orderIds, dto.getUserId(),null );
		if(listOrder != null && listOrder.size() >= 1){
			BizOrderShowExt showSingleOrder = BizOrderShowExt.toShowSingleOrder(listOrder.get(0), baseUrl);
			List<BizOrderItemShowExt> items = showSingleOrder.getItems();
			for (BizOrderItemShowExt bizOrderItemShowExt : items) {
				bizOrderItemShowExt.setNum(null);
				bizOrderItemShowExt.setPrice(null);
			}
			Map<String,Object> map = new HashMap<>();
			map.put("items", items);
			map.put("totalPrice", showSingleOrder.getTotalPrice());
			br.setObj(map);
		}
		writeJson(br, response);
		
	}
	
	
	//获取订单详情
	@RequestMapping("/orderDetail.do")
	public void orderDetail(OrderDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
	 		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		ArrayList<Integer> orderIds = new ArrayList<>();
		orderIds.add(dto.getId());
		List<BizOrderExt> listOrder = bizOrderService.listOrder(orderIds, dto.getUserId(),null );
		if(listOrder != null && listOrder.size() >= 1){
			UserAddressExt addr = userAddressService.getAddrExt(listOrder.get(0).getAddressId());
			BizOrderShowExt showSingleOrder = BizOrderShowExt.toShowSingleOrder(listOrder.get(0), baseUrl);
			Map<String,Object>  map = new HashMap<>();
			map.put("address", addr);
			List<Map<String,Object>> attrs = showSingleOrder.getAttrs();
			showSingleOrder.setAttrs(null);
			map.put("attrs", attrs);
			map.put("orderDetail", showSingleOrder);
			map.put("endDate", listOrder.get(0).getCreateTime().getTime()+86400000); //结束时间
			br.setObj(map);
		}
		writeJson(br, response);
	}
	
	//取消订单
	@RequestMapping("/cancelOrder.do")
	public void cancelOrder(OrderDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		bizOrderService.cancelOrder(dto.getId(),dto.getUserId());
		BaseResponse br = BaseResponse.getSuccess("取消成功");
		writeJson(br, response);
	}
	
	//确认订单
	@RequestMapping("/confirmOrder.do")
	public void confirmOrder(ConfirmOrderDto dto, HttpServletResponse response){
		
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		String cartIds = dto.getCartIds();
		String[] split = cartIds.split(",");
		List<Integer> cartIdsList = new ArrayList<>();
		for (String id : split) {
			cartIdsList.add(Integer.parseInt(id));
		}
		List<ShoppingCartBrandResult> listShoppingCart = bizShoppingCartService.listShoppingCart(dto.getUserId(), cartIdsList, dto.getCityId());
		UserAddressExt defaultAddr = userAddressService.getDefaultAddrExt(dto.getUserId());
		Double totalPrice = 0D;
		for (ShoppingCartBrandResult shoppingCartBrandResult : listShoppingCart) {
			totalPrice += shoppingCartBrandResult.getBrandTotalPrice();
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("orders", listShoppingCart);
		map.put("defaultAddr", defaultAddr);
		map.put("totalPrice", totalPrice);
		
		BaseResponse br = BaseResponse.getSuccess("确认成功");
		br.setObj(map);
		writeJson(br, response);
		
	}
	
	//提交订单
	@RequestMapping("/submitOrder.do")
	public void submitOrder(SubmitOrderDto dto, HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		String cartIds = dto.getCartIds();
		String[] split = cartIds.split(",");
		List<Integer> cartIdsList = new ArrayList<>();
		for (String id : split) {
			cartIdsList.add(Integer.parseInt(id));
		}
		BaseResponse br = null;
		try {
			br = bizOrderService.addOrder(dto.getAddressId(), dto.getUserId(), dto.getRemark(), cartIdsList, dto.getCityId());
		} catch (ClientDataRuntimeException e) {
			br = BaseResponse.getWrong(e.getMessage());
		}
		writeJson(br, response);
		
	}
	
	@RequestMapping("/pay.do")
	public void pay(){
		//支付 TODO
	}
	
	@RequestMapping("/list.do")
	public void list(ListOrderDto dto,HttpServletResponse response){
		Result result = dto.validate();
		if(result.getKey("errorMsg") != null){
			writeJson(result.getValues(), response);
			return ;
		}
		
		//token验证
		UserToken userToken = userService.getByTokenAndUserId(dto.getToken(),dto.getUserId());
		if(userToken == null ){
			result.wrong("40000","token失效");
			writeJson(result.getValues(), response);
			return ;
		}
		
		List<Integer> orderIdsList = new ArrayList<>();
		if(dto.getOrderIds() != null){
			String orderIds = dto.getOrderIds();
			String[] split = orderIds.split(",");
			for (String id : split) {
				orderIdsList.add(Integer.parseInt(id));
			}
		}
		
		List<BizOrderExt> orders = bizOrderService.listOrder(orderIdsList ,dto.getUserId(),dto.getState());
		List<BizOrderShowExt> orderShow = new ArrayList<>();
		
		for (BizOrderExt order : orders) {
			BizOrderShowExt bizOrderShowExt = BizOrderShowExt.toShow(order,baseUrl);
			orderShow.add(bizOrderShowExt);
		}
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		br.setObj(orderShow);
		writeJson(br, response);
		
	}

}
