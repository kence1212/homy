package com.quantum.engine.homy.util;

import java.util.Date;

public class OrderUtil {
	/**订单取消*/
	public static final int ORDER_STATE_CANCEL = 0;
	/**订单生成*/
	public static final int ORDER_STATE_INIT = 1;
	/**订单已支付*/
	public static final int ORDER_STATE_PAYED = 2;
	/**订单已发货*/
	public static final int ORDER_STATE_SENDED = 3;
	/**订单已收货*/
	public static final int ORDER_STATE_RECEIVE = 4;
	/**订单已评价*/
	public static final int ORDER_STATE_REPLYED = 5;
	/**订单退货*/
	public static final int ORDER_STATE_BACK = 6;
	/**订单已退货*/
	public static final int ORDER_STATE_REBACK = 7;
	/**订单已退款*/
	public static final int ORDER_STATE_REFUND = 8;
	/**订单已完结*/
	public static final int ORDER_STATE_FINISH = 9;
	
	/**
	 * 生成订单号
	 * @return
	 */
	public static String genOrderNo(){
		StringBuffer sb = new StringBuffer("ono");
		sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss" ));
		sb.append(StringHelper.getRandomNum(4));
		return sb.toString();
	}
	
	/**
	 * 生成交易号
	 * @return
	 */
	public static String genOrderTradeNo(){
		StringBuffer sb = new StringBuffer("tno");
		sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss" ));
		sb.append(StringHelper.getRandomNum(4));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(genOrderNo());
		System.out.println(genOrderTradeNo());
	}
	
	public static String getStateStr(Integer state){
		if(state == null){
			return "";
		}
		String stateStr = "";
		if(state == OrderUtil.ORDER_STATE_CANCEL){
	   		 stateStr = "订单取消";
	   	} else if(state == OrderUtil.ORDER_STATE_INIT){
	   		 stateStr = "订单生成";
	   	} else if(state == OrderUtil.ORDER_STATE_PAYED){
	   		 stateStr = "订单已支付";
	   	} else if(state == OrderUtil.ORDER_STATE_SENDED){
	   		 stateStr = "订单已发货";
	   	} else if(state == OrderUtil.ORDER_STATE_RECEIVE){
	   		 stateStr = "订单已收货";
	   	} else if(state == OrderUtil.ORDER_STATE_REPLYED){
	   		 stateStr = "订单已评价";
	   	} else if(state == OrderUtil.ORDER_STATE_BACK){
	   		 stateStr = "订单退货";
	   	} else if(state == OrderUtil.ORDER_STATE_REBACK){
	   		 stateStr = "订单已退货";
	   	}
		return stateStr;
	}

}
