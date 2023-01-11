package org.oszimt.datengeneratorserver.model;
public class Company extends Customer {
	private int creditLimit;
	private String name;
	private String contact;

	public Company(int id, String name, String contact) {
		super(id, "", "");
	}

	public Company(int id, int creditLimit, String name, String contact, String tel, String email) {
		super(id);
		this.creditLimit = creditLimit;
		this.name = name;
		this.contact = contact;
	}

	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}


}
