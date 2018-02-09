package com.quantum.engine.homy.model.ext;

import com.quantum.engine.homy.model.BizOrderHandlerLog;

public class BizOrderHandlerLogExt extends BizOrderHandlerLog {

	private String finOrderStateStr;
	private String userName;
	public String getFinOrderStateStr() {
		return finOrderStateStr;
	}
	public void setFinOrderStateStr(String finOrderStateStr) {
		this.finOrderStateStr = finOrderStateStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
