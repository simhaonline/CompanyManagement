package com;

public class Product {

	private String productId;
	private Inventory inventory;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Product(String productId, Inventory inventory) {
		super();
		this.productId = productId;
		this.inventory = inventory;
	}
	
}
