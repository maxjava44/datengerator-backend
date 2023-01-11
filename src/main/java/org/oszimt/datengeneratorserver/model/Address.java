package org.oszimt.datengeneratorserver.model;
public class Address {
	private String street;
	private int plz;
	private String town;
	private int id;


	public Address(String street, int plz, String town, int id) {
		super();
		this.street = street;
		this.plz = plz;
		this.town = town;
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



}
