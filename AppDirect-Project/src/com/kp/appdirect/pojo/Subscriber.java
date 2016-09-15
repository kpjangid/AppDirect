package com.kp.appdirect.pojo;

public class Subscriber {
	
	private Payload payload;

	private Marketplace marketplace;

	private String type;

	private Creator creator;

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ClassPojo [payload = " + payload + ", marketplace = " + marketplace + ", type = " + type
				+ ", creator = " + creator + "]";
	}
}
