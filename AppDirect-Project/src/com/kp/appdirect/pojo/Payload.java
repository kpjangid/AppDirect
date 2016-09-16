package com.kp.appdirect.pojo;

public class Payload {

	private Order order;

	private Company company;

	private Account account;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ClassPojo [order = " + order + ", company = " + company + ", account = " + account + "]";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
