package com.quantum.engine.homy.action.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.Gson;
import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.model.UserToken;
import com.quantum.engine.homy.model.dto.ApplyRefundDto;
import com.quantum.engine.homy.model.dto.OrderRefundInfoDto;
import com.quantum.engine.homy.model.dto.PayDto;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderItemShowExt;
import com.quantum.engine.homy.model.ext.BizOrderShowExt;
import com.quantum.engine.homy.model.ext.UserAddressExt;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.OrderRefundInfoResult;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.pay.ali.AliConfig;
import com.quantum.engine.homy.pay.wx.WxPayData;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.BizOrderService;
import com.quantum.engine.homy.service.PayService;
import com.quantum.engine.homy.service.UserService;
import com.quantum.engine.homy.util.DateUtil;

@Controller
@RequestMapping("/pay")
public class PayAct extends BaseAct {
	
	private static final Logger logger = Logger.getLogger(PayAct.class);
	
	@Autowired
	private PayService payService;
	@Autowired
	private UserService userService;
	@Autowired
	private BizOrderService bizOrderService;
	@Autowired
	private BizGoodsService bizGoodsService;
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@RequestMapping("/alinotify.do")
	@ResponseBody
	public String alinotify(HttpServletRequest request) throws AlipayApiException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean flag = AlipaySignature.rsaCheckV1(params, AliConfig.ALIPAY_PUBLIC_KEY, AliConfig.CHARSET,"RSA2");
		if(flag){
			payService.exePayedOrderByOutTradeNo((String)params.get("out_trade_no"));
		}
		return null;
	}
	
	@RequestMapping("/wxnotify.do")
	@ResponseBody
	public String wxnotify(HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException{
		request.setCharacterEncoding("utf-8");
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		logger.info(wholeStr);
		WxPayData data = null;
		if(!"".equals(wholeStr)){
			data = new WxPayData();
			data.fromXml(wholeStr);
			if("SUCCESS".equals(data.getValue("return_code")) 
					&& "SUCCESS".equals(data.getValue("result_code"))){
				payService.exePayedOrderByOutTradeNo((String)data.getValue("out_trade_no"));
			}
		}
		data = new WxPayData();
		data.setValue("return_code", "SUCCESS");
		data.setValue("return_msg", "OK");
		return data.toXml();
	}
	
	@RequestMapping("/appPay.do")
	public void alipay(PayDto dto,HttpServletResponse response){
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
		Map<String, Object> values = payService.getAppPayData(dto.getOrderIds(), dto.getType(),dto.getUserId());
		result.success("获取成功");
		result.getValues().putAll(values);
		result.putValue("dateStr", DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.putValue("orderId", dto.getOrderIds());
		result.putValue("goodsNames",bizGoodsService.goodsNames(dto.getOrderIds()) );
		writeJson(result.getValues(), response);
	}
	
	//申请售后
	@RequestMapping("/applyService.do")
	public void applyService(@RequestParam(value = "imgs", required = false) MultipartFile[] imgs
			,ApplyRefundDto dto, HttpServletRequest request,HttpServletResponse response){
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
		
		List<String> imgUrls = new ArrayList<String>();
		if(imgs != null && imgs.length > 0){
			for(MultipartFile file : imgs){
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
				imgUrls.add(fileUrl);
			}
		}
		BizOrderRefund refund = new BizOrderRefund();
		refund.setCreateId(dto.getUserId());
		refund.setCreateTime(new Date());
		refund.setOrderId(dto.getOrderId());
		refund.setRefundReason(dto.getRefundReason());
		refund.setRemark(dto.getRemark());
		refund.setState(BizOrderRefund.STATE_INIT);
		String error = payService.applyRefund(imgUrls, refund);
		if(error == null){
			result.success("申请提交");
		}else{
			result.wrong(error);
		}
		writeJson(result.getValues(), response);
	}
	
	@RequestMapping("/serviceInfo.do")
	public void serviceInfo(OrderRefundInfoDto dto,HttpServletResponse response){
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
		int orderId = dto.getOrderId();
		BizOrderRefund refund = payService.getRefundInfoByOrderId(orderId);
		if(refund == null){
			result.wrong("该订单尚未申请售后");
			writeJson(result.getValues(), response);
			return ;
		}
		
		BaseResponse br = BaseResponse.getSuccess("获取成功");
		ArrayList<Integer> orderIds = new ArrayList<>();
		orderIds.add(orderId);
		List<BizOrderExt> listOrder = bizOrderService.listOrder(orderIds, dto.getUserId(),null );
		if(listOrder != null && listOrder.size() >= 1){
			BizOrderShowExt showSingleOrder = BizOrderShowExt.toShowSingleOrder(listOrder.get(0), baseUrl);
			Map<String,Object>  map = new HashMap<>();
			List<Map<String,Object>> attrs = showSingleOrder.getAttrs();
			showSingleOrder.setAttrs(null);
			map.put("attrs", attrs);
			map.put("orderDetail", showSingleOrder);
			map.put("endDate", listOrder.get(0).getCreateTime().getTime()+86400000); //结束时间
			map.put("refundInfo", new OrderRefundInfoResult(refund));
			br.setObj(map);
		}
		writeJson(br, response);
	}
	
	@RequestMapping("/cancelService.do")
	public void cancelService(OrderRefundInfoDto dto,HttpServletResponse response){
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
		int orderId = dto.getOrderId();
		String error = payService.cancelRefundService(orderId,dto.getUserId());
		if(error == null){
			result.success("取消成功");
		}else{
			result.wrong(error);
		}
		writeJson(result.getValues(), response);
	}

}
