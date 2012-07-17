package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * Servlet implementation class for Servlet: DisplayUsers
 * 
 */
public class DisplayUsers extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayUsers() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("action",request.getParameter("action") );
	   if(request.getParameter("action").toString().equals("getUser"))
		request.setAttribute("getuserbyid",request.getParameter("getuserbyid") );
		else
			request.setAttribute("getuserbyid","");
		
		JSONMessage json_message = new JSONMessage();
		
		 if(request.getParameter("action").toString().equals("sort"))
		json_message.setMessageType("SortuserListRequest");
		 
		 else
			 json_message.setMessageType("userListRequest");
		 
		JSONClass jclass= new JSONClass();

		jclass.setSessionID();
		// Set the message payload.
		json_message.setMessagePayload(jclass);
		
		// Get the final json string.
		String jsonStringUserList = new Gson().toJson(json_message);
		request.setAttribute("jsonStringUserList", jsonStringUserList);
		request.getRequestDispatcher("DisplayUsersFromJson").forward(request,
                response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		this.doGet(request, response);
			
	}
}