package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class Login extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Login() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JSONMessage json_message = new JSONMessage();
		FHuserClass user = new FHuserClass();
		JSONClass jclass= new JSONClass();
		
		//Add the user details to the 
		user.setEmail(username);
		user.setPassword(password);		
		jclass.setFHUser(user);
		
		//Set the message payload.
		json_message.setMessagePayload(jclass);
		
		//Get the final json string.
		String jsonStringUserLogin = new Gson().toJson(json_message);
		request.setAttribute("jsonStringUserLogin", jsonStringUserLogin);
		request.getRequestDispatcher("LoginFromJson").forward(request,
                response);
	}   	  	    
}