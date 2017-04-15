package com;

import java.sql.*;
import java.util.ArrayList;

public class UserManagementDAO {
	

	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@intvmoradb02:1521:ORAJAVADB";
	private String username="PJ01DEV_TJA24";
	private String pass="tcstvm";
	private Connection con=null;

	ResultSet rs=null;
	
	public String getRole(String empId) throws SQLException, ClassNotFoundException
	{
		String role=null;
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		
	
	
		
		 
		String sql="select role from groupe_employee where empid=?";
       PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, empId);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
		
			role=rs.getString(1);
		
			
		}
		
		return role;
	}
	
	public String deleteemp(String empId,String oldemp,String newemp) throws ClassNotFoundException, SQLException
	{
		WorkFlowService wfserv=new WorkFlowService();
		String role=null;
	
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		
	
	
		int i=0;
		 
		String sql="select role from groupe_employee where empid=?";
       PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, empId);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			System.out.println("whilein"+role);
			role=rs.getString(1);
			System.out.println("whileout"+role);
			
		}
		
		if(role.equalsIgnoreCase("admin"))
		
		{
			String sql2="update GROUPE_store set managerid=? where managerid=?";
		       PreparedStatement ps2 = con.prepareStatement(sql2);
		       ps2.setString(1,"0");
				ps2.setString(2,oldemp);
				ps2.executeUpdate();
		
			String sql1="delete from groupe_employee where empid=?";
	       PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1,oldemp);
			ps1.executeUpdate();
			
	
			String sql3="update  GROUPE_REPRESENTATIVE set mgrid=? where mgrid=?";
		       PreparedStatement ps3 = con.prepareStatement(sql3);
		       ps3.setString(1,"0");
				ps3.setString(2,oldemp);
				ps3.executeUpdate();
			
			
			
		}
		if(role.equalsIgnoreCase("manager"))
			
		{
			System.out.println("manager");
			System.out.println(empId+oldemp+newemp);
			String sql1="delete from groupe_employee where empid=?";
		
		       PreparedStatement ps1 = con.prepareStatement(sql1);
		   	ps1.setString(1,oldemp);
		   	i=ps1.executeUpdate();
		   	
		   	String sql2="delete from GROUPE_REPRESENTATIVE where repid=?";
		       PreparedStatement ps2 = con.prepareStatement(sql2);
		    	ps2.setString(1,oldemp);
			
				
				ps2.executeUpdate();
				System.out.println("executer"+i);
				if(i>0)
				{
			
					String id=wfserv.reassignEmp(oldemp, newemp);
					
				}
			
		}
		System.out.println("return"+role);
		return role;
		
	}
	public ArrayList<String> veiwAll(String empId) throws ClassNotFoundException, SQLException
	{
		String role=null;
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		System.out.println("dao"+empId);
		ArrayList<String> empList=new ArrayList<>();
		
		Employee emp=new Employee();
		
		 
		String sql="select role from groupe_employee where empid=?";
       PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, empId);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			role=rs.getString(1);
		}
		System.out.println("role"+role);
		if(role.equalsIgnoreCase("admin"))
		{
			String sql1="select empid from groupe_employee where role=?";
		       PreparedStatement ps1 = con.prepareStatement(sql1);
				
				ps1.setString(1,"Manager");
				rs=ps1.executeQuery();
		
	   while(rs.next())
	   {
		   String empidString=rs.getString("empId");
		  
		

		   empList.add(empidString);
	   }
		}
	   if(role.equalsIgnoreCase("manager"))
		{
			String sql2="select repid from GROUPE_REPRESENTATIVE where mgrid=?";
		       PreparedStatement ps2 = con.prepareStatement(sql2);
				
				ps2.setString(1,empId);
				rs=ps2.executeQuery();
		
	   while(rs.next())
	   {
		   String empidString=rs.getString(1);
			  
			

		   empList.add(empidString);
	   }
		}
	
		
		return empList;
	}
	
	public Employee logincheck(String empId,String pwd) throws SQLException, ClassNotFoundException
	{
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		String sql1="select * from groupe_employee where empid=? and password=?";
	       PreparedStatement ps1 = con.prepareStatement(sql1);
			Employee emp=new Employee();
	       
			ps1.setString(1,empId);
			ps1.setString(2,pwd);
			rs=ps1.executeQuery();
	
while(rs.next())
{
	emp.setRole(rs.getString("role"));
	emp.setFirstName(rs.getString("firstname"));
 
}
		
		return emp;
		
	}
	
	public boolean updateEmp(Employee emp) throws ClassNotFoundException, SQLException
{
		int i=0;
		boolean flag=false;
		try{
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			
			 
			String sql="UPDATE GRoupe_Employee  SET FIRSTNAME=?,LASTNAME=?,Gender=?,DOB=?,ADDRESS=?,password=?,mobilenumber=? WHERE empID=?";
	       PreparedStatement ps = con.prepareStatement(sql);
		   
		   ps.setString(1,emp.getFirstName());
		   ps.setString(2,emp.getLastName());
		   ps.setString(3,emp.getGender());
		   ps.setDate(4,(Date)emp.getDob());
		   ps.setString(5, emp.getAddress());
		   ps.setString(6, emp.getPassword());
		   ps.setLong(7, emp.getMobNo());
		   ps.setString(8, emp.getEmployeeId());
		   i= ps.executeUpdate();
		
		 
			   
		   }
		finally{
		if(i==1)
		{
			flag=true;
		}
		else 
		{
			flag=false;
		}
	}
		
		
		
		
		
		
		
	return flag;
		
	}
	
        public Employee empDetails(String empId) throws SQLException{
        	
        	Employee emp=new Employee();
        	try{
    			Class.forName(driver);
    			con=DriverManager.getConnection(url,username,pass);
    			String sql="select * from groupe_employee where empid=? ";
    			PreparedStatement ps=con.prepareStatement(sql);
    			
    			ps.setString(1, empId);
    			rs=ps.executeQuery();
    		}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    			while(rs.next())
    			{
    			emp.setEmployeeId(rs.getString(1));
    			emp.setFirstName(rs.getString(2));
    			emp.setLastName(rs.getString(3));
    			emp.setGender(rs.getString(4));
    			emp.setDob(rs.getDate(5));
    			emp.setMobNo(rs.getLong(9));
    			emp.setAddress(rs.getString(6));
    			emp.setRole(rs.getString(7));
    			emp.setPassword(rs.getString(8));

    			}
        	
        	
		
		
		return emp;
		
		
	}
        public boolean checkmgr(String storename) throws SQLException
        {
        	boolean flag=false;
        	String mgrid=null;
        	try{
    			Class.forName(driver);
    			con=DriverManager.getConnection(url,username,pass);
    			String sql="select managerid from groupe_store where storename=? ";
    			PreparedStatement ps=con.prepareStatement(sql);
    			
    			ps.setString(1,storename);
    		
    			rs=ps.executeQuery();
    		}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    			while(rs.next())
    			{
    			

    				mgrid=rs.getString(1);
    			
    			}
    			
    			if(mgrid.equalsIgnoreCase("0"))
    			{
    			
    				flag=true;
    			}
        	
			return flag;
        	
        }
	
	public String adduser(Employee emp) throws ClassNotFoundException, SQLException
	{

		
		int i=0;
		String empid=null;
		
boolean flag=false;
		
		flag=checkmob(emp.getMobNo());
		if(flag){
	try{
		
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		
		
		String sql="insert into GroupE_Employee values('E'||emp_id_seq.nextval,?,?,?,?,?,?,'E'||emp_id_seq.currval,?)";
		
			
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,emp.getFirstName());
		ps.setString(2,emp.getLastName());
		ps.setString(3,emp.getGender());
		ps.setDate(4, (Date)emp.getDob());
		ps.setString(5,emp.getAddress());
		ps.setString(6, emp.getRole());
		ps.setLong(7, emp.getMobNo());
	
		i=ps.executeUpdate();
		

	}
	catch (Exception e) {
		e.printStackTrace();
	}
		
		if(i==1)
		{
			try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			
		
			
			String sql="select empid from groupe_employee where mobilenumber=? ";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setLong(1, emp.getMobNo());
			rs=ps.executeQuery();
		
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			while(rs.next())
			{
			empid=rs.getString(1);
			
			
			String sql3="update  GROUPE_REPRESENTATIVE set mgrid=? where mgrid=?";
		       PreparedStatement ps3 = con.prepareStatement(sql3);
		       ps3.setString(2,"0");
				ps3.setString(1,empid);
				ps3.executeUpdate();

			}
			
		}
		}
	
		
		return empid;
		
	}


	public String addrep(Employee emp,String mId) throws SQLException, ClassNotFoundException {

		
		int i=0;
		String empid=null;
		boolean flag=false;
		
		flag=checkmob(emp.getMobNo());
		
		if(flag){
	try{
		
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		String sql="insert into GroupE_Employee values('E'||emp_id_seq.nextval,?,?,?,?,?,?,'E'||emp_id_seq.currval,?)";
		
		
		  
		
			
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,emp.getFirstName());
		ps.setString(2,emp.getLastName());
		ps.setString(3,emp.getGender());
		ps.setDate(4, (Date)emp.getDob());
		ps.setString(5,emp.getAddress());
		ps.setString(6, emp.getRole());
		ps.setLong(7, emp.getMobNo());
		i=ps.executeUpdate();
		
		
  
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		
		if(i==1)
		{
			try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			String sql="select empid from groupe_employee where mobilenumber=? ";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setLong(1, emp.getMobNo());
			rs=ps.executeQuery();
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			while(rs.next())
			{
			empid=rs.getString(1);

			}
			String sql1="insert into groupe_representative values(?,?)";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setString(1,empid);
			ps1.setString(2, mId);
			ps1.executeUpdate();
			
		}
		}
	
		
		return empid;
	}
	public boolean addmgrtostore(Store st) throws SQLException, ClassNotFoundException {
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		boolean flag=false;
	
		
		
		if(st.getStorename().equalsIgnoreCase("mumbai"))
		{
		String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S1'";
			
		PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,st.getMgrId());
        ps.setString(2,"0");
        ps.executeUpdate();
		flag=true;
		}
		if(st.getStorename().equalsIgnoreCase("chennai"))
		{
		String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S4'";
			
		PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,st.getMgrId());
        ps.setString(2,"0");
        ps.executeUpdate();
		flag=true;
		}
		if(st.getStorename().equalsIgnoreCase("delhi"))
		{
			String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S2'";
			
			PreparedStatement ps=con.prepareStatement(sql);
	        ps.setString(1,st.getMgrId());
	        ps.setString(2,"0");
	        ps.executeUpdate();
		flag=true;
		}
		if(st.getStorename().equalsIgnoreCase("Kolkata"))
		{
			String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S3'";
			
			PreparedStatement ps=con.prepareStatement(sql);
	        ps.setString(1,st.getMgrId());
	        ps.setString(2,"0");
	        ps.executeUpdate();
		flag=true;
		}
		
		if(st.getStorename().equalsIgnoreCase("Bangaluru"))
		{
			String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S5'";
			
			PreparedStatement ps=con.prepareStatement(sql);
	        ps.setString(1,st.getMgrId());
	        ps.setString(2,"0");
	        ps.executeUpdate();
		flag=true;
		}
		if(st.getStorename().equalsIgnoreCase("trivandrum"))
		{
			String sql="update GROUPE_store set managerid=? where managerid=? and storeid='S6'";
			
			PreparedStatement ps=con.prepareStatement(sql);
	        ps.setString(1,st.getMgrId());
	        ps.setString(2,"0");
	        ps.executeUpdate();
		flag=true;
		}
		
		return flag;
	}
	public Store storedetils(String empId) throws ClassNotFoundException, SQLException {
		
		Store st=new Store();
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		
	   String sql="select storename,region from groupe_store where managerid=?";
       PreparedStatement ps;
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1, empId);
		
		rs=ps.executeQuery();
		while(rs.next())
		{
			st.setStorename(rs.getString(1));
			st.setRegionName(rs.getString(2));
		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
      
		return st;
	}
	
	public boolean checkmob(long mobileno) throws SQLException, ClassNotFoundException
	{
		boolean flag=true;
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		String sql="select * from groupe_employee where mobilenumber=?";
	       PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setLong(1, mobileno);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
			
				flag=false;
			
				
			}
			
			
			return flag;
		
	}

}
