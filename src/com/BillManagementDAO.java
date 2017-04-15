package com;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BillManagementDAO {
	static Connection con=null;
	static Statement st=null;
	static ResultSet rs=null;
	static PreparedStatement ps=null;
	static PreparedStatement ps2=null;
	static PreparedStatement ps3=null;

String url="jdbc:oracle:thin:@intvmoradb02:1521:orajavadb";
String pwd="tcstvm";
String username="PJ01DEV_TJA24";
String driver="oracle.jdbc.driver.OracleDriver";


public String generateBill(String[] prodlist,Date sellingDate,double sellingAmount,String custName,long custNo,String payType,String custAddr,String empid)throws ClassNotFoundException,SQLException, ProductAlreadySoldException
{  Bill b =new Bill();
String billid = null;
double abi=0;
	
try{
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pwd);
		System.out.println("connected");
		//for(int i=0;i<prodlist.length;i++){
		
		int flag=0;
		for(String prodid:prodlist){
		String sql9="select * from GroupE_Bill where pdtid=?";
		PreparedStatement ps9 = con.prepareStatement(sql9);
		ps9.setString(1,prodid);
		ResultSet rs9=ps9.executeQuery();
		if(rs9.next()){
			flag=1;
		}
		}
		
		if(flag==0){
		String sql="insert into GROUPE_BILL values ('B'||bill_id_seq.nextval,?,?,?,?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setDate(1,(Date) sellingDate);
		ps.setDouble(2,sellingAmount);
		ps.setString(3, custName);
		ps.setString(4,custAddr);
		ps.setLong(5,custNo);
		ps.setString(6,prodlist[0]);
		ps.setString(7, payType);
		ps.setString(8, empid);
		int a=ps.executeUpdate();
//		System.out.println("value of a"+a);
		//}
		
		for(int i=1;i<prodlist.length;i++){
				String sql1="insert into GROUPE_BILL values ('B'||bill_id_seq.currval,?,?,?,?,?,?,?,?)";
				ps=con.prepareStatement(sql1);
				ps.setDate(1,(Date) sellingDate);
				ps.setDouble(2,sellingAmount);
				ps.setString(3, custName);
				ps.setString(4,custAddr);
				ps.setLong(5,custNo);
				ps.setString(6,prodlist[i]);
				ps.setString(7, payType);
				ps.setString(8, empid);
				int a1=ps.executeUpdate();
//				System.out.println("value of a"+a1);
				}
		
		
//		int a=ps.executeUpdate();
		String sql1="select * from GroupE_Bill where custname=? and custno=?  ";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1,custName);
		ps1.setLong(2, custNo);
		
		
		
		rs=ps1.executeQuery();
		
		while(rs.next()){
			
			billid=rs.getString("BILLID");
			
		}
		
		for(int i=0;i<prodlist.length;i++){
			
			String sql2="update GROUPE_WORKFLOW set status='closed' where pdtid=?";
			
			ps2 = con.prepareStatement(sql2);
			ps2.setString(1,prodlist[i]);
			int a1=ps2.executeUpdate();
			System.out.println(a1++);
		}
		double sellamt=0;
		for(int i=0;i<prodlist.length;i++){
			System.out.println("in calc price");
			String sql3="select price from GROUPE_PRODUCT left join GROUPE_PRODUCTMODEL on GROUPE_PRODUCT.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID where pdtid=?";
            ps3=con.prepareStatement(sql3);
            ps3.setString(1, prodlist[i]);
            rs=ps3.executeQuery();
    		
    		while(rs.next()){
    			
    		sellamt+=rs.getDouble(1);
    		System.out.println("amt is "+sellamt);
    		}
			
		}
		 abi=sellamt-sellingAmount;
		System.out.println("selling price is  "+ abi);
        		
		}
		else{
			throw new ProductAlreadySoldException();
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

return (billid+",.,"+abi);

}
public Bill searchBill(String billid)throws ClassNotFoundException,SQLException,NullPointerException
{
	Bill b=new Bill();

	try{
	 Class.forName(driver);
		con=DriverManager.getConnection(url,username,pwd);
	ps=con.prepareStatement("select pdtid from GROUPE_BILL where billid =?");
	ps.setString(1,billid);
	rs=ps.executeQuery();
	ArrayList<String> plist=new ArrayList<String>();
	while(rs.next()){
		plist.add(rs.getString(1));
	}
	b.setProdlist(plist);
	
	ps=con.prepareStatement("select * from groupe_bill where billid=?");
	ps.setString(1, billid);
	rs=ps.executeQuery();
		while(rs.next())
	{
			b.setBillId(rs.getString(1));
		b.setSellingDate(rs.getDate(2));
		b.setSellingAmt(rs.getDouble(3));
		b.setCusName(rs.getString(4));
		b.setCustAddr(rs.getString(5));
		b.setCustNo(rs.getLong(6));
		b.setPaytype(rs.getString(8));
		
	}	}
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
return b;
}
	public ArrayList<Product> fetchProductsForSale(String empid) throws SQLException, ClassNotFoundException {
		ArrayList<Product> prolist=new ArrayList<Product>();
		
		//empid="E25"; 
		try{
			 Class.forName(driver);
			 con=DriverManager.getConnection(url,username,pwd);
			 ps=con.prepareStatement("select pdtid from GROUPE_WORKFLOW where STATUS='open' and repid=?");
			 ps.setString(1,empid);
			 rs=ps.executeQuery();
			 while(rs.next()){
				 String proid=rs.getString(1);
				 PreparedStatement ps1=con.prepareStatement("select pdtmdlid from GROUPE_PRODUCT where PDTID=?");
				 ps1.setString(1,proid);
				 ResultSet rs1=ps1.executeQuery();
				 rs1.next();
				 String model=rs1.getString(1);
				 
				 
				 String modelname=null;
				 PreparedStatement ps2=con.prepareStatement("select modelname from GROUPE_PRODUCTMODEL where PDTMDLID=?");
				 ps2.setString(1,model);
				 ResultSet rs2=ps2.executeQuery();
				 while(rs2.next()){
				 modelname=rs2.getString(1);
				 }
				 ProductModel productmodel=new ProductModel(model,modelname);
				 Inventory inv=new Inventory(productmodel);
				 Product product=new Product(proid,inv);
				 
				 prolist.add(product);
			 }
			 return prolist;
		}
		
		finally{
			
		}
			 
	}
	public boolean setbillamount(String b, double sellingprice) throws SQLException, ClassNotFoundException {
		boolean flag=false;
		Class.forName(driver);
		 try {
			con=DriverManager.getConnection(url,username,pwd);
			String sql2="update GROUPE_BILL set sellingamt=? where billid=?";
			
			ps2 = con.prepareStatement(sql2);
			ps2.setDouble(1, sellingprice);
			ps2.setString(2,b);
			ps2.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return flag;
	}
	public ArrayList<Bill> viewAllBill(String empid)throws ClassNotFoundException,SQLException
	{
		String role=null;
		ArrayList<Bill> bf=new ArrayList<Bill>();
		
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
				
				String sql1="select * from groupe_bill order by TO_NUMBER(LTRIM(billid,'B'))";
				PreparedStatement ps1=con.prepareStatement(sql1);
				ResultSet rs1=ps1.executeQuery();
				System.out.println("role"+role);
			
			String check="hii";
				
			while(rs1.next())
			{
				if(!check.equals(rs1.getString(1))){
				Bill b=new Bill();
				
				b.setBillId(rs1.getString(1));
				b.setSellingDate(rs1.getDate(2));
				b.setSellingAmt(rs1.getDouble(3));
				b.setCusName(rs1.getString(4));
				b.setCustAddr(rs1.getString(5));
				b.setCustNo(rs1.getLong(6));
				PreparedStatement pss=con.prepareStatement("select pdtid from GROUPE_BILL where billid=?");
				pss.setString(1,b.getBillId());
				ResultSet rss=pss.executeQuery();
				ArrayList<String> plist=new ArrayList<String>();
				while(rss.next()){
					plist.add(rss.getString(1));
				}
				b.setProdlist(plist);
				
				
				b.setPaytype(rs1.getString(8));
				b.setEmpid(rs1.getString(9));
				bf.add(b);
				}
				check=rs1.getString(1);
				
			}
			}
		if(role.equalsIgnoreCase("manager"))
		{
			String sql2="select * from groupe_bill left join groupe_representative on GROUPE_bill.empid=GROUPE_REPRESENTATIVE.REPID where mgrid=? order by TO_NUMBER(LTRIM(billid,'B'))";

			ps2=con.prepareStatement(sql2);
			ps2.setString(1, empid);
			ResultSet rs2=ps2.executeQuery();
			
			String check="hii";
			
			while(rs2.next())
			{
				if(!check.equals(rs2.getString(1))){
				Bill b=new Bill();
				b.setBillId(rs2.getString(1));
			
				b.setSellingDate(rs2.getDate(2));
				System.out.println(b.getSellingAmt());
				b.setSellingAmt(rs2.getDouble(3));
				b.setCusName(rs2.getString(4));
				b.setCustAddr(rs2.getString(5));
				b.setCustNo(rs2.getLong(6));
				PreparedStatement pss=con.prepareStatement("select pdtid from GROUPE_BILL where billid =?");
				pss.setString(1,b.getBillId());
				ResultSet rss=pss.executeQuery();
				ArrayList<String> plist=new ArrayList<String>();
				while(rss.next()){
					plist.add(rss.getString(1));
				}
				b.setProdlist(plist);
				
				b.setPaytype(rs2.getString(8));
				b.setEmpid(rs2.getString(9));
				bf.add(b);
				}
				check=rs2.getString(1);
				
			}
		}
		
		if(role.equalsIgnoreCase("rep"))
		{
			
			String sql3="select * from groupe_bill where empid=? order by TO_NUMBER(LTRIM(billid,'B'))";
			ps3=con.prepareStatement(sql3);
			ps3.setString(1, empid);
			ResultSet rs3=ps3.executeQuery();
			String check="hii";
			
			while(rs3.next())
			{
				if(!check.equals(rs3.getString(1))){
				Bill b=new Bill();
				b.setBillId(rs3.getString(1));
				b.setSellingDate(rs3.getDate(2));
				b.setSellingAmt(rs3.getDouble(3));
				b.setCusName(rs3.getString(4));
				b.setCustAddr(rs3.getString(5));
				b.setCustNo(rs3.getLong(6));
				PreparedStatement pss=con.prepareStatement("select pdtid from GROUPE_BILL where billid =?");
				pss.setString(1,b.getBillId());
				ResultSet rss=pss.executeQuery();
				ArrayList<String> plist=new ArrayList<String>();
				while(rss.next()){
					plist.add(rss.getString(1));
				}
				b.setProdlist(plist);
				
				b.setPaytype(rs3.getString(8));
				b.setEmpid(rs3.getString(9));
				bf.add(b);
				}
				check=rs3.getString(1);
				
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
		return bf;
	}
	public ArrayList<String> loadbill(String empId) throws SQLException, ClassNotFoundException {
		
		ArrayList<String> st=new ArrayList<String>();
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pwd);

		
	
		
		String sql="select billid from groupe_bill where empid=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, empId);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			st.add(rs.getString(1));
		}
		
		return st;
	}

}


