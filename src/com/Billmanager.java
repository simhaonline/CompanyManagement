package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Billmanager
 */
public class Billmanager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Billmanager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fun=request.getParameter("fun");
		BillManagementService  bms=new BillManagementService();
		 if(fun.equals("searchbill"))
			{
		
				String billid=request.getParameter("billid");
				
					Bill al=new Bill();
					
						
					try {
						
						al=bms.searchBill(billid);
						if(!al.getBillId().equals(null)){
							System.out.println("not null");
	               	request.setAttribute("billobj", al);
					RequestDispatcher rd=request.getRequestDispatcher("Viewbill.jsp");
					rd.forward(request, response);
						}
						else
						{
							
						}
					} catch (ClassNotFoundException | SQLException |NullPointerException e) {
						e.printStackTrace();
						RequestDispatcher er = request.getRequestDispatcher("Nosuchbill.jsp");
						request.setAttribute("error", "No such bill exists");
						er.forward(request, response);
					}
					
					
			
				
			}
			//System.out.println(fun);
			
			else if(fun.equals("fetchproduct")){
				ArrayList<Product> prolist=new ArrayList<Product>();
				HttpSession session=request.getSession();
				String empid=(String) session.getAttribute("username");
				
				try {
					
					prolist=bms.fetchProductsForSale(empid);
					if(prolist.size()!=0){
						System.out.println(prolist.toString());
						System.out.println("not null has products");
						request.setAttribute("prolist", prolist);
						RequestDispatcher rd=request.getRequestDispatcher("addbill.jsp");
						rd.forward(request, response);
					}
					else{
						System.out.println(" null no products");
						RequestDispatcher er = request.getRequestDispatcher("Nosuchbill.jsp");
						request.setAttribute("error", "No products for this employee");
						er.forward(request, response);

					}
				} catch (ClassNotFoundException | SQLException |NullPointerException e) {
					e.printStackTrace();
				}
				
				
				
			}
			else if(fun.equals("loadbill"))
			{
				HttpSession session=request.getSession();
				String empId=(String)session.getAttribute("username");
				ArrayList<String> list=new   ArrayList<>();
				
				try {
					list=bms.loadbill(empId);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("list", list);
				RequestDispatcher rd=request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
				
			}
			else if(fun.equals("viewall"))
			{
						
				Bill b=new Bill();

				ArrayList<Bill> b1=new ArrayList<Bill>();
				HttpSession session=request.getSession();
				String empId=(String)session.getAttribute("username");
				String role=null;
               

			try {
					b1=bms.viewAllBill(empId);
					for(Bill billo :b1){
						System.out.println("bill list values"+billo.getBillId()+" "+billo.getPaytype());
					}
					if(b1.size()!=0)
					{
					request.setAttribute("bill", b1);
					RequestDispatcher rd=request.getRequestDispatcher("ViewAllBill.jsp");
					rd.forward(request, response);
					
				} 
						
						else  {
							//System.out.println(" null no products");
							RequestDispatcher er = request.getRequestDispatcher("Nosuchbill.jsp");
							request.setAttribute("error", "No Bills available");
							er.forward(request, response);

						
						}

			}

						catch (ClassNotFoundException | SQLException |NullPointerException e) {
							e.printStackTrace();
						}
				
				

				
				
			}
			
				
	}
	
	
	
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fun=request.getParameter("fun");
		Bill bm=new Bill();
		BillManagementService  bms=new BillManagementService();
		if(fun.equals("addbill"))
		{try {
		  	HttpSession session=request.getSession();
	    	String empId=(String)session.getAttribute("username");
			String b=null;
			Date sellingDate=Date.valueOf(request.getParameter("selldate"));
			double sellingAmount=Double.parseDouble(request.getParameter("reduction"));
			System.out.println("reduction "+sellingAmount);
			String custName=request.getParameter("cname");
			long custNo=Long.parseLong(request.getParameter("cno"));
			String payType=request.getParameter("paytype");
			String custAddr=request.getParameter("address");
			String[] prodlist=request.getParameterValues("products");
			
			
				String result=bms.generateBill(prodlist,sellingDate,sellingAmount,custName,custNo,payType,custAddr,empId);
				 b=result.split(",.,")[0];
				
				double sellingprice = Double.parseDouble(result.split(",.,")[1]);
				System.out.println("fffffffff"+sellingprice);
				
				bms.setbillamount(b,sellingprice);
				
				System.out.println("amt is "+bm.getSellingAmt());
				System.out.println("bill id is "+b);
				PrintWriter out=response.getWriter();
				//out.println(pmid);
			
			request.setAttribute("billid", b);
			request.setAttribute("link", "<a href='Billmanager?billid="+b+"&fun=searchbill&submit=Submit'> View Bill</a>");
			RequestDispatcher rd = request.getRequestDispatcher("billadded.jsp");
			rd.include(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(ProductAlreadySoldException e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("Nosuchbill.jsp");
				System.out.println("already sold");
				request.setAttribute("error", "Selected products are already sold!!");
				er.forward(request, response);
				
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher er = request.getRequestDispatcher("Nosuchbill.jsp");
				request.setAttribute("error", "Some server error");
				er.forward(request, response);
			}
			
			
		
		
	
	}
	}
}

