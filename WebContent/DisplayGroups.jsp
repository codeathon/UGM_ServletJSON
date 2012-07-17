<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.sql.*,com.fh.json.*,java.util.ArrayList,java.util.ListIterator"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Launchpad Profile Manager</title>
<link rel="stylesheet" type="text/css" href="css/temp.css" />
</head>
<% 
ArrayList<FHGroupClass> FHGroupList = (ArrayList<FHGroupClass>)request.getAttribute("groupList");
%>
<body>
<script type="text/javascript">  
    function submitgroup(obj){     
        val="getGroup";
       location.href="DisplayGroups?action="+val+"&getgroupbyname="+obj;
    }  
</script>


	<div id="banner">
    	<div id="bannerContent">
        	<div id="topLogo"></div>
            <h1>Launchpad Profile Manager</h1>
            <div id="logoutForm">
            	<span>You are logged in as </span>
                <span class="loggedInUserName">
                <%= request.getSession().getAttribute("usernameSession") %></span>
                <a href="/logout">Log Out</a>
             </div>
     	</div>
 	</div>
    <div id="contentContainer">
         <div id="contentHeader" >
            <h1 >All Groups</h1>
            <a id="DisplayGroups_Add" class="normalButton absoluteTopRight" href="AddGroup.jsp">++ Add New</a>
            <a id="DisplayGroups_Back" class="normalButton absoluteTopLeft" href="DisplayGroups.jsp"> << Back </a>
         </div>
         <div class="innerContent">
            <ul class="list" id="GroupList">
	            <%
				for (ListIterator<FHGroupClass> i = FHGroupList.listIterator(); i.hasNext();) {
					FHGroupClass fhgroup = new FHGroupClass();
					fhgroup = (FHGroupClass)i.next();
				%>
				<li onclick="submitgroup('<%= fhgroup.getGroup_name()%>');">
                <div class="preview">
                	<div class="color" style="background-color:#00316D"></div>
               	</div>
                <span class="title" > <%=fhgroup.getGroup_name()%> </span>
                <p class="description" > <%= fhgroup.getDescription()%></p>
         	    <div class="hotspot"></div>
                   <a class="normalButton absoluteTopRight makeCopyButton"  href="profile/">+ Make Copy</a>
                   <a class="normalButton absoluteTopRight deleteButton" href="profile/">X</a>
                </li>
                <% } %>
            </ul>
         </div>
   
         <div id="footer">
      <div id="footerContent">
         <ul class="menu">
            <li><a href="http://framehawk.com">Home</a></li>
            <li><a href="http://framehawk.com/byod-solution/secure-mobile-enterprise-platform-overview/">Product</a></li>
            <li><a href="http://framehawk.com/company/">Company</a></li>
            <li><a href="http://framehawk.com/mobile-application-development-jobs/">Careers</a></li>
            <li><a href="http://framehawk.com/category/news/">News</a></li>
            <li><a href="http://framehawk.com/blog/">Blog</a></li>
            <li><a href="http://framehawk.com/contact-us-2/">Contact Us</a></li>
         </ul>
      </div>
   </div>
</body>
</html>
