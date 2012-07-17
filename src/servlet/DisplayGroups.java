package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * Servlet implementation class for Servlet: DisplayGroups
 * @author rohit
 *
 */
 public class DisplayGroups extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayGroups() {
		super();
	}   	
 	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    	request.setAttribute("action",request.getParameter("action") );
		   if(request.getParameter("action").toString().equals("getGroup"))
			request.setAttribute("getgroupbyname",request.getParameter("getgroupbyname") );
			else
				request.setAttribute("getgroupbyname","");
		   
		JSONMessage json_message = new JSONMessage();
		json_message.setMessageType("groupListRequest");
		JSONClass jclass= new JSONClass();

		jclass.setSessionID();
		
		// Set the message payload.
		json_message.setMessagePayload(jclass);
		
		// Get the final json string.
		String jsonStringGroupList = new Gson().toJson(json_message);
		request.setAttribute("jsonStringGroupList", jsonStringGroupList);
		request.getRequestDispatcher("DisplayGroupsFromJson").forward(request,
                response);
	}   	  	    
	
}