package com;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;


public class Bill {

	private String billId;
	private Date sellingDate;
	private double sellingAmt;
	private String custName;
	private Long custNo;
	private String custAddr;
	private String empid;
	
	private String payType;
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	private ArrayList<String> prodlist;
	
	public ArrayList<String> getProdlist() {
		return prodlist;
	}
	public void setProdlist(ArrayList<String> prodlist) {
		this.prodlist = prodlist;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public Date getSellingDate() {
		return sellingDate;
	}
	public void setSellingDate(Date sellingDate) {
		this.sellingDate = sellingDate;
	}
	public double getSellingAmt() {
		return sellingAmt;
	}
	public void setSellingAmt(double d) {
		this.sellingAmt = d;
	}
	public String getCusName() {
		return custName;
	}
	public void setCusName(String cusName) {
		this.custName = cusName;
	}
	public Long getCustNo() {
		return custNo;
	}
	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}
	public String getPaytype() {
		return payType;
	}
	public void setPaytype(String paytype) {
		this.payType = paytype;
	}
	
	/*public Bill(int billId, Date sellingDate, int sellingAmt, String cusName,
			int custNo, String paytype, ArrayList<Product> prodList) {
		super();
		this.billId = billId;
		this.sellingDate = sellingDate;
		this.sellingAmt = sellingAmt;
		this.custName = cusName;
		this.custNo = custNo;
		this.payType = paytype;
		this.prodList = prodList;
	}
	*/
}
