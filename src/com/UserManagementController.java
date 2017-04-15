package com;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class UserManagementController
 */
public class UserManagementController extends HttpServlet {
	
	UserManagementDAO umdao=new UserManagementDAO();
	UserManagementService umserv=new UserManagementService();
	Employee emp=new Employee();

	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementController() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fun=request.getParameter("fun");
    	PrintWriter out=response.getWriter();
    
        ArrayList <String> empList=new ArrayList<>();
        
        if(fun.equals("deleteemp")){
          	HttpSession session=request.getSession();
        	String empId=(String)session.getAttribute("username");
        	String role=null;
        	
        	String oldemp=request.getParameter("oldemp");
        	String newemp=request.getParameter("newemp");
        	
        	try {
        	
				 role=umserv.deleteemp(empId, oldemp, newemp);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("role"+role);
        	if(role.equalsIgnoreCase("Admin"))
        	{
	            out.print("<div align=center><font color=red size=4px>Employee delete succesfully!</font></div>");  

    			RequestDispatcher rd = request.getRequestDispatcher("deleteemp.jsp");
    			rd.forward(request, response);
    			
        	}
        	if(role.equalsIgnoreCase("Manager"))
        	{
	            out.print("<div align=center><font color=red size=4px>Employee delete succesfully and Products assigned to new Employee </font></div>");  

    			RequestDispatcher rd = request.getRequestDispatcher("deleteemp.jsp");
    			rd.forward(request, response);
    			
        	}
        	
        	
        }   
        else if(fun.equals("logout"))
		{
	         
	         HttpSession session=request.getSession();  
	            session.invalidate(); 
	            request.setAttribute("msg", "Successfully Logged Out");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
		}
    else if(fun.equals("deletemgr")){
        	
    	HttpSession session=request.getSession();
    	String empId=(String)session.getAttribute("username");
 
        	try {
        	
				empList=umserv.veiwAll(empId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
      if(empList.size()>0)
      {
        		
        	request.setAttribute("empList", empList);
			RequestDispatcher rd = request.getRequestDispatcher("deletemgr.jsp");
			rd.include(request, response);
      }
      else{
    		
        	request.setAttribute("error", "Not Enough Employee to delete ");
			RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
			rd.include(request, response);
      }
        	
        
        }

        
        else if(fun.equals("deleterep")){
        	
        	String empId=request.getParameter("empid");
 
        	try {
        	
				empList=umserv.veiwAll(empId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        if(empList.size()>1)
        {
        		System.out.println("list more than 2 ");
        	request.setAttribute("empList", empList);
			RequestDispatcher rd = request.getRequestDispatcher("deleterep.jsp");
			rd.include(request, response);
        }
    	
        if(empList.size()==1)
        {
        		System.out.println("1 employee");
        	request.setAttribute("error", "Not Enough Employee to delete ");
			RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
			rd.include(request, response);
        }
     
        }

        else if(fun.equals("viewall")){
        	HttpSession session=request.getSession();
        	String empId=(String)session.getAttribute("username");
 
        	try {
        	
				empList=umserv.veiwAll(empId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        
        	if(empList.size()>0)
        	{
        	request.setAttribute("empList", empList);
			RequestDispatcher rd = request.getRequestDispatcher("viewall.jsp");
			rd.include(request, response);
        	}
        	else{
        		request.setAttribute("error","No Sales Representative in Store ");
				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
				rd.forward(request, response);
        		
        	}
        	
        
        }
        else if(fun.equals("loademp")){
			
    			String empId=request.getParameter("empId");
    			HttpSession session=request.getSession();
            	String mgrId=(String)session.getAttribute("username");
    		Store st=new Store();
    			String role=null;
    			try {
    				emp=umserv.empDetails(empId);
    				
    				role=umserv.getRole(mgrId);
    				
    				if(role.equalsIgnoreCase("manager")){
    				st=umserv.storedetils(mgrId);
    				}
    				else 
    				{
    					st=umserv.storedetils(empId);
    				}
    				
    			} catch (SQLException | ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			if(emp.getGender()!=null)
    			{
    				
    			
    			request.setAttribute("emp", emp);
    			request.setAttribute("st", st);
    			RequestDispatcher rd = request.getRequestDispatcher("empsearch.jsp");
    			rd.include(request, response);
    			}
    			else{
    	
    	        	request.setAttribute("error", "Employee Profile Deleted ");
    				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
    				rd.include(request, response);
    			}
    			
    		}
        
    	
	



	
		
        else if(fun.equals("update")){
			
			String empId=request.getParameter("empid");
		
			
			try {
				emp=umserv.empDetails(empId);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(emp.getGender()!=null)
			{
			request.setAttribute("emp", emp);
			RequestDispatcher rd = request.getRequestDispatcher("updateemp.jsp");
			rd.include(request, response);
			}
			else
			{
				request.setAttribute("error","Contact Admin");
				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
				rd.forward(request, response);
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fun=request.getParameter("fun");
		PrintWriter out=response.getWriter();
		if(fun.equals("updatedemp")){
			
	
		String empId=(String)request.getParameter("empId");
		
		Employee emp=new Employee();
		emp.setEmployeeId(request.getParameter("empId"));
		emp.setFirstName(request.getParameter("fname"));
		emp.setLastName(request.getParameter("lname"));
		emp.setGender(request.getParameter("gender"));
		emp.setDob(Date.valueOf(request.getParameter("dob")));
		emp.setAddress(request.getParameter("address"));
		emp.setRole(request.getParameter("role"));
		emp.setMobNo(Long.parseLong(request.getParameter("mobno")));
		emp.setPassword(request.getParameter("pwd"));
		
		boolean flag=false;
		try {
			flag = umserv.updateEmp(emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(flag)
		{
			request.setAttribute("flag",flag);
			RequestDispatcher rd = request.getRequestDispatcher("empupdated.jsp");
			rd.include(request, response);
		}
		else {
			request.setAttribute("flag",flag);
			RequestDispatcher rd = request.getRequestDispatcher("empupdated.jsp");
			rd.include(request, response);
		}
		
		}
		
		
		
		
		else if(fun.equals("addrep")){
			String empId=null;
			HttpSession session=request.getSession();
			String mId=(String)session.getAttribute("username");
			
			
		
			
			Employee emp=new Employee();
			emp.setFirstName(request.getParameter("fname"));
			emp.setLastName(request.getParameter("lname"));
			emp.setGender(request.getParameter("gender"));
			emp.setDob(Date.valueOf(request.getParameter("dob")));
			emp.setAddress(request.getParameter("address"));
			emp.setRole(request.getParameter("role"));
			emp.setMobNo(Long.parseLong(request.getParameter("mobno")));
			
		
			 try {
				empId=umserv.addrep(emp, mId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			if(empId!=null)
			{
			request.setAttribute("empId", empId);
			RequestDispatcher rd = request.getRequestDispatcher("repadded.jsp");
			rd.include(request, response);
			}
			else {
				request.setAttribute("error","User already added");
				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
				rd.forward(request, response);
			}
		
			
		}
	
		
		else if(fun.equals("addemployee")){
			String empId=null;
			Employee emp=new Employee();
			emp.setFirstName(request.getParameter("fname"));
			emp.setLastName(request.getParameter("lname"));
			emp.setGender(request.getParameter("gender"));
			emp.setDob(Date.valueOf(request.getParameter("dob")));
			emp.setAddress(request.getParameter("address"));
			emp.setRole(request.getParameter("role"));
			emp.setMobNo(Long.parseLong(request.getParameter("mobno")));
		
			Store st=new Store();
			st.setStorename((String)request.getParameter("store"));
			st.setRegionName((String)request.getParameter("region"));
			
			if(emp.getRole().equalsIgnoreCase("manager"))
			{	
				boolean flag1=false;
				
				
				try {
					flag1=umdao.checkmgr(st.getStorename());
					
				} catch (SQLException e1) {
					
					request.setAttribute("error", "Manager Already available for the store ");
					
					RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
					rd.include(request, response);
				}
			
		if(flag1){
			
			
		
			
		
			boolean flag=false;
			
			try {
			 empId=umdao.adduser(emp);
			 
			 st.setMgrId(empId);
			 flag=umserv.addmgrtostore(st);
			 
		
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(flag){
				
			
			request.setAttribute("empId", empId);
			RequestDispatcher rd = request.getRequestDispatcher("empadded.jsp");
			rd.include(request, response);
			}
			else
			{
				request.setAttribute("error", "Manager Already assigned to store ");
				
				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
				rd.include(request, response);
			}
			}
		else{
			request.setAttribute("error", "Manager Already assigned to store ");
			
			RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
			rd.include(request, response);
		}
			
			}
			
			
			if(emp.getRole().equalsIgnoreCase("admin"))
			{
			
		
			
	
			emp.setFirstName(request.getParameter("fname"));
			emp.setLastName(request.getParameter("lname"));
			emp.setGender(request.getParameter("gender"));
			emp.setDob(Date.valueOf(request.getParameter("dob")));
			emp.setAddress(request.getParameter("address"));
			emp.setRole(request.getParameter("role"));
			emp.setMobNo(Long.parseLong(request.getParameter("mobno")));
			
		
			
		
			
			
			try {
				
			 empId=umdao.adduser(emp);
			
			} catch (ClassNotFoundException e) {
				
				
			} catch (SQLException e) {
			
			}
			
			if(empId!=null)
			{
			request.setAttribute("empId", empId);
			RequestDispatcher rd = request.getRequestDispatcher("empadded.jsp");
			rd.include(request, response);
			}
			else {
				request.setAttribute("error","User already added");
				RequestDispatcher rd = request.getRequestDispatcher("producterror.jsp");
				rd.forward(request, response);
			}
			
			
			}
			
		}
		
		else if(fun.equals("logout"))
		{
	         
	         HttpSession session=request.getSession();  
	            session.invalidate(); 
	            request.setAttribute("msg", "Successfully Logged Out");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
		}
		
		
		else if (fun.equals("login")){
			
	
	Employee emp=new Employee();
	String empId=request.getParameter("uname");
		
		String pwd=request.getParameter("pwd");
		
		try {
			emp=umserv.logincheck(empId, pwd);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(emp.getRole()!=null)
		{
		
			HttpSession session=request.getSession();
			session.setAttribute("username",empId);
		    session.setAttribute("role",emp.getRole());
		    session.setAttribute("name",emp.getFirstName());
		    
			request.setAttribute("empId", empId);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
		}
			
		else{
			request.setAttribute("msg", "Sorry UserName or Password Error");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		//out.println("<div align=center><font color=red size=4px><br><br><br><br><br><br><br><br><br><br><br><br><br><br>sorry UserName or Password Error</font></div>");
			  
		}
			
		}
		
	}

}
