package org.oszimt.datengeneratorserver.model;
public class Consumer extends Customer {
	private String firstname;
	private String lastname;

	public Consumer(String firstname, String lastname, int id) {
		super(id, "", "");
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Consumer(String firstname, String lastname, int id, String tel, String email) {
		super(id, tel, email);
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}
