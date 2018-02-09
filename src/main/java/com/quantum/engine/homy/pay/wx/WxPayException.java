package com.quantum.engine.homy.pay.wx;

public class WxPayException extends Exception {

    private static final long serialVersionUID = 3743313534667643060L;

    public WxPayException() {
        super();
    }

    public WxPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxPayException(String message) {
        super(message);
    }

    public WxPayException(Throwable cause) {
        super(cause);
    }

}
