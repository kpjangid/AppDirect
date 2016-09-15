package com.kp.appdirect.response;

public class Response {

	private String message;

	private String errorCode;

	private boolean success;

	private String accountIdentifier;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	@Override
	public String toString() {
		return "ClassPojo [message = " + message + ", errorCode = " + errorCode + ", success = " + success
				+ ", accountIdentifier = " + accountIdentifier + "]";
	}

}
