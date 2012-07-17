package servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.json.FHGroupClass;
import com.fh.json.FHuserClass;
import com.fh.json.JSONClass;
import com.fh.json.JSONMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class for Servlet: DisplayGroupsFinal
 *
 */
 public class DisplayGroupsFinal extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DisplayGroupsFinal() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getAttribute("action").toString();
		String gname=request.getAttribute("getgroupbyname").toString();
		
		String userListResponse = (String)request.getAttribute("fidGroupListResponse");
		Type type = new TypeToken<JSONMessage>() {
		}.getType();
		JSONMessage result = new Gson().fromJson(userListResponse, type);
		JSONClass jO = result.getMessagePayload();
		
		ArrayList<FHGroupClass> FHGroupList = new ArrayList<FHGroupClass>();
		FHGroupList = (ArrayList<FHGroupClass>)jO.getFHGroupClassList();
		
		if(action.equals("display"))
		{		
		request.setAttribute("groupList", FHGroupList);
		getServletContext().getRequestDispatcher("/DisplayGroups.jsp").forward(request, response);
		}
		else if(action.equals("getGroup"))
		{	
			for(int i=0;i<FHGroupList.size();i++)
			{	
				if(FHGroupList.get(i).getGroup_name().equals(gname))
				{
					 request.setAttribute("gid", FHGroupList.get(i).getId());
					request.setAttribute("groupname", gname);
					request.setAttribute("description", FHGroupList.get(i).getDescription());
					
			  }
			}
			getServletContext().getRequestDispatcher("/modifyGroup.jsp").forward(request, response);
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
 
 
 
 
 
	