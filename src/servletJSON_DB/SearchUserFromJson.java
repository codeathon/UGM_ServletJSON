package servletJSON_DB;

import java.io.IOException;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class for Servlet: SearchUserFromJson
 * @author rohit
 */
 public class SearchUserFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SearchUserFromJson() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String SQLstatement = "SELECT * FROM fhuser WHERE email LIKE  CONCAT('%', ? , '%') OR first_name LIKE  CONCAT('%', ? , '%') OR last_name LIKE  CONCAT('%', ? , '%')";
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";
		PreparedStatement myStatement = null;
		Connection myConnection = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			String userNameJSON = (String)request.getAttribute("jsonStringSearchUser");
			System.out.print(userNameJSON);
			Type type = new TypeToken<JSONMessage>() {
			}.getType();
			JSONMessage result = new Gson().fromJson(userNameJSON, type);
			JSONClass jO = result.getMessagePayload();
			
			String searchName = (String)jO.getFHUser().getUser_name();
			Class.forName(driver).newInstance();
			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);
			myStatement = myConnection.prepareStatement(SQLstatement);
			myStatement.setString(1, "%"+searchName+"%");
			myStatement.setString(2, "%"+searchName+"%");
			myStatement.setString(3, "%"+searchName+"%");

			ResultSet rs = myStatement.executeQuery();

			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidSearchUserResponse");
			JSONClass jclass= new JSONClass();
			ArrayList<FHuserClass> FHUserSearchList = new ArrayList<FHuserClass>();

			while(rs.next()) {
				FHuserClass user = new FHuserClass();
				user.setId(rs.getString("id"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole_id(1);
				FHUserSearchList.add(user);
			}
			
			jclass.setFHUserClassList(FHUserSearchList);
			jclass.setSuccess(true);
			json_message.setMessagePayload(jclass);
			
			String jsonStringUserSearchList = new Gson().toJson(json_message);
			System.out.print(jsonStringUserSearchList);
			request.setAttribute("fidUserSearchResponse", jsonStringUserSearchList);
			request.getRequestDispatcher("SearchUserFinal").forward(request,
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