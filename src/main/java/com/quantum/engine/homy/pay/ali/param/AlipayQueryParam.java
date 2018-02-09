package com.quantum.engine.homy.pay.ali.param;

public class AlipayQueryParam {
	
	private String out_trade_no;//支付时传入的商户订单号，与trade_no必填一个
	private String trade_no;	//支付时返回的支付宝交易号，与out_trade_no必填一个
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

}
