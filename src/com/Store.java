package com;

import java.util.ArrayList;

public class Store {

	private ArrayList<Product> prodList= new ArrayList<Product>();
	private String mgrId;
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	private String storeId;
	private String storeName;
	private String regionName;
	public ArrayList<Product> getProdList() {
		return prodList;
	}
	public void setProdList(ArrayList<Product> prodList) {
		this.prodList = prodList;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStorename() {
		return storeName;
	}
	public void setStorename(String storename) {
		this.storeName = storename;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Store(ArrayList<Product> prodList, String storeId, String storename,
			String regionName) {
		super();
		this.prodList = prodList;
		this.storeId = storeId;
		this.storeName = storename;
		this.regionName = regionName;
	}
	public Store(String storeId, String storename,
			String regionName) {
		super();
		
		this.storeId = storeId;
		this.storeName = storename;
		this.regionName = regionName;
	}
	public Store() {
		// TODO Auto-generated constructor stub
	}
	
	
}
