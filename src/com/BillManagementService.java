package com;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class BillManagementService {

	public String generateBill(String[] prodlist,Date sellingDate,double sellingAmount,String custName,long custNo,String payType,String custAddr,String empid) throws ClassNotFoundException, SQLException, ProductAlreadySoldException{
		
	BillManagementDAO bill=new BillManagementDAO();
	return bill.generateBill(prodlist,sellingDate,sellingAmount,custName,custNo,payType,custAddr,empid);
	
}

public Bill searchBill(String billid)throws ClassNotFoundException, SQLException,NullPointerException
{
	
	//ArrayList<Bill> bi=new ArrayList<Bill>();
	BillManagementDAO bill=new BillManagementDAO();
 return bill.searchBill(billid);

}

public ArrayList<Product> fetchProductsForSale(String empid) throws ClassNotFoundException, SQLException {
	 ArrayList<Product> prolist=new  ArrayList<Product>();
	 BillManagementDAO bmd=new BillManagementDAO();
	 return bmd.fetchProductsForSale(empid);
	
	}

public boolean setbillamount(String b, double sellingprice) throws ClassNotFoundException, SQLException {
	BillManagementDAO bmd=new BillManagementDAO();
	return bmd.setbillamount(b,sellingprice);
	
	
	
}
public ArrayList<Bill> viewAllBill(String empid)throws ClassNotFoundException,SQLException{
	BillManagementDAO bmd=new BillManagementDAO();
	return bmd.viewAllBill(empid);
}

public ArrayList<String> loadbill(String empId) throws ClassNotFoundException, SQLException {
	BillManagementDAO bmd=new BillManagementDAO();
	return bmd.loadbill(empId);
	
	
}
}
