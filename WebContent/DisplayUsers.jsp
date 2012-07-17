<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.sql.*,com.fh.json.*,java.util.ArrayList,java.util.ListIterator"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Launchpad Profile Manager</title>
<link rel="stylesheet" type="text/css" href="css/temp.css" />

</head>
<body>
<script type="text/javascript">  
    function submitname(obj){     
        val="getUser";
        location.href="DisplayUsers?action="+val+"&getuserbyid="+obj;
    }  
    
    function searchuser() {
        var obj=document.getElementById("SearchText").value;
    	var val="SearchUser";
    	location.href="SearchUser?action="+val+"&searchuserbyname="+obj;
    }
</script>
<div id="banner">
<div id="bannerContent">
<div id="topLogo"></div>
<h1>Launchpad Profile Manager</h1>
<div id="logoutForm"><span>You are logged in as </span> <span
	class="loggedInUserName"> <%= request.getSession().getAttribute("usernameSession") %></span> <a href="/logout">Log
Out</a></div>
</div>
</div>
<div id="contentContainer">
<div id="contentHeader">
<center>
<input type="text" name="SearchText" id="SearchText"/>
<input type = "button" name = "SearchButton" value="Search" onclick="searchuser();"/>
<a href="DisplayUsers?action=sort">++ sort</a>
</center>
	
	
 <a id="DisplayUsers_Add" class="normalButton absoluteTopRight"
	href="addUser.jsp">++ Add New</a> <a id="DisplayUsers_Back"
	class="normalButton absoluteTopLeft" href="DisplayUsers.jsp"> <<
Back </a></div>
<% 
ArrayList<FHuserClass> FHUserList = (ArrayList<FHuserClass>)request.getAttribute("userList");
%>
<div class="innerContent">
<ul class="list" id="DisplayUsers_UserList">
	<%
	for (ListIterator<FHuserClass> i = FHUserList.listIterator(); i.hasNext();) {
		FHuserClass fhuser = new FHuserClass();
		fhuser = (FHuserClass)i.next();
	%>
	<li onclick="submitname('<%=fhuser.getId()%>');">
	<div class="preview">
	<div class="color" style="background-color: #00316D"></div>
	</div>

	<span class="title"><%=fhuser.getFirst_name()%>  <%=fhuser.getLast_name()%></span>
	<p class="description"><%=fhuser.getEmail()%></p>
	
	<div class="hotspot"></div>
	<a class="normalButton absoluteTopRight makeCopyButton" href="profile/">+
	Make Copy</a> <a class="normalButton absoluteTopRight deleteButton"
		href="profile/">X</a>
		</li>
	<% 
	} 
	%>

</ul>
</div>
<div id="footer">
<div id="footerContent">
<ul class="menu">
	<li><a href="http://framehawk.com">Home</a></li>
	<li><a
		href="http://framehawk.com/byod-solution/secure-mobile-enterprise-platform-overview/">Product</a></li>
	<li><a href="http://framehawk.com/company/">Company</a></li>
	<li><a
		href="http://framehawk.com/mobile-application-development-jobs/">Careers</a></li>
	<li><a href="http://framehawk.com/category/news/">News</a></li>
	<li><a href="http://framehawk.com/blog/">Blog</a></li>
	<li><a href="http://framehawk.com/contact-us-2/">Contact Us</a></li>
</ul>
</div>
</div>
</body>
</html>
