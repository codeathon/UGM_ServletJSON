<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UGM</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/placeholder.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<div id="body_container">
		<div id="body_border">
			<div id="body_content">
				<br /> <span id="content_title"> My <font color="#6699CC">Dashboard:</font>
				<span style="font-size: 18px; margin-left: 48%;" > You are logged in as <font color="#6699CC"><%= request.getSession().getAttribute("usernameSession") %></font></span>
					<hr color="#6699CC" width="100%" />										
				</span> <br />
				<%@ include file="jsp/dasboard_contest.jsp"%>
				</div>
			</div>
		</div>
	<%@ include file="jsp/footer.jsp"%>
</body>
</html>