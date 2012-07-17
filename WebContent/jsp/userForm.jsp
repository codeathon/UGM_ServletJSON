<%@ page import="java.sql.*"%>
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
<div id="addUserInnerContent">
	<div id="add_user_form_container">
  <form action="InsertUser" method="post">	
  <div class="form_left">
     <input type="text" id="firstname" name="firstname" value="First Name" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>
     <input type="text" id="lastname" name="lastname" value="Last Name" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>
     <input type="text" id="Fakepassword" name="Fakepassword"  style="color:#999999; margin-bottom: 3%; text-align: left;" value="  Password" onfocus="pwdFocus()" />
	 <input type="password" id="Password" name="password" style="display:none; margin-bottom: 3%; text-align: left;"  onblur="pwdBlur()"/>
	 <input type="text" id="Retype_Fakepassword" name="Retype_Fakepassword"  style="color:#999999; margin-bottom: 3%; text-align: left;" value="  Retype Password" onfocus="pwdFocus_adduser_retype()" />
	 <input type="password" id="Retype_Password" name="Retype_Password" style="display:none; margin-bottom: 3%; text-align: left;" onblur="pwdBlur_adduser_retype()"/><br/>
	 <input type="text" id="Email" name="email" value="  Email" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>	 
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
 	 <input type="submit" value="Submit" class="add_user_submit" id="add_user_submit"/>
  	 <input type="button" value="Back" class="add_user_back" id="add_user_back"/>
  </form>
     
  </div>
</div>