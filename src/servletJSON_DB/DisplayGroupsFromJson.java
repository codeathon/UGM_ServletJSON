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

import com.fh.json.FHGroupClass;
import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * Servlet implementation class for Servlet: DisplayGroupsFromJson
 *
 */
 public class DisplayGroupsFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayGroupsFromJson() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String SQLstatement = "SELECT * FROM fhgroup";
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
			json_message.setMessageType("groupListResponse");
			JSONClass jclass= new JSONClass();
			
			ArrayList<FHGroupClass> FHGroupList = new ArrayList<FHGroupClass>();

			while(rs.next()) {
				FHGroupClass group = new FHGroupClass();
				group.setId(rs.getString("id"));
				group.setGroup_name(rs.getString("group_name"));
				group.setDescription(rs.getString("description"));
				FHGroupList.add(group);
			}
			jclass.setFHGroupClassList(FHGroupList);
			jclass.setSuccess(true);
			json_message.setMessagePayload(jclass);
			
			String jsonStringGroupList = new Gson().toJson(json_message);
			
			request.setAttribute("action", request.getAttribute("action"));
			if(request.getParameter("action").toString().equals("getGroup"))
				request.setAttribute("getgroupbyname",request.getAttribute("getgroupbyname") );
				else
					request.setAttribute("getgroupbyname","");
			
			
			request.setAttribute("fidGroupListResponse", jsonStringGroupList);
			request.getRequestDispatcher("DisplayGroupsFinal").forward(request,
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