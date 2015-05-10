
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.dto;

public class ProductBean {
	private String productmodel_id;
	private String productmodel_name;
	private String productmodel_des;
	private String productmodel_feature;
	private float price;
	private int threshold;
	
	public ProductBean(String productmodelId, String productmodelName,
			String productmodelDes, String productmodelFeature, float price,
			int threshold) {
		super();
		productmodel_id = productmodelId;
		productmodel_name = productmodelName;
		productmodel_des = productmodelDes;
		productmodel_feature = productmodelFeature;
		this.price = price;
		this.threshold = threshold;
	}
	public String getProductmodel_id() {
		return productmodel_id;
	}
	public void setProductmodel_id(String productmodelId) {
		productmodel_id = productmodelId;
	}
	public String getProductmodel_name() {
		return productmodel_name;
	}
	public void setProductmodel_name(String productmodelName) {
		productmodel_name = productmodelName;
	}
	public String getProductmodel_des() {
		return productmodel_des;
	}
	public void setProductmodel_des(String productmodelDes) {
		productmodel_des = productmodelDes;
	}
	public String getProductmodel_feature() {
		return productmodel_feature;
	}
	public void setProductmodel_feature(String productmodelFeature) {
		productmodel_feature = productmodelFeature;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}
