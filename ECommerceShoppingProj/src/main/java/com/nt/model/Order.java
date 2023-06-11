package com.nt.model;

public class Order extends Product{
	
	private int oid;
	private int uid;
	private int quantity;
	private String date;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	

}
