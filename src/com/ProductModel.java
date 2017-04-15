package com;

public class ProductModel {
String modelid;
String modelName;
String description;
String specification;
double price;
String category;




public ProductModel() {
	super();
}
public ProductModel(String modelid, String modelName, String description,
		String specification, double price, String category) {
	super();
	this.modelid = modelid;
	this.modelName = modelName;
	this.description = description;
	this.specification = specification;
	this.price = price;
	this.category = category;
}
public String getModelid() {
	return modelid;
}
public void setModelid(String modelid) {
	this.modelid = modelid;
}
public String getModelName() {
	return modelName;
}
public void setModelName(String modelName) {
	this.modelName = modelName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getSpecification() {
	return specification;
}
public void setSpecification(String specification) {
	this.specification = specification;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public ProductModel(String modelid, String modelName) {
	super();
	this.modelid = modelid;
	this.modelName = modelName;
}




}
