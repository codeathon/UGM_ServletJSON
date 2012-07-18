/**
 * 
 */
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

import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 *
 */
public class DeleteUserFromJson extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public DeleteUserFromJson() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.doPost(request, response);
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		//Get user id from Json
		String json_string = (String) request.getAttribute("fidDeleteUser");
		Type type = new TypeToken<JSONMessage>(){}.getType();
		JSONMessage result = new Gson().fromJson(json_string, type);
		JSONClass jO = result.getMessagePayload();
		FHuserClass user = jO.getFHUser();
		String id = new Integer(user.getId()).toString();
		
		//Remove user from user and group tables
		Connection myConnection = null;
		String SQLstatement_delete_user = "DELETE FROM fhuser WHERE id = ?";
		String SQLstatement_delete_user_group = "DELETE FROM user_in_group WHERE fhuser_id = ?";
		String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		String driver = "com.mysql.jdbc.Driver";

		try{
			//Establish connection
			Class.forName(driver).newInstance();
			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			//Send delete user message
			PreparedStatement myStatement_delete_user;
			myStatement_delete_user = myConnection.prepareStatement(SQLstatement_delete_user);
			myStatement_delete_user.setString(1,new Integer(id).toString());
			int result1 = myStatement_delete_user.executeUpdate();
			this.resultToConsole(result1);
			
			//Send delete user group message
			PreparedStatement myStatement_delete_usergroup;
			myStatement_delete_usergroup = myConnection.prepareStatement(SQLstatement_delete_user_group);
			myStatement_delete_usergroup.setString(1,new Integer(id).toString());
			int result2 = myStatement_delete_usergroup.executeUpdate();
			this.resultToConsole(result2);
			
			//Create response Json
			JSONClass jclass = new JSONClass();
			boolean success = (result1 == 1) && (result2 == 1);
			jclass.setSuccess(success);
			if(success)
				jclass.setMessage("Delete Successful.");
			else 
				jclass.setMessage("Delete Unsuccessful.");
			
			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidDeleteUserResponse");
			json_message.setMessagePayload(jclass);
			String jsonDeleteUser = new Gson().toJson(json_message);
			
			//Forward response Json
			request.setAttribute("fidDeleteUserResponse",jsonDeleteUser);
			request.getRequestDispatcher("DeleteUserFinal").forward(request, response);
			
			}catch(Exception e){
				System.out.println(e);
			}
				
	}
	
	private void resultToConsole(int result){
		if (result == 1) {
			System.out.println("Delete Successfull.");
		} else {
			System.out.println("Delete UnSuccessfull.");
		}
	}
	
	
}
