
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.dto;

public class OrderProductBean {
	 private String productname;
	 private int quantity;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderProductBean(String productname, int quantity) {
		super();
		this.productname = productname;
		this.quantity = quantity;
	}
	


}
