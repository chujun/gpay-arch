package com.fuiou.gpay.arch.exception;

@SuppressWarnings("serial")
public class SystemException extends RuntimeException {

	private String errorCode;

	private String errorMsg;


	
	public SystemException() {
		super();
	}

	public SystemException(String errorMsg) {
		super(errorMsg);
		this.errorCode="8888";
		this.errorMsg = errorMsg;
	}

	public SystemException(String errorCode, String errorMsg) {
		super("错误码：" + errorCode + "，错误描述：" + errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public SystemException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = "8888";
		this.errorMsg = errorMsg;
	}

	public SystemException(String errorCode, String errorMsg, Throwable cause) {
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
