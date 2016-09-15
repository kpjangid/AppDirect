package com.kp.appdirect.pojo;

public class Company {
	private String phoneNumber;

	private String website;

	private String name;

	private String uuid;

	private String country;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "ClassPojo [phoneNumber = " + phoneNumber + ", website = " + website + ", name = " + name + ", uuid = "
				+ uuid + ", country = " + country + "]";
	}
}
