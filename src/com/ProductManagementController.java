package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductManagementController
 */
public class ProductManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fun=request.getParameter("fun");
		if(fun.equals("addmodel")){
			try{
			String modelname=request.getParameter("modelname");
			String modeldescription=request.getParameter("modeldescription");
			String modelspecification=request.getParameter("modelspecification");
			String pr=request.getParameter("price");
			double price=0;;
			
				price=Double.parseDouble(pr);
//			}catch(Exception e){
//				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
//				request.setAttribute("error", "Please enter price as a number");
//				er.include(request, response);
//			}
			
			String category=request.getParameter("category");
			
			ProductModel model=new ProductModel("na", modelname, modeldescription, modelspecification, price, category);
			ProductManagementService man=new ProductManagementService();
			String pmid = null;
			
				pmid=man.addProductModel(model);
				
				PrintWriter out=response.getWriter();
				//out.println(pmid);
				RequestDispatcher rd = request.getRequestDispatcher("modeladded.jsp");
				request.setAttribute("pmid", pmid);
				rd.forward(request, response);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.include(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Database Error. Please try entering specified type of inputs");
				er.forward(request, response);
			} catch(Exception e){
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.include(request, response);
				e.printStackTrace();
			}
			
			
		}
		
		else if(fun.equals("addproduct")){
			try {
			String modelid=request.getParameter("products");
			String storeid=request.getParameter("store");
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			System.out.println(modelid);
			System.out.println(storeid);
			ProductManagementService man= new ProductManagementService();
			
				man.addProductToStore(modelid,storeid,quantity);
				
//				PrintWriter out=response.getWriter();
//				out.println(quantity+" PRODUCTS ADDED");
				RequestDispatcher er = request.getRequestDispatcher("info.jsp");
				request.setAttribute("info", quantity+" Products Added");
				er.forward(request, response);
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}
			
			
		}
		
		
		else if(fun.equals("viewallproductmodels")){
			try{
			ArrayList<ProductModel> tree=new ArrayList<ProductModel>();
			ProductManagementService man= new ProductManagementService();
			
				tree=man.viewAllProductModels();
				RequestDispatcher rd = request.getRequestDispatcher("showingAllModels.jsp");
				request.setAttribute("tree", tree);
				rd.include(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.include(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Database Error. Please try entering specified type of inputs");
				er.forward(request, response);
			}catch(NoDeletionException e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "No Deletion");
				er.forward(request, response);
			}
			
			catch(Exception e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person ");
				er.forward(request, response);
			}
			finally{
				
			}
			
			
			
			
		}
		
		else if(fun.equals("viewallproductdetails")){
			try {
			ArrayList<Product> prodlist=new ArrayList<Product>();
			HttpSession session=request.getSession();
			String empid=(String)session.getAttribute("username");
			System.out.println(empid);
			ProductManagementService man= new ProductManagementService();
			
				prodlist=man.viewAllProductDetails(empid);
				
				RequestDispatcher rd = request.getRequestDispatcher("showingAllProducts.jsp");
				request.setAttribute("prodlist", prodlist);
				rd.include(request, response);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}
			
		
			
		}
		else if(fun.equals("assignproductstorep")){
			try{
			
			String modelid=request.getParameter("products");
			System.out.println(modelid);
			String repid=request.getParameter("repid");
			String qua=request.getParameter("quantity");
			String red=request.getParameter("reduction");
			int quantity = 0;
			double reduction=0;
			
			quantity=Integer.parseInt(qua);
			reduction=Double.parseDouble(red);
			HttpSession session=request.getSession();
			
			String managerid=(String) session.getAttribute("username");
			System.out.println(managerid);
			ProductManagementService man= new ProductManagementService();
			String result = null;
			result = man.assignProductToRep(modelid,repid,quantity,reduction,managerid);
			PrintWriter out=response.getWriter();
			//out.println("<html><body><center><p><font color=red>"+result+"</font></p></center></body></html>");
			request.setAttribute("info",result);
			RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
			rd.include(request, response);
			}catch (Exception e) {
				System.out.println("err Parse");
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Please enter numbers in the respective fields");
				er.forward(request, response);
				e.printStackTrace();
			}
			
			
		}
		
		else if(fun.equals("fetchproductmodel")){
			try {
			System.out.println("arjyhfghfghf");
			ArrayList<ProductModel> promodellist=new ArrayList<ProductModel>();
//			HttpSession session=request.getSession();
//			String empid=(String) session.getAttribute("username");
			ProductManagementService man= new ProductManagementService();
			
				promodellist=man.fetchProductModels();
				System.out.println("arr"+promodellist.size());
				
				request.setAttribute("promodellist", promodellist);
				RequestDispatcher rd=request.getRequestDispatcher("addProductToStore.jsp");
				rd.include(request, response);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}catch(Exception e){
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}
			
			
			
		}
		
		
		
		else if(fun.equals("fetchproduct")){
			try {
		
			ArrayList<ProductModel> promodellist=new ArrayList<ProductModel>();
			ArrayList<String> emplist=new ArrayList<String>();
//			HttpSession session=request.getSession();
//			String empid=(String) session.getAttribute("username");
			ProductManagementService man= new ProductManagementService();
			UserManagementService use= new UserManagementService();
				HttpSession session=request.getSession();
				String empId=(String) session.getAttribute("username");
				promodellist=man.fetchProductModels();
				emplist=use.veiwAll(empId);
			
				
				request.setAttribute("promodellist", promodellist);
				request.setAttribute("emplist", emplist);
				RequestDispatcher rd=request.getRequestDispatcher("assignProduct.jsp");
				rd.include(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}catch(Exception e){
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}
			
		
			
		}
		
		else if(fun.equals("updateprod")){
			
			ProductManagementService man= new ProductManagementService();
			String pmid=request.getParameter("pmid");
			System.out.println(pmid);
			ProductModel prd=null;
			
			try {
				prd=man.searchDetails(pmid);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(prd.getModelName());
			request.setAttribute("prd", prd);
			RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
			rd.include(request, response);
			
			
		}
		else if(fun.equals("compdetails")){
			
			ProductManagementService man= new ProductManagementService();
		//String proId=(String)request.getParameter("pmid");
			try {
			ProductModel pro=new ProductModel();
			pro.setModelid(request.getParameter("modelid"));
			pro.setModelName(request.getParameter("modelname"));
			pro.setSpecification(request.getParameter("modelspecification"));
			pro.setDescription(request.getParameter("modeldescription"));
			pro.setPrice(Double.parseDouble(request.getParameter("price")));
			pro.setCategory(request.getParameter("category"));
			
			boolean flag=false;
			
				flag = man.prodUpdate(pro);
				
				if(flag)
				{
					request.setAttribute("info","Update Successful");
					RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
					rd.include(request, response);
				}
				else {
					request.setAttribute("info","Update Failed");
					RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Database Error!!");
				er.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			}
		
		
		}
		else if(fun.equals("deleteprod")){
			try {
			ProductManagementService man= new ProductManagementService();
			String pmid=request.getParameter("pmid");
			System.out.println(pmid);
			request.setAttribute("flag",true);
			
			
				man.deletemodel(pmid);
				
				RequestDispatcher rd = request.getRequestDispatcher("/ProductManagementController?fun=viewallproductmodels");
				rd.include(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server error. Please Contact Your Service Person");
				er.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Database Error!!");
				er.forward(request, response);
			}catch(NoDeletionException e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "No such product model exists");
				er.forward(request, response);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("producterror.jsp");
				request.setAttribute("error", "Server Error.  Please Contact Your Service Person");
				er.forward(request, response);
			}
		
		}
		else if(fun.equals("getmenu")){
			try {
				ProductManagementService man= new ProductManagementService();
				ArrayList<Menu> menu=new ArrayList<Menu>();
				HttpSession session=request.getSession();
				
				String empid=(String) session.getAttribute("username");
				String role=(String) session.getAttribute("role");
				menu=man.getMenu(empid,role);
				System.out.println(menu);
				RequestDispatcher er = request.getRequestDispatcher("showmenu.jsp");
				request.setAttribute("menu", menu);
				er.forward(request, response);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}
	}  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
