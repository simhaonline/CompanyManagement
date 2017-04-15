package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductManagementDAO {
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@intvmoradb02:1521:ORAJAVADB";
	private String username="PJ01DEV_TJA24";
	private String pass="tcstvm";
	private Connection con=null;
	private Statement stmt = null;
	ResultSet rs=null;
	ResultSet rs1=null;
	
	
	public String addProductModel(ProductModel model) throws SQLException, ClassNotFoundException {
		
		String pmid = null;
		try{
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			System.out.println("connected");
			String sql="insert into GroupE_ProductModel values ('PM'||productmodel_id_seq.nextval,?,?,?,?,?,'open')";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,model.getModelName());
			ps.setString(2, model.getDescription());
			ps.setString(3, model.getSpecification());
			ps.setDouble(4, model.getPrice());
			ps.setString(5, model.getCategory());
			
			ps.executeUpdate();
			
			String sql1="select * from GroupE_ProductModel where MODELNAME=? and DESCRIPTION=? and SPECIFICATION=? and PRICE=? and CATEGORY=?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1,model.getModelName());
			ps1.setString(2, model.getDescription());
			ps1.setString(3, model.getSpecification());
			ps1.setDouble(4, model.getPrice());
			ps1.setString(5, model.getCategory());
			
			rs=ps1.executeQuery();
			
			while(rs.next()){
				
				pmid=rs.getString("PDTMDLID");
			}
			
			

		}
		finally{
			
		}
		return pmid;
			
			
	}


	public void addProductToStore(String modelid, String storeid, int quantity) throws SQLException, ClassNotFoundException {
		try{
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		System.out.println("connected add product to "+ storeid);
		String sql="insert into GROUPE_PRODUCT values ('P'||product_id_seq.nextval,?,?,?,'open')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,modelid);
		ps.setString(2,storeid);
		ps.setDouble(3,0);
		
		for(int i=0;i<quantity;i++){
			ps.executeUpdate();
			System.out.println("added 1");
		}
		
		
		
		}
		
		finally{
			
		}
		
		
		
		
	}


	public ArrayList<ProductModel> viewAllProductModels() throws SQLException, ClassNotFoundException, Exception {
		ArrayList<ProductModel> tree=new ArrayList<ProductModel>();
		try{
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			System.out.println("connected");
			String sql="select * from GroupE_ProductModel where pdtmdlstatus='open' order by TO_NUMBER(LTRIM(pdtmdlid,'PM'))";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductModel model=new ProductModel(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6));
				tree.add(model);
				
			}
			return tree;
		}
		finally{
			
		}
	}


	public ArrayList<Product> viewAllProductDetails(String empid) throws SQLException, ClassNotFoundException {
		//empid="E19";
		ArrayList<Product> proList=new ArrayList<Product>();
		try{
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			System.out.println("connected");
			String sql="select * from GROUPE_STORE";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int flag=0;
			String sql9="select * from GROUPE_EMPLOYEE";
			PreparedStatement ps9 = con.prepareStatement(sql9);
			ResultSet rs9=ps9.executeQuery();
			while(rs9.next()){
				System.out.println("rs9");
				if((rs9.getString(1)).equals(empid) && rs9.getString(7).equals("Admin")){
					flag=2;
					
					break;
				}
			}
			
			
			String storeid = null;
			while(rs.next()){
				if(empid.equals(rs.getString(4))){
					flag=1;
					storeid=rs.getString(1);
					break;
				}
			}
			System.out.println(storeid);
			if(flag==1){
				String sql1="select * from GROUPE_PRODUCT WHERE STOREID=? and PRODSTATUS='open' order by TO_NUMBER(LTRIM(pdtid,'P'))";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, storeid);
				ResultSet rs1=ps1.executeQuery();
				Product product = null;
				while(rs1.next()){
					System.out.println("rs1");//test
					String sql2="select * from GROUPE_PRODUCTMODEL WHERE PDTMDLID=?";
					PreparedStatement ps2 = con.prepareStatement(sql2);
					ps2.setString(1, rs1.getString(2));
					ResultSet rs2=ps2.executeQuery();
					Inventory inv=null;
					while(rs2.next()){
						System.out.println("rs2");//test
						ProductModel model=new ProductModel(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getDouble(5),rs2.getString(6));
						Store store=new Store(rs.getString(1),rs.getString(2),rs.getString(3));
						inv=new Inventory(store, model);
					}
					
					product=new Product(rs1.getString(1),inv);
					proList.add(product);
				}
				
				
			}
			
			else if(flag==0){
				String sql3="select * from GROUPE_WORKFLOW WHERE REPID=?";
				PreparedStatement ps3 = con.prepareStatement(sql3);
				ps3.setString(1, empid);
				ResultSet rs3=ps3.executeQuery();
				
				Product product = null;
				while(rs3.next()){
					System.out.println("rs3");//test
					String sql5="select * from GROUPE_PRODUCT WHERE PDTID=? order by TO_NUMBER(LTRIM(pdtid,'P'))";
					PreparedStatement ps5 = con.prepareStatement(sql5);
					ps5.setString(1, rs3.getString(2));
					ResultSet rs5=ps5.executeQuery();
					rs5.next();
					
					
					String sql4="select * from GROUPE_PRODUCTMODEL WHERE PDTMDLID=?";
					PreparedStatement ps4 = con.prepareStatement(sql4);
					ps4.setString(1, rs5.getString(2));
					ResultSet rs4=ps4.executeQuery();
					Inventory inv=null;
					ProductModel model = null;
					while(rs4.next()){
						model=new ProductModel(rs4.getString(1),rs4.getString(2),rs4.getString(3),rs4.getString(4),rs4.getDouble(5),rs4.getString(6));
						
					}
					
					
					String sql6="select * from GROUPE_STORE WHERE STOREID=?";
					PreparedStatement ps6 = con.prepareStatement(sql6);
					ps6.setString(1, rs5.getString(3));
					ResultSet rs6=ps6.executeQuery();
					rs6.next();
					Store store=new Store(rs6.getString(1),rs6.getString(2),rs6.getString(3));
					inv=new Inventory(store, model);
					
					
					product=new Product(rs5.getString(1),inv);
					proList.add(product);
				}
				
				
					
			}
			else if(flag==2){
				String sql8="select * from GROUPE_PRODUCT left join GROUPE_STORE on GROUPE_PRODUCT.STOREID=GROUPE_STORE.STOREID where prodstatus='open' order by TO_NUMBER(LTRIM(pdtid,'P'))";
				PreparedStatement ps8 = con.prepareStatement(sql8);
				
				ResultSet rs8=ps8.executeQuery();
				
				Product product = null;
				while(rs8.next()){
					System.out.println("rs8");//test
					String sql7="select * from GROUPE_PRODUCTMODEL WHERE PDTMDLID=?";
					PreparedStatement ps7 = con.prepareStatement(sql7);
					ps7.setString(1, rs8.getString(2));
					ResultSet rs7=ps7.executeQuery();
					Inventory inv=null;
					while(rs7.next()){
						System.out.println("rs7");//test
						ProductModel model=new ProductModel(rs7.getString(1),rs7.getString(2),rs7.getString(3),rs7.getString(4),rs7.getDouble(5),rs7.getString(6));
						Store store=new Store(rs8.getString(6),rs8.getString(7),rs8.getString(8));
						inv=new Inventory(store, model);
						product=new Product(rs8.getString(1),inv);
						proList.add(product);
						
					}
					
					
				}
				
				
			}
			
			
		
		}
		finally{
			
		}
		return proList;

	}


	public String assignProductToRep(String modelid, String repid, int quantity, double reduction, String managerid) throws Exception {
		
		
		try{
		System.out.println(modelid);
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		System.out.println("connected");
		String sql1="select * from GROUPE_STORE where MANAGERID=?";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, managerid);
		ResultSet rs1=ps1.executeQuery();
		rs1.next();
		String storeid=rs1.getString(1);
		System.out.println(storeid);
		
		String sql="select * from GROUPE_PRODUCT where PDTMDLID=? AND STOREID=? AND PRODSTATUS='open'";
		PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
		
		System.out.println(storeid);
		System.out.println(modelid);
		
		ps.setString(1, modelid);
		ps.setString(2, storeid);
		ResultSet rs=ps.executeQuery();
		//int count=0;
	
		int rowcount = 0;
		while(rs.next()) {
		  rowcount++;
		  
		}
		System.out.println(rowcount);
		
		rs.beforeFirst();
		System.out.println(rowcount);
		String result="Some Error Happened";
		
		if(rowcount>=quantity){
			
			
			
			for(int i=0;i<quantity;i++){
			System.out.println(rowcount);	
			System.out.println("whileloopentry");
			rs.next();
			String sql2="insert into GROUPE_WORKFLOW values('W'||workflow_id_seq.nextval,?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, rs.getString(1));
			ps2.setString(2, repid);
			ps2.setString(3, "open");
			ps2.setDouble(4, reduction);
			ps2.executeUpdate();
			
			String sql3="UPDATE GROUPE_PRODUCT SET PRODSTATUS='closed' where PDTID=?";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, rs.getString(1));
			ps3.executeUpdate();
			//count++;
			}
			
			result="Assigned"; 
		}
		else{
			result="Not enough products available";
		}
		
		return result;
		
	}
		finally{
			
		}
	}
	
	
	public ProductModel searchDetails(String pmid) throws ClassNotFoundException, SQLException {
		try{
			 
			Class.forName(driver);
			 con=DriverManager.getConnection(url,username,pass);
			 PreparedStatement ps;
			 ps=con.prepareStatement("select * from groupe_productmodel where PDTMDLID=?");
			 ps.setString(1,pmid);
			 rs=ps.executeQuery();
			 rs.next();
			 ProductModel model=new ProductModel();
			 model.setModelid(pmid);
			 model.setModelName(rs.getString(2));
			 model.setDescription(rs.getString(3));
			 model.setSpecification(rs.getString(4));
			 model.setPrice(rs.getDouble(5));
			 model.setCategory(rs.getString(6));
			 return model;		 
	}finally{
		
	}
		

	}

public boolean prodUpdate(ProductModel pro) throws ClassNotFoundException, SQLException {
		
		int i=0;
		boolean flag=false;
		try{
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,pass);
			
			 
			String sql="UPDATE GRoupe_productmodel  SET PRICE=?,DESCRIPTION=?,SPECIFICATION=? WHERE PDTMDLID=?";
	       PreparedStatement ps = con.prepareStatement(sql);
		   
		   ps.setDouble(1,pro.getPrice());
		   ps.setString(2,pro.getDescription());
		   ps.setString(3,pro.getSpecification());
		   ps.setString(4,pro.getModelid());
		  
		   i= ps.executeUpdate();
		   if(i!=0)
			{
				flag=true;
			}
			else 
			{
				flag=false;
			}
		   System.out.println(i);
		   System.out.println(flag);
		   return flag;	   
		   }
		finally{
		
		}
		
		
		
		
		
		
		
	
	}
	
	
	public ArrayList<ProductModel> fetchProductModels() throws SQLException, ClassNotFoundException {
		ArrayList<ProductModel> promodellist=new ArrayList<ProductModel>();
		
		//empid="E25";//test REMOVEEEEEE 
		try{
			 Class.forName(driver);
			 con=DriverManager.getConnection(url,username,pass);
			 PreparedStatement ps;
			 ps=con.prepareStatement("select * from groupe_productmodel where pdtmdlstatus='open' order by TO_NUMBER(LTRIM(pdtmdlid,'PM'))");
			 rs=ps.executeQuery();
			 while(rs.next()){
				 String promdlid=rs.getString(1);
				 String promdlname=rs.getString(2);
				 ProductModel productmodel=new ProductModel(promdlid,promdlname);
				 promodellist.add(productmodel);
			 }
			 return promodellist;
		}
		
		finally{
			
		}
			 
	}


	public void deletemodel(String pmid) throws ClassNotFoundException, SQLException, NoDeletionException {
		try{
			 Class.forName(driver);
			 con=DriverManager.getConnection(url,username,pass);
			 PreparedStatement ps;
			 ps=con.prepareStatement("update GROUPE_PRODUCTMODEL set pdtmdlstatus='closed' where pdtmdlid=? and pdtmdlstatus='open'");
			 ps.setString(1,pmid);
			 int i=ps.executeUpdate();
			 if(i==0){
				 throw new NoDeletionException();
			 }

		
	}finally{
		
	}
	}


	public ArrayList<Menu> getMenu(String empid, String role) throws ClassNotFoundException, SQLException {
		 
		ArrayList<Menu> menu=new ArrayList<Menu>();
		Class.forName(driver);
		con=DriverManager.getConnection(url,username,pass);
		 
		if(role.equals("Manager")){
			 PreparedStatement ps;
			 ps=con.prepareStatement("select * from (select * from GROUPE_PRODUCT left join GROUPE_STORE on GROUPE_PRODUCT.STOREID=GROUPE_STORE.STOREID) A left join GROUPE_PRODUCTMODEL ON A.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID  where prodstatus='open' and MANAGERID=? order by TO_NUMBER(LTRIM(A.pdtmdlid,'PM'))");
			 ps.setString(1,empid);
			 ResultSet rs=ps.executeQuery();
			 String check="check";
			 int quantity=0;
			 while(rs.next()){
				if(!(rs.getString(2).equals(check))){
					Menu men=new Menu();
					 men.setModelid(rs.getString(2));
					 men.setModel(rs.getString(11));
					 PreparedStatement ps1=con.prepareStatement("select * from (select * from GROUPE_PRODUCT left join GROUPE_STORE on GROUPE_PRODUCT.STOREID=GROUPE_STORE.STOREID) A left join GROUPE_PRODUCTMODEL ON A.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID  where prodstatus='open' and MANAGERID=? and A.PDTMDLID=?");
					 ps1.setString(1,empid);
					 ps1.setString(2,rs.getString(2));
					 ResultSet rs1=ps1.executeQuery();
					 quantity=0;
					 while(rs1.next()){
						 quantity++;
					 }
					 men.setQuantity(quantity);
					 menu.add(men);
					 check=rs.getString(2);
				}
//				
			 }
			
		}
		else if(role.equals("Rep")){
			 PreparedStatement ps;
			 ps=con.prepareStatement("select * from (select * from GROUPE_WORKFLOW left join GROUPE_PRODUCT on GROUPE_WORKFLOW.PDTID=GROUPE_PRODUCT.PDTID) Z left join GROUPE_PRODUCTMODEL on Z.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID where prodstatus='closed' and repid=?  order by TO_NUMBER(LTRIM(Z.pdtmdlid,'PM'))");
			 ps.setString(1,empid);
			 ResultSet rs=ps.executeQuery();
			 String check="check";
			 int quantity=0;
			 while(rs.next()){
				if(!(rs.getString(7).equals(check))){
					Menu men=new Menu();
					 men.setModelid(rs.getString(7));
					 men.setModel(rs.getString(12));
					 PreparedStatement ps1=con.prepareStatement("select * from (select * from GROUPE_WORKFLOW left join GROUPE_PRODUCT on GROUPE_WORKFLOW.PDTID=GROUPE_PRODUCT.PDTID) Z left join GROUPE_PRODUCTMODEL on Z.PDTMDLID=GROUPE_PRODUCTMODEL.PDTMDLID where prodstatus='closed'  and repid=? AND  Z.PDTMDLID=?");
					 ps1.setString(1,empid);
					 ps1.setString(2,rs.getString(7));
					 ResultSet rs1=ps1.executeQuery();
					 quantity=0;
					 while(rs1.next()){
						 quantity++;
					 }
					 men.setQuantity(quantity);
					 menu.add(men);
					 check=rs.getString(7);
				}
//				
			 }
		}
		return menu;
		
		
		
		
	}

}
