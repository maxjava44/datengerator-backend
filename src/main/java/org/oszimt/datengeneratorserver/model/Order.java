package org.oszimt.datengeneratorserver.model;
public class Order {
	private String comment;
	private String status;
	private int id;
	private String date;
	private String time;

	public Order(int id, String comment, String status) {
		this(comment, status, id, "", "");
	}

	public Order(String comment, String status, int id, String date, String time) {
		super();
		this.comment = comment;
		this.status = status;
		this.id = id;
		this.date = date;
		this.time = time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


}
