package servlet;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class for Servlet: InsertUserFinal
 * @author rohit
 */
 public class ModifyUserFinal extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ModifyUserFinal() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String modGroupResponse = (String)request.getAttribute("fidModUserResponse");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(modGroupResponse, type);
		JSONClass jO = result.getMessagePayload();
		
		if(jO.isSuccess()) {
			getServletContext().getRequestDispatcher("/DisplayUsers?action=display").forward(request, response);
		}
		else {
			request.getRequestDispatcher("Error.jsp").forward(request,
					response);
		}
	}   	  	    
}