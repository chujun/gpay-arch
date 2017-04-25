package com.fuiou.gpay.exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception {

    private String errorCode;

    private String errorMsg;


    public BusinessException() {
        super();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = "8888";
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super("错误码：" + errorCode + "，错误描述：" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg, Throwable cause) {
        super("错误码：" + errorCode + "，错误描述：" + errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
