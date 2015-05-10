
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.dto;

public class OrderShipmentBean {
	 private String cust_name;
	 private String address;
	 private long phno;
	 private String email;
	 	 	 
	public OrderShipmentBean(String custName, String address, long phno, String email) {
		super();
		cust_name = custName;
		this.address = address;
		this.phno = phno;
		this.email = email;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String custName) {
		cust_name = custName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
