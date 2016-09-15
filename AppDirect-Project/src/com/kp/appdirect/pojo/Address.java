package com.kp.appdirect.pojo;

public class Address {
	private String lastName;

	private String fullName;

	private String firstName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "ClassPojo [lastName = " + lastName + ", fullName = " + fullName + ", firstName = " + firstName + "]";
	}
}
