package com;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductManagementService {

	public String addProductModel(ProductModel model) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		String pmid=man.addProductModel(model);
		return pmid;
	}

	public void addProductToStore(String modelid, String storeid, int quantity) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		man.addProductToStore(modelid,storeid,quantity);
		
	}

	public ArrayList<ProductModel> viewAllProductModels() throws Exception {
		ProductManagementDAO man=new ProductManagementDAO();
		ArrayList<ProductModel> tree=new ArrayList<ProductModel>();
		tree=man.viewAllProductModels();
		return tree;
	}

	public ArrayList<Product> viewAllProductDetails(String empid) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		ArrayList<Product> prodlist=new ArrayList<Product>();
		prodlist=man.viewAllProductDetails(empid);
		return prodlist;
	}

	public String assignProductToRep(String modelid, String repid, int quantity, double reduction, String managerid) throws Exception {
		ProductManagementDAO man=new ProductManagementDAO();
		return man.assignProductToRep(modelid,repid,quantity,reduction,managerid);
		
	}

	public ArrayList<ProductModel> fetchProductModels() throws SQLException, ClassNotFoundException {
		ProductManagementDAO man=new ProductManagementDAO();
		return man.fetchProductModels();
	}
	
	public ProductModel searchDetails(String pmid) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		 ProductModel pro=new ProductModel();
		return pro=man.searchDetails(pmid);
		
	}
	
	public boolean prodUpdate(ProductModel pro) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		
		return man.prodUpdate(pro);
	}

	public void deletemodel(String pmid) throws ClassNotFoundException, SQLException, NoDeletionException  {
		ProductManagementDAO man=new ProductManagementDAO();
		man.deletemodel(pmid);
		
	}

	public ArrayList<Menu> getMenu(String empid, String role) throws ClassNotFoundException, SQLException {
		ProductManagementDAO man=new ProductManagementDAO();
		
		return(man.getMenu(empid,role));
		
	}
	
	
	}

