<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.sql.*"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>USM</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/placeholder.js" type="text/javascript"></script>
</head>
<body>
<%
	String SQLstatement = "SELECT * FROM fhgroup";
	String SQLStatementRole = "SELECT * FROM role";
	
	String jdbcUsername = "root";
	String jdbcPassword = "framehawk";
	String jdbcURL = "jdbc:mysql://172.16.1.11:3306/launchpad";
	PreparedStatement myStatement;
	PreparedStatement myStatementRole;

	Connection myConnection = null;

	String driver = "com.mysql.jdbc.Driver";
	Class.forName(driver).newInstance();

	myConnection = DriverManager.getConnection(jdbcURL, jdbcUsername,
			jdbcPassword);

	myStatement = myConnection.prepareStatement(SQLstatement);
	myStatementRole = myConnection.prepareStatement(SQLStatementRole);
	
	ResultSet rs = myStatement.executeQuery();
	ResultSet rsRole = myStatementRole.executeQuery();

%>
	<%@ include file="jsp/header.jsp"%>
	<div id="body_container">
		<div id="body_border">
			<div id="body_content">
				<br /> <span id="content_title"> Modify <font color="#6699CC">User:</font>
					<hr color="#6699CC" width="100%" />
				</span> <br />
				
<div id="addUserInnerContent">
	<div id="add_user_form_container">
  <form action="ModifyUser" method="post">	
  <div class="form_left">
  
     <input type="hidden" name=id value="<%=(String)request.getAttribute("userid")%>"/>
     <input type="text" id="firstname" name="firstname" value="<%=(String)request.getAttribute("firstname")%>" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>
     <input type="text" id="lastname" name="lastname" value="<%=(String)request.getAttribute("lastname")%>" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>
    <input type="text" id="email" name="email" value="<%=(String)request.getAttribute("email")%>" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>	 
	 <input type="text" id="Fakepassword" name="Fakepassword"  style="color:#999999; margin-bottom: 3%; text-align: left;" value="<%=(String)request.getAttribute("password")%>" onfocus="pwdFocus()" />
	 <input type="password" id="Password" name="password" style="display:none; margin-bottom: 3%; text-align: left;"  onblur="pwdBlur()"/>
	 <input type="text" id="Retype_Fakepassword" name="Retype_Fakepassword"  style="color:#999999; margin-bottom: 3%; text-align: left;" value="  Retype Password" onfocus="pwdFocus_adduser_retype()" />
	 <input type="password" id="Retype_Password" name="Retype_Password" style="display:none; margin-bottom: 3%; text-align: left;" onblur="pwdBlur_adduser_retype()"/><br/>
	 <select id="Privilege" name="Privilege" style="color:#999999; margin-bottom: 3%; text-align: left;">
					<%
						while (rsRole.next()) {
					%>
					<option><%=rsRole.getString("role_name")%></option>
					<%
						}
					%>
	 </select>
	 <select id="Groups" name="group" style="color:#999999; margin-bottom: 3%;">
					<%
						while (rs.next()) {
					%>
					<option><%=rs.getString("group_name")%></option>
					<%
						}
					%>
	 </select>	 
  	</div>    
  	<div class="form_right">
     	<img src="images/AVATAR.png" alt="avatar" id="avatar"/>  <br/>   	
     	<input type="button" value="Upload" class="add_user_img_upload" id="add_user_img_upload"/> <br/> <br/>
     	
 	 </div> 	   	 
 	 <input type="submit" value="Submit" class="modify_user_submit" id="modify_user_submit"/>
  	 <input type="button" value="Back" class="add_user_back" id="add_user_back"/>
  </form>
     
  </div>
</div>
		</div>
			</div>
<%@ include file="jsp/footer.jsp"%>
</body>
</html>