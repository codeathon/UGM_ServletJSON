/**
 * 
 */
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

/**
 * 
 *
 */
public class DeleteUser extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public DeleteUser() {
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
		//Get id from response
		response.setContentType("text/html");
		String user_id = request.getParameter("user_id");
		
		//Create deleteUserMessage/Json
		FHuserClass user = new FHuserClass();
		user.setId(user_id);
		
		JSONClass jclass = new JSONClass();
		jclass.setFHUser(user);		
		
		JSONMessage json_message = new JSONMessage();
		json_message.setMessageType("deleteUserMessage");
		json_message.setMessagePayload(jclass);
		
		//Forward deleteUserMessage/Json
		String json_string = new Gson().toJson(json_message);
		request.setAttribute("fidDeleteUser", json_string);
		request.getRequestDispatcher("DeleteUserFromJson").forward(request, response);
	}
	
	
}
