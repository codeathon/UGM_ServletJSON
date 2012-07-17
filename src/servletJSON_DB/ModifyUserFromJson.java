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
 */
public class ModifyUserFromJson extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ModifyUserFromJson() {
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
	String userJson = (String) request.getAttribute("ModifyjsonStringUser");
	String userid=(String) request.getAttribute("userid");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(userJson, type);
		JSONClass jO = result.getMessagePayload();

		Connection myConnection = null;

		String SQLstatement1 = "update fhuser set password=?, first_name=?, last_name=?, email=?  where id=?";
		String SQLstatement2 = "update user_in_group set fhgroup_id=?  where fhuser_id=?";
		String SQLstatement_groupID = "SELECT id FROM fhgroup WHERE group_name = ?";

		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		try {
			String first_name = jO.getFHUser().getFirst_name();
			String last_name = jO.getFHUser().getLast_name();
			String user_password = jO.getFHUser().getPassword();
			String user_email = jO.getFHUser().getEmail();
			String user_group = jO.getFHUser().getGroup();
			String user_profile_image = jO.getFHUser().getProfile_image();
			String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";

			PreparedStatement myStatement_user;

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();

			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);

			myStatement_user = myConnection.prepareStatement(SQLstatement1);
			myStatement_user.setString(1, user_password);
			myStatement_user.setString(2, first_name);
			myStatement_user.setString(3, last_name);
			myStatement_user.setString(4, user_email);
			myStatement_user.setString(5, userid);

			int result_user = myStatement_user.executeUpdate();
			if (result_user == 1) {
				System.out.println("Modify Successfull.");
			} else {
				System.out.println("Modify UnSuccessfull.");
			}

			PreparedStatement myStatement_groupID;
			myStatement_groupID = myConnection.prepareStatement(SQLstatement_groupID);
			myStatement_groupID.setString(1, user_group);
			ResultSet result_GroupID = myStatement_groupID.executeQuery();
			result_GroupID.next();
			String groupID= result_GroupID.getString("id");
			
			PreparedStatement myStatement_group;
			myStatement_group = myConnection.prepareStatement(SQLstatement2);
			myStatement_group.setString(1,groupID);
			myStatement_group.setString(2, userid);
			int result_Group = myStatement_group.executeUpdate();
			
			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidUpdateUserResponse");
			
			JSONClass jclass = new JSONClass();
			
			if (result_Group == 1) {
				jclass.setSuccess(true);
				jclass.setMessage("Modify User Successfull.");
				
			} else {
				jclass.setSuccess(false);
				jclass.setMessage("Modify User UnSuccessfull.");
			}
			//Set the message payload.
			json_message.setMessagePayload(jclass);

			//Get the final json string.
			String jsonModUser = new Gson().toJson(json_message);
			request.setAttribute("fidModUserResponse", jsonModUser);
			request.getRequestDispatcher("ModifyUserFinal").forward(request,
					response);
			
			
		} catch (Exception e) {
		}
	
	}
}