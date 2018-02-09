package com.quantum.engine.homy.model.ext;

import java.util.List;

import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.util.OrderUtil;

public class BizOrderExt extends BizOrder{

	private  List<BizOrderItemExt> items ;
	
	private Long date ;
	
	private String stateStr ;
	
	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr() {
		if(this.getState() == OrderUtil.ORDER_STATE_CANCEL){
	   		 stateStr = "订单取消";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_INIT){
	   		 stateStr = "订单生成";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_PAYED){
	   		 stateStr = "订单已支付";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_SENDED){
	   		 stateStr = "订单已发货";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_RECEIVE){
	   		 stateStr = "订单已收货";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_REPLYED){
	   		 stateStr = "订单已评价";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_BACK){
	   		 stateStr = "订单退货";
	   	} else if(this.getState() == OrderUtil.ORDER_STATE_REBACK){
	   		 stateStr = "订单已退货";
	   	}
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public List<BizOrderItemExt> getItems() {
		return items;
	}

	public void setItems(List<BizOrderItemExt> items) {
		this.items = items;
	}

	
}
