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
 * Servlet implementation class for Servlet: InsertUserFromJson
 * @author rohit
 */
public class InsertUserFromJson extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InsertUserFromJson() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userJson = (String) request.getAttribute("jsonStringUser");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(userJson, type);
		JSONClass jO = result.getMessagePayload();

		Connection myConnection = null;

		String SQLstatement_user = "INSERT INTO fhuser(first_name,last_name, email,password,profile_image,role_id) VALUES (?,?,?,?,?,1)";
		String SQLstatement_group = "INSERT INTO user_in_group(fhuser_id,fhgroup_id) VALUES (?,?)";
		String SQLstatement_newUserID = "SELECT id from fhuser WHERE email = ?";
		String SQLstatement_groupID = "SELECT id FROM fhgroup WHERE group_name = ?";

		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		try {
			String first_name = jO.getFHUser().getFirst_name();
			String last_name = jO.getFHUser().getLast_name();
			String user_password = jO.getFHUser().getPassword();
			String user_email = jO.getFHUser().getEmail();
			String user_name = jO.getFHUser().getEmail();
			String user_group = jO.getFHUser().getGroup();
			String user_profile_image = jO.getFHUser().getProfile_image();
			String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";

			PreparedStatement myStatement_user;

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();

			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);

			myStatement_user = myConnection.prepareStatement(SQLstatement_user);
			myStatement_user.setString(1, first_name);
			myStatement_user.setString(2, last_name);
			myStatement_user.setString(3, user_email);
			myStatement_user.setString(4, user_password);
			myStatement_user.setString(5, user_profile_image);

			int result_user = myStatement_user.executeUpdate();
			if (result_user == 1) {
				System.out.println("Insert Successfull.");
			} else {
				System.out.println("Insert UnSuccessfull.");
			}

			PreparedStatement myStatement_newUserID;
			myStatement_newUserID = myConnection
					.prepareStatement(SQLstatement_newUserID);
			myStatement_newUserID.setString(1, user_email);
			ResultSet result_userID = myStatement_newUserID.executeQuery();
			result_userID.next();

			PreparedStatement myStatement_groupID;
			myStatement_groupID = myConnection
					.prepareStatement(SQLstatement_groupID);
			myStatement_groupID.setString(1, user_group);
			ResultSet result_GroupID = myStatement_groupID.executeQuery();
			result_GroupID.next();

			PreparedStatement myStatement_group;
			myStatement_group = myConnection
					.prepareStatement(SQLstatement_group);
			myStatement_group.setString(1, result_userID.getString("id"));
			myStatement_group.setString(2, result_GroupID.getString("id"));
			int result_Group = myStatement_group.executeUpdate();

			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidAddUserResponse");
			
			JSONClass jclass = new JSONClass();
			
			if (result_Group == 1) {
				System.out.println("Insert Successfull.");
				jclass.setSuccess(true);
				jclass.setMessage("Insert Successfull.");
				
				//Set the message payload.
				json_message.setMessagePayload(jclass);

				//Get the final json string.
				String jsonAddUser = new Gson().toJson(json_message);
				request.setAttribute("fidAddUserResponse", jsonAddUser);
				request.getRequestDispatcher("InsertUserFinal").forward(request,
						response);
				
			} else {
				jclass.setSuccess(false);
				jclass.setMessage("Insert UnSuccessfull.");
				
				//Set the message payload.
				json_message.setMessagePayload(jclass);

				//Get the final json string.
				String jsonAddUser = new Gson().toJson(json_message);
				request.setAttribute("fidAddUserResponse", jsonAddUser);
				request.getRequestDispatcher("InsertUserFinal").forward(request,
						response);
			}
		} catch (Exception e) {
		}
	}
}