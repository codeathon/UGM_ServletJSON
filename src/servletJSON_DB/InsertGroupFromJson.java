package servletJSON_DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class for Servlet: InsertGroupFromJson
 * @author rohit
 */
 public class InsertGroupFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InsertGroupFromJson() {
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
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Connection myConnection = null;
		String SQLstatement = "INSERT INTO fhgroup(group_name,description) VALUES (?,?)";
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		try {
			String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";

			PreparedStatement myStatement;

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();

			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);

			myStatement = myConnection.prepareStatement(SQLstatement);
			
			String userJson = (String) request.getAttribute("jsonStringGroup");
			Type type = new TypeToken<JSONMessage>() {
			}.getType();
			JSONMessage result = new Gson().fromJson(userJson, type);
			JSONClass jO = result.getMessagePayload();
			
			
			myStatement.setString(1, jO.getFHGroup().getGroup_name());
			myStatement.setString(2, jO.getFHGroup().getDescription());

			int result1 = myStatement.executeUpdate();
			
			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidAddGroupResponse");
			
			JSONClass jclass = new JSONClass();
			
			if (result1 == 1) {
				pw.println("Insert Successfull.");
				jclass.setSuccess(true);
				jclass.setMessage("Insert Successfull.");
			} else {
				pw.println("Insert UnSuccessfull.");
				jclass.setSuccess(false);
				jclass.setMessage("Insert UnSuccessfull.");				
			}
			//Set the message payload.
			json_message.setMessagePayload(jclass);

			//Get the final json string.
			String jsonAddGroup = new Gson().toJson(json_message);
			request.setAttribute("fidAddGroupResponse", jsonAddGroup);
			request.getRequestDispatcher("InsertGroupFinal").forward(request,
					response);
		} catch (Exception e) {
			pw.println(e);
		}
	}   	  	    
}