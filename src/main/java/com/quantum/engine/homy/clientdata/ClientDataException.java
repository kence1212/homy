package com.quantum.engine.homy.clientdata;

/**
 * 
 * @ClassName: ClientDataException 
 * @Description: 数据异常
 * @author nicya
 * @date 2017年11月6日 下午2:31:39 
 *
 */
public class ClientDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClientDataException() {
		super();
	}

	public ClientDataException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ClientDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientDataException(String arg0) {
		super(arg0);
	}

	public ClientDataException(Throwable arg0) {
		super(arg0);
	}
	
	

}
