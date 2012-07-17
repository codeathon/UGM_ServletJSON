package servletJSON_DB;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class for Servlet: LoginFromJson
 * @author rohit
 */
 public class LoginFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginFromJson() {
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
		
		String jsonStringUserLogin = (String)request.getAttribute("jsonStringUserLogin");
		String uname="";
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(jsonStringUserLogin, type);
		JSONClass jO = result.getMessagePayload();
		
		Connection myConnection = null;
		String SQLstatement = "SELECT * FROM fhuser WHERE email =? AND password=?";
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		try {
			String user_name = jO.getFHUser().getEmail();
			String user_password = jO.getFHUser().getPassword();

			String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";

			PreparedStatement myStatement;

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();

			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);

			myStatement = myConnection.prepareStatement(SQLstatement);
			myStatement.setString(1, user_name);
			myStatement.setString(2, user_password);
			
			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidLoginResponse");
			
			JSONClass jclass = new JSONClass();
			ResultSet rs = myStatement.executeQuery();
			
			if (!(rs.next())) {
				jclass.setSuccess(false);
				jclass.setMessage("Login Failed.");
				//Set the message payload.
				json_message.setMessagePayload(jclass);

				//Get the final json string.
				String jsonLogin = new Gson().toJson(json_message);
				request.setAttribute("fidLoginResponse", jsonLogin);
				request.getRequestDispatcher("LoginFinal").forward(request,
						response);
			} else {

				uname=rs.getString("email");
				request.setAttribute("username", uname);
				
				jclass.setSuccess(true);
				jclass.setMessage("Login Successfull.");
				//Set the message payload.
				json_message.setMessagePayload(jclass);
	
				//Get the final json string.
				String jsonLogin = new Gson().toJson(json_message);
				request.setAttribute("fidLoginResponse", jsonLogin);
				request.getRequestDispatcher("LoginFinal").forward(request,
						response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}   	  	    
}