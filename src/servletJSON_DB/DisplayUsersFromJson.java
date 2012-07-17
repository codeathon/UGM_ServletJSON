package servletJSON_DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * Servlet implementation class for Servlet: DisplayUsersFromJson
 * @author rohit
 */
 public class DisplayUsersFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayUsersFromJson() {
		super();
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String SQLstatement ="";
		request.setAttribute("action", request.getAttribute("action"));
		if(request.getParameter("action").toString().equals("getUser"))
			request.setAttribute("getuserbyid",request.getAttribute("getuserbyid") );
			else
				request.setAttribute("getuserbyid","");
		
		 if(request.getParameter("action").toString().equals("sort"))
			        SQLstatement =  "SELECT * FROM fhuser Order by last_name ASC"; 
				 else
					  SQLstatement = "SELECT * FROM fhuser";
		
	//	String SQLstatement = "SELECT * FROM fhuser";
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";
		PreparedStatement myStatement = null;
		Connection myConnection = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);
			myStatement = myConnection.prepareStatement(SQLstatement);
			ResultSet rs = myStatement.executeQuery();

			JSONMessage json_message = new JSONMessage();
			
			 if(request.getParameter("action").toString().equals("sort"))
			 {
				 System.out.println(request.getParameter("action").toString());
				 json_message.setMessageType("SortuserListResponse");
			 }
				 else
					json_message.setMessageType("userListResponse");
			 
			JSONClass jclass= new JSONClass();
			ArrayList<FHuserClass> FHUserList = new ArrayList<FHuserClass>();

			while(rs.next()) {
				FHuserClass user = new FHuserClass();
				user.setId(rs.getString("id"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole_id((Integer)rs.getInt("role_id"));
				FHUserList.add(user);
			}
			
			jclass.setFHUserClassList(FHUserList);
			jclass.setSuccess(true);
			json_message.setMessagePayload(jclass);
			
			String jsonStringUserList = new Gson().toJson(json_message);
			
			request.setAttribute("fidUserListResponse", jsonStringUserList);
			request.getRequestDispatcher("DisplayUsersFinal").forward(request,
					response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}   	  	    
}