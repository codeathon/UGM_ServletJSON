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
public class ModifyGroupFromJson extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ModifyGroupFromJson() {
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
	String groupJson = (String) request.getAttribute("ModifyjsonStringGroup");
	String gid=(String) request.getAttribute("gid");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(groupJson, type);
		JSONClass jO = result.getMessagePayload();

		Connection myConnection = null;

		String SQLstatement = "update fhgroup set group_name=?, description=? where id=?";
		
		String jdbcUsername = "root";
		String jdbcPassword = "framehawk";
		try {
			String group_name = jO.getFHGroup().getGroup_name();
			String description = jO.getFHGroup().getDescription();
			String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";

			PreparedStatement myStatement_group;

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();

			myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
					jdbcPassword);

			myStatement_group = myConnection.prepareStatement(SQLstatement);
			myStatement_group.setString(1, group_name);
			myStatement_group.setString(2, description);
			myStatement_group.setString(3, gid);

			int result_group = myStatement_group.executeUpdate();
		
			JSONMessage json_message = new JSONMessage();
			json_message.setMessageType("fidUpdateUserResponse");
			
			JSONClass jclass = new JSONClass();
			
			if (result_group == 1) {
				jclass.setSuccess(true);
				jclass.setMessage("Modify group Successfull.");
				
			} else {
				jclass.setSuccess(false);
				jclass.setMessage("Modify group UnSuccessfull.");
			}
			//Set the message payload.
			json_message.setMessagePayload(jclass);

			//Get the final json string.
			String jsonModgroup = new Gson().toJson(json_message);
			request.setAttribute("fidModGroupResponse", jsonModgroup);
			request.getRequestDispatcher("ModifyGroupFinal").forward(request,
					response);
			
			
		} catch (Exception e) {
		}
	
	}
}