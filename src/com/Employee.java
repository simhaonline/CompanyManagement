package com;

import java.sql.Date;



public class Employee {
	
	public Employee(String employeeId, String firstName, String lastName,
			String gender, Date dob, String address, String role,
			String password, long mobNo) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.role = role;
		this.password = password;
		this.mobNo = mobNo;
	}









	private String employeeId;
	public String getEmployeeId() {
		return employeeId;
	}









	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}









	public String getFirstName() {
		return firstName;
	}









	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}









	public String getLastName() {
		return lastName;
	}









	public void setLastName(String lastName) {
		this.lastName = lastName;
	}









	public String getGender() {
		return gender;
	}









	public void setGender(String gender) {
		this.gender = gender;
	}









	public Date getDob() {
		return dob;
	}









	public void setDob(Date dob) {
		this.dob = dob;
	}









	public String getAddress() {
		return address;
	}









	public void setAddress(String address) {
		this.address = address;
	}









	public String getRole() {
		return role;
	}









	public void setRole(String role) {
		this.role = role;
	}









	public String getPassword() {
		return password;
	}









	public void setPassword(String password) {
		this.password = password;
	}









	public long getMobNo() {
		return mobNo;
	}









	public void setMobNo(long mobNo) {
		this.mobNo = mobNo;
	}









	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private String address;
	private String role;
	private String password;
	private long mobNo;
	
	



	



	//default constructor
	public Employee() {
		super();
	}
	
	
	
	
}
