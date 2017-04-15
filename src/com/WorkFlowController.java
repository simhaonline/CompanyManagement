package com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WorkFlowController
 */
public class WorkFlowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkFlowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fun=request.getParameter("fun");
		WorkFlowService wfs=new WorkFlowService();
		
		 if(fun.equals("viewall"))
{
			
	WorkFlow w=new WorkFlow();

	ArrayList<WorkFlow> w1=new ArrayList<WorkFlow>();
	HttpSession session=request.getSession();
	String empId=(String)session.getAttribute("username");
	String role=null;


try {
		w1=wfs.viewAllWorkFlow(empId);
		if(w1.size()!=0)
		{
		request.setAttribute("wrk", w1);
		RequestDispatcher rd=request.getRequestDispatcher("ViewAllwork.jsp");
		rd.forward(request, response);
		
	} 
			
			else  {
				//System.out.println(" null no products");
				RequestDispatcher er = request.getRequestDispatcher("workerror.jsp");
				request.setAttribute("error", "No products avalilable in this outlet");
				er.forward(request, response);

			
			}

}

			catch (ClassNotFoundException | SQLException |NullPointerException e) {
				e.printStackTrace();
			}
	
	

	
	
}
		
	}

}
