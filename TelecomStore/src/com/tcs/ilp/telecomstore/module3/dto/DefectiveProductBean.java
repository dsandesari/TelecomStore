package com.tcs.ilp.telecomstore.module3.dto;

public class DefectiveProductBean {
private String productcode;
private String productname;
public DefectiveProductBean(String productcode, String productname) {
	super();
	this.productcode = productcode;
	this.productname = productname;
}
public String getProductcode() {
	return productcode;
}
public void setProductcode(String productcode) {
	this.productcode = productcode;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}

}
