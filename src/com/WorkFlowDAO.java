package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkFlowDAO {
	static Connection con=null;
	static Statement st=null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static ResultSet rs2=null;
	static ResultSet rs3=null;
	static PreparedStatement ps=null;
	static PreparedStatement ps1=null;
	static PreparedStatement ps2=null;
	static PreparedStatement ps3=null;
String url="jdbc:oracle:thin:@intvmoradb02:1521:orajavadb";
String pwd="tcstvm";
String username="PJ01DEV_TJA24";
String driver="oracle.jdbc.driver.OracleDriver";

public ArrayList<WorkFlow> viewAllWrokFlow(String empid)throws ClassNotFoundException,SQLException
{
	String role=null;
	String status="Sold";
	ArrayList<WorkFlow> wf=new ArrayList<WorkFlow>();
	try{
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pwd);
		System.out.println("connected");
//ArrayList<String> empList=new ArrayList<>();
		
		Employee emp=new Employee();
		
		String sql="select role from groupe_employee where empid=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, empid);
		rs=ps.executeQuery();
		while(rs.next())
		{
			role=rs.getString(1);
		}
		
		if(role.equalsIgnoreCase("admin")){
			
			String sql1="select * from (select * from GROUPE_WORKFLOW left join groupe_product on GROUPE_WORKFLOW.pdtid=groupe_product.pdtid order by workflowid) A left join GROUPE_PRODUCTMODEL ON A.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID ";
			ps1=con.prepareStatement(sql1);
			rs1=ps1.executeQuery();
			System.out.println("role"+role);
			
		
		
		
		while(rs1.next())
		{
			WorkFlow w=new WorkFlow();
			w.setWorkFlowId(rs1.getString(1));
			w.setProductId(rs1.getString(2));
			w.setRepresentativeId(rs1.getString(3));
			
		
			w.setWorkFlowStatus(rs1.getString(4));
			w.setReductionLimit(rs1.getInt(5));
			w.setModelid(rs1.getString(7));
			w.setModel(rs1.getString(12));
			
			String sql9="select sellingamt from groupe_bill where pdtid=?";
			PreparedStatement ps9=con.prepareStatement(sql9);
			ps9.setString(1,rs1.getString(2));
			ResultSet rs9=ps9.executeQuery();
			if(rs9.next()){
			w.setPrice(rs9.getDouble(1));	
				
			}
			
			else{
				w.setPrice(rs1.getDouble("PRICE"));
			}
			
			
			
			
			wf.add(w);
			
		}
		}
	if(role.equalsIgnoreCase("manager"))
	{
		String sql2="SELECT * FROM (select * from(select * from GROUPE_WORKFLOW left join GROUPE_PRODUCT on GROUPE_workflow.pdtid=groupe_product.pdtid order by workflowid)B left join GROUPE_STORE ON B.STOREID=GROUPE_STORE.STOREID where groupe_store.managerid=? )a left join groupe_productmodel on a.pdtmdlid=groupe_productmodel.pdtmdlid";

		ps2=con.prepareStatement(sql2);
		ps2.setString(1, empid);
		rs2=ps2.executeQuery();
		
		while(rs2.next())
		{
			WorkFlow w=new WorkFlow();
			w.setWorkFlowId(rs2.getString("workflowid"));
			w.setProductId(rs2.getString(2));
			w.setRepresentativeId(rs2.getString("repid"));
			
			w.setWorkFlowStatus(rs2.getString("status"));
			w.setReductionLimit(rs2.getInt(5));
			w.setModelid(rs2.getString(7));
		
			w.setModel(rs2.getString(16));
			String sql4="select sellingamt from groupe_bill where pdtid=?";
			PreparedStatement ps5=con.prepareStatement(sql4);
			ps5.setString(1,rs2.getString(2));
			ResultSet rs5=ps5.executeQuery();
			if(rs5.next()){
				w.setPrice(rs5.getDouble(1));
			}
			else{
				w.setPrice(rs2.getDouble("PRICE"));
			}
			wf.add(w);
			
			
		}
	}
	
	if(role.equalsIgnoreCase("rep"))
	{
		
		String sql3="select * from (select * from GROUPE_WORKFLOW left join groupe_product on GROUPE_WORKFLOW.pdtid=groupe_product.pdtid where repid=? order by workflowid) A left join GROUPE_PRODUCTMODEL ON A.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID ";
		ps3=con.prepareStatement(sql3);
		ps3.setString(1, empid);
		rs3=ps3.executeQuery();
		while(rs3.next())
		{
			WorkFlow w=new WorkFlow();
			w.setWorkFlowId(rs3.getString(1));
			w.setProductId(rs3.getString(2));
			w.setRepresentativeId(rs3.getString(3));
			w.setWorkFlowStatus(rs3.getString(4));
			w.setReductionLimit(rs3.getInt(5));
			w.setModelid(rs3.getString(7));
			w.setModel(rs3.getString(12));
			String sql10="select sellingamt from groupe_bill where pdtid=?";
			PreparedStatement ps10=con.prepareStatement(sql10);
			ps10.setString(1,rs3.getString(2));
			ResultSet rs8=ps10.executeQuery();
			if(rs8.next()){
				w.setPrice(rs8.getDouble(1));
				
			}
			else{
				w.setPrice(rs3.getDouble("PRICE"));
			}
			
			
			
			
			
			
			wf.add(w);
			
			
			
		}
		
	}
	}

	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
		}
	catch(SQLException e)
	{
		e.printStackTrace();
		}
	finally{
		
		if(con!=null)
			con.close();
		if(st!=null)
		st.close();
		if(rs!=null)
			rs.close();
		if(ps!=null)
			ps.close();


}
	return wf;
}
public String reassignEmp(String oldemp,String newemp)throws ClassNotFoundException,SQLException{
	
	try{
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pwd);
		System.out.println("connected");
		ps=con.prepareStatement("update GROUPE_WORKFLOW set repid=? where repid=? and status='open'");
		ps.setString(1, newemp);
		ps.setString(2, oldemp);
		int a=ps.executeUpdate();
		System.out.println(a);
	}
	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
		}
	catch(SQLException e)
	{
		e.printStackTrace();
		}
	finally{
		
		if(con!=null)
			con.close();
		if(st!=null)
		st.close();
		if(rs!=null)
			rs.close();
		if(ps!=null)
			ps.close();
	
	}
	return newemp;
}
}

