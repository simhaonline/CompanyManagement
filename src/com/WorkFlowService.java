package com;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorkFlowService {

	
public ArrayList<WorkFlow> viewAllWorkFlow(String empid) throws ClassNotFoundException, SQLException
{
	WorkFlowDAO wf=new WorkFlowDAO();
	return wf.viewAllWrokFlow(empid);
}
public String reassignEmp(String oldemp,String newemp)throws ClassNotFoundException,SQLException{
	WorkFlowDAO wf=new WorkFlowDAO();
    return wf.reassignEmp(oldemp, newemp);
}
}
