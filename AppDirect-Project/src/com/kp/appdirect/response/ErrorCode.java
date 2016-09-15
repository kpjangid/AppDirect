package com.kp.appdirect.response;

public interface ErrorCode {

	public String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
	public String USER_NOT_FOUND = "USER_NOT_FOUND";
	public String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
	public String MAX_USERS_REACHED = "MAX_USERS_REACHED";
	public String UNAUTHORIZED = "UNAUTHORIZED";
	public String OPERATION_CANCELED = "OPERATION_CANCELED";
	public String CONFIGURATION_ERROR = "CONFIGURATION_ERROR";
	public String INVALID_RESPONSE = "INVALID_RESPONSE";
	public String PENDING = "PENDING";
	public String FORBIDDEN = "FORBIDDEN";
	public String BINDING_NOT_FOUND = "BINDING_NOT_FOUND";
	public String TRANSPORT_ERROR = "TRANSPORT_ERROR";
	public String UNKNOWN_ERROR = "UNKNOWN_ERROR";

}
