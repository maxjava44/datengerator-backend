package org.oszimt.datengeneratorserver.model;
public class Customer {
	protected int id;
	protected String tel;
	protected String email;

	public Customer(int id) {
		this(id, "", "");
	}

	public Customer(int id, String tel, String email) {
		super();
		this.id = id;
		this.tel = tel;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



}
