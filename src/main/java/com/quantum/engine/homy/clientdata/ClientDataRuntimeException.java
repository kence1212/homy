package com.quantum.engine.homy.clientdata;

public class ClientDataRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientDataRuntimeException() {
		super();
	}

	public ClientDataRuntimeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ClientDataRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientDataRuntimeException(String arg0) {
		super(arg0);
	}

	public ClientDataRuntimeException(Throwable arg0) {
		super(arg0);
	}
	
	
}
