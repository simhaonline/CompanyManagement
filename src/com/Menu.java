package com;

public class Menu {
	private String model;
	private String modelid;
	private int quantity;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Menu(String model, String modelid, int quantity) {
		super();
		this.model = model;
		this.modelid = modelid;
		this.quantity = quantity;
	}
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	

}
