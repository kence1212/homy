package com.quantum.engine.homy.model.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.util.DateUtil;

public class BizOrderShowExt extends BizOrder{

	private  List<BizOrderItemShowExt> items ;
	
	private String brandName;
	
	private Integer brandId;
	
	private Integer totalNum;
	
	private Long date;
	
	private UserAddressExt userAddressExt;
	
	private List<Map<String,Object>> orders ;
	
	public List<Map<String, Object>> getOrders() {
		return orders;
	}

	public void setOrders(List<Map<String, Object>> orders) {
		this.orders = orders;
	}

	public UserAddressExt getUserAddressExt() {
		return userAddressExt;
	}

	public void setUserAddressExt(UserAddressExt userAddressExt) {
		this.userAddressExt = userAddressExt;
	}

	private List<Map<String,Object>> attrs ;
	
	
	
	public List<Map<String, Object>> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Map<String, Object>> attrs) {
		this.attrs = attrs;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}


	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<BizOrderItemShowExt> getItems() {
		return items;
	}

	public void setItems(List<BizOrderItemShowExt> items) {
		this.items = items;
	}

	
	public static BizOrderShowExt toShowSingleOrder(BizOrderExt order, String baseUrl) {
		BizOrderShowExt bizOrderShowExt = new BizOrderShowExt();
		
		//订单信息
		bizOrderShowExt.setId(order.getId());
		bizOrderShowExt.setTotalPrice(order.getTotalPrice());
		//bizOrderShowExt.setDate(order.getCreateTime().getTime()/1000);
		//bizOrderShowExt.setOrderNo(order.getOrderNo());
		bizOrderShowExt.setOutTradeNo(order.getOutTradeNo());
		//bizOrderShowExt.setRemark(order.getRemark());
		bizOrderShowExt.setState(order.getState());
		List<Map<String,Object>> attrs = new ArrayList<>();
		Map<String,Object> m1 = new HashMap<>();
		m1.put("attrKey", "订单编号：");
		m1.put("attrVal", order.getOrderNo());
		Map<String,Object> m2 = new HashMap<>();
		m2.put("attrKey", "下单时间：");
		m2.put("attrVal", DateUtil.dateToString(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		Map<String,Object> m3 = new HashMap<>();
		Integer payType = order.getPayType();
		String payTypeStr = "未支付";
		if(payType != null){
			if(payType == 0){
				payTypeStr = "支付宝支付";
			} else if(payType == 1){
				payTypeStr = "微信支付";
			}
		}
		m3.put("attrKey", "支付方式：");
		m3.put("attrVal", payTypeStr);
		Map<String,Object> m4 = new HashMap<>();
		m4.put("attrKey", "备注：");
		m4.put("attrVal", order.getRemark());
		attrs.add(m1);
		attrs.add(m2);
		attrs.add(m3);
		attrs.add(m4);
		bizOrderShowExt.setAttrs(attrs);
		
		//订单明细信息
		List<BizOrderItemShowExt> list = new ArrayList<>();
		List<BizOrderItemExt> items2 = order.getItems();
		Integer totalNum = 0;
		for (BizOrderItemExt bizOrderItemExt : items2) {
			
			BizOrderItemShowExt bizOrderItemShowExt = new BizOrderItemShowExt();
			bizOrderItemShowExt.setColorName(bizOrderItemExt.getBizGoodsSku().getColorName());
			bizOrderItemShowExt.setSizeName(bizOrderItemExt.getBizGoodsSku().getSizeName());
			bizOrderItemShowExt.setTextureName(bizOrderItemExt.getBizGoodsSku().getTextureName());
			bizOrderItemShowExt.setGoodsName(bizOrderItemExt.getBizGoodsSku().getGoodsName());
			bizOrderItemShowExt.setPrice(bizOrderItemExt.getPrice());
			bizOrderItemShowExt.setSkuIcon(baseUrl + bizOrderItemExt.getBizGoodsSku().getSkuIcon());
			bizOrderItemShowExt.setNum(bizOrderItemExt.getNum());
			list.add(bizOrderItemShowExt);
			totalNum += bizOrderItemExt.getNum();
			bizOrderShowExt.setBrandName(bizOrderItemExt.getBizGoodsSku().getBrandName());
			bizOrderShowExt.setBrandId(bizOrderItemExt.getBizGoodsSku().getBrandId());
			
		}
		bizOrderShowExt.setItems(list);
		bizOrderShowExt.setTotalNum(totalNum);
		
		return bizOrderShowExt;
	}
	
	public static BizOrderShowExt toShow(BizOrderExt order, String baseUrl) {
		BizOrderShowExt bizOrderShowExt = new BizOrderShowExt();
		
		//订单信息
		bizOrderShowExt.setId(order.getId());
		bizOrderShowExt.setTotalPrice(order.getTotalPrice());
		bizOrderShowExt.setDate(order.getCreateTime().getTime()/1000);
		//bizOrderShowExt.setOrderNo(order.getOrderNo());
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> orders = new ArrayList<>();
		map.put("ono", order.getOrderNo());
		orders.add(map);
		bizOrderShowExt.setOrders(orders);
		bizOrderShowExt.setOutTradeNo(order.getOutTradeNo());
		bizOrderShowExt.setRemark(order.getRemark());
		bizOrderShowExt.setState(order.getState());
		
		//订单明细信息
		List<BizOrderItemShowExt> list = new ArrayList<>();
		List<BizOrderItemExt> items2 = order.getItems();
		Integer totalNum = 0;
		for (BizOrderItemExt bizOrderItemExt : items2) {
			
			BizOrderItemShowExt bizOrderItemShowExt = new BizOrderItemShowExt();
			bizOrderItemShowExt.setColorName(bizOrderItemExt.getBizGoodsSku().getColorName());
			bizOrderItemShowExt.setSizeName(bizOrderItemExt.getBizGoodsSku().getSizeName());
			bizOrderItemShowExt.setTextureName(bizOrderItemExt.getBizGoodsSku().getTextureName());
			bizOrderItemShowExt.setGoodsName(bizOrderItemExt.getBizGoodsSku().getGoodsName());
			bizOrderItemShowExt.setPrice(bizOrderItemExt.getPrice());
			bizOrderItemShowExt.setSkuIcon(baseUrl + bizOrderItemExt.getBizGoodsSku().getSkuIcon());
			bizOrderItemShowExt.setNum(bizOrderItemExt.getNum());
			list.add(bizOrderItemShowExt);
			totalNum += bizOrderItemExt.getNum();
			bizOrderShowExt.setBrandName(bizOrderItemExt.getBizGoodsSku().getBrandName());
			bizOrderShowExt.setBrandId(bizOrderItemExt.getBizGoodsSku().getBrandId());
			
		}
		bizOrderShowExt.setItems(list);
		bizOrderShowExt.setTotalNum(totalNum);
		
		return bizOrderShowExt;
	}
	
}
