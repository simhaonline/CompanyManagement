package com;

public class Customer {

	private String custName;
	private int custNo;
	private String payType;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getCustNo() {
		return custNo;
	}
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Customer(String custName, int custNo, String payType) {
		super();
		this.custName = custName;
		this.custNo = custNo;
		this.payType = payType;
	}
	
}
