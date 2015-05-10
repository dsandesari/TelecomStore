/*
 @author T139 Group C 
 */
package com.tcs.ilp.telecomstore.module3.dto;
public class OrderDetailsBean {
	private int order_id;
	private String order_date;
	private int order_amount;
	private String product_status;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int orderId) {
		order_id = orderId;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String orderDate) {
		order_date = orderDate;
	}
	public int getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(int orderAmount) {
		order_amount = orderAmount;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String productStatus) {
		product_status = productStatus;
	}
	public OrderDetailsBean(int orderId, String orderDate, int orderAmount,
			String productStatus) {
		super();
		order_id = orderId;
		order_date = orderDate;
		order_amount = orderAmount;
		product_status = productStatus;
	}
	public OrderDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
