<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<%@ include file="jsp/header.jsp"%>
	<div id="body_container">
		<div id="body_border">
			<div id="body_content">
				<br /> <span id="content_title"> Modify <font color="#6699CC">Group:</font>
					<hr color="#6699CC" width="100%" />
				</span> <br />
				
				<div id="modGroupInnerContent">
	<div id="mod_group_form_container">
 		<form action="ModifyGroup" method="post">
 		
 		    <input type="hidden" id="gid" name="gid" value="<%=(String)request.getAttribute("gid")%>" style="color:#999999; margin-bottom: 3%; text-align: left;"/> <br/>
            <input type="text" id="groupname" name="groupname" value="<%=(String)request.getAttribute("groupname")%>" style="color:#999999; margin-bottom: 3%; text-align: left;"> <br/>
     		<textarea name="description" id="description" class="description" style="color:#999999; margin-bottom: 3%; text-align: left; height:120px;"> 
     		<%=(String)request.getAttribute("description")%></textarea><br/>
     		<input type="submit" value="Submit" class="mod_group_submit" id="mod_group_submit"/>
  	 		<input type="button" value="Back" class="mod_group_back" id="mod_group_back"/>
 		</form>     
  	</div>
</div>
</div>
			</div>
		</div>
	<%@ include file="jsp/footer.jsp"%>
</body>
</html>