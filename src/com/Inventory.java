package com;

public class Inventory {
 
	private Store store;
	private ProductModel prodModel;
	private int quantity;
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public ProductModel getProdModel() {
		return prodModel;
	}
	public void setProdModel(ProductModel prodModel) {
		this.prodModel = prodModel;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Inventory(Store store, ProductModel prodModel, int quantity) {
		super();
		this.store = store;
		this.prodModel = prodModel;
		this.quantity = quantity;
	}
	public Inventory(Store store, ProductModel prodModel) {
		super();
		this.store = store;
		this.prodModel = prodModel;
		
	}
	public Inventory(ProductModel prodModel) {
		super();
		this.prodModel = prodModel;
	}
	
	
	
	
}
