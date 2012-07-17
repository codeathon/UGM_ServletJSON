package servlet;

import java.io.IOException;
import java.lang.reflect.Type;
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
 * Servlet implementation class for Servlet: DisplayUsersFinal
 * @author rohit
 */
 public class DisplayUsersFinal extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayUsersFinal() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setAttribute("action", request.getAttribute("action"));
		String action=request.getAttribute("action").toString();
		String uname=request.getAttribute("getuserbyid").toString();
		
		String userListResponse = (String)request.getAttribute("fidUserListResponse");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(userListResponse, type);
		JSONClass jO = result.getMessagePayload();
		
		ArrayList<FHuserClass> FHUserList = new ArrayList<FHuserClass>();
		FHUserList = (ArrayList<FHuserClass>)jO.getFHUserClassList();
		
		if(action.equals("display")|| action.equals("sort"))
		{			
			request.setAttribute("userList", FHUserList);
			getServletContext().getRequestDispatcher("/DisplayUsers.jsp").forward(request, response);
	
		}
		else if(action.equals("getUser"))
		{	
			for(int i=0;i<FHUserList.size();i++)
			{
				
				if(FHUserList.get(i).getId().equals(uname))
				{
				//	request.setAttribute("username", FHUserList.get(i).getUser_name());
					request.setAttribute("firstname", FHUserList.get(i).getFirst_name());
					request.setAttribute("lastname", FHUserList.get(i).getLast_name());
					request.setAttribute("userid", uname);
					request.setAttribute("email", FHUserList.get(i).getEmail());
					request.setAttribute("password", FHUserList.get(i).getPassword());
					request.setAttribute("profile_image", FHUserList.get(i).getProfile_image());
					request.setAttribute("role_id",FHUserList.get(i).getRole_id() );
			  }
			}
			getServletContext().getRequestDispatcher("/modifyUser.jsp").forward(request, response);
		}
		else{}
		}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
		
	}   	  	   
}