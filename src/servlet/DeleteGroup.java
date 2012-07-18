package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.FHGroupClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;

public class DeleteGroup extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   static final long serialVersionUID = 1L;

	public DeleteGroup() {
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
		String group_id = request.getParameter("group_id");
		
		//Create deleteGroupMessage/Json
		FHGroupClass group = new FHGroupClass();
		group.setId(group_id);
		
		JSONClass jclass = new JSONClass();
		jclass.setFHGroup(group);
		
		JSONMessage json_message = new JSONMessage();
		json_message.setMessageType("deleteGroupMessage");
		json_message.setMessagePayload(jclass);
		
		//Forward deleteUserMessage/Json
		String json_string = new Gson().toJson(json_message);
		request.setAttribute("fidDeleteGroup", json_string);
		request.getRequestDispatcher("DeleteGroupFromJson").forward(request, response);
	}
}
