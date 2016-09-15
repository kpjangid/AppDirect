package com.kp.appdirect.pojo;

public class Order {
	
	private String pricingDuration;

	private Item item;

	private String editionCode;

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	@Override
	public String toString() {
		return "ClassPojo [pricingDuration = " + pricingDuration + ", item = " + item + ", editionCode = " + editionCode
				+ "]";
	}
}
