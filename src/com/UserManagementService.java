package com;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManagementService {
	
	UserManagementDAO umdao=new UserManagementDAO();
	
	
	public String addrep(Employee emp,String mId) throws ClassNotFoundException, SQLException
	{
		
		
		String empId=umdao.addrep(emp, mId);
	
		return empId;
		
	}
	

	public String deleteemp(String empId,String oldemp,String newemp) throws ClassNotFoundException, SQLException
	{
		return umdao.deleteemp(empId, oldemp, newemp);
	}
	public Employee logincheck(String uname,String pwd) throws SQLException, ClassNotFoundException
	{
		return umdao.logincheck(uname, pwd);
		
	}
	public Employee empDetails(String empId) throws SQLException{
		
		
		Employee emp=umdao.empDetails(empId);
		
		return emp;
		
		
	}
	
	public boolean updateEmp(Employee emp) throws ClassNotFoundException, SQLException
     {
		boolean flag=false;
		
		
		 flag=umdao.updateEmp(emp);
		
		
	return flag;
		
	}
	
	public ArrayList<String> veiwAll(String empId) throws ClassNotFoundException, SQLException
	{
		
		ArrayList<String> empList =new ArrayList<>();
		
		empList=umdao.veiwAll(empId);
		
		return empList;
		
	}


	public String getRole(String empId) throws ClassNotFoundException, SQLException{
		return umdao.getRole(empId);
		
	}


	public boolean addmgrtostore(Store st) throws ClassNotFoundException, SQLException {
	return umdao.addmgrtostore(st);
		
	}


	public Store storedetils(String empId) throws ClassNotFoundException, SQLException {
		
		return umdao.storedetils(empId);
	}

}
