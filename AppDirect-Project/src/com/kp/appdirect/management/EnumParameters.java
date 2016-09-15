package com.kp.appdirect.management;

public enum EnumParameters {

	http_host(0, null),

	http_port(1, null),

	// contexts

	http_user_management_context(2, null),

	http_subscription_management_context(3, null),

	http_notification_management_context(4, null),

	// SSL Configuration Setting

	sslKeystorePath(5, null),

	sslKeystorePassword(6, null),

	host_https_port(7, null),

	sslKeymanagerPassword(8, null),

	sslKeystoreName(9, null);

	private int id;
	private int intDesc;
	private String stringDesc;
	private long longDesc;
	private boolean boolDesc;

	private EnumParameters(int id, int description) {
		this.id = id;
		this.intDesc = description;
	}

	private EnumParameters(int id, String description) {
		this.id = id;
		this.stringDesc = description;
	}

	private EnumParameters(int id, long description) {
		this.id = id;
		this.longDesc = description;
	}

	private EnumParameters(int id, boolean description) {
		this.id = id;
		this.boolDesc = description;
	}

	private EnumParameters() {

	}

	public int getId() {
		return id;
	}

	public void setStringDescription(String description) {
		this.stringDesc = description;
	}

	public void setLongDescription(long description) {
		this.longDesc = description;
	}

	public void setIntDescription(int description) {
		this.intDesc = description;
	}

	public void setBoolDescription(boolean description) {
		this.boolDesc = description;
	}

	public int getIntDescription() {
		return intDesc;
	}

	public String getStringDescription() {
		return stringDesc;
	}

	public boolean getBoolDescription() {
		return boolDesc;
	}

	public long getLongDescription() {
		return longDesc;
	}

}
