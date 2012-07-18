package servlet;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DeleteGroupFinal extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   static final long serialVersionUID = 1L;
	public DeleteGroupFinal() {
		// TODO Auto-generated constructor stub
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
		String deleteGroupResponse = (String)request.getAttribute("fidDeleteGroupResponse");
		Type type = new TypeToken<JSONMessage>(){}.getType();
		JSONMessage result = new Gson().fromJson(deleteGroupResponse, type);
		JSONClass jO = result.getMessagePayload();
		
		getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);	

	}  
	
}
