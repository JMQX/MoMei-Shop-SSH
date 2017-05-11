package com.momei.domain;

public class Book {
	
	  private int bookId;
	  private int userId;
	  private String Name;
	  private String userName;
	  private String statu;
	  private java.util.Date dates_order;
	  private String address;
	  private String phoneNum;
	  private String pay_way;
	  private String send_time;
	  private String message;
	  private int count;
	  private double pay;
	  private String comment_statu;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public java.util.Date getDates_order() {
		return dates_order;
	}
	public void setDates_order(java.util.Date datesOrder) {
		dates_order = datesOrder;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String payWay) {
		pay_way = payWay;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment_statu() {
		return comment_statu;
	}
	public void setComment_statu(String commentStatu) {
		comment_statu = commentStatu;
	}
	  

}
