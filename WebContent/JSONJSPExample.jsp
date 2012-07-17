<%@ page language="java" import="net.sf.json.JSONArray" %>
 
<%
 JSONArray arrayObj=new JSONArray();
 arrayObj.add("MCA");
 arrayObj.add("Amit Kumar");
 arrayObj.add("19-12-1986");
 arrayObj.add(24);
 arrayObj.add("Scored");
 arrayObj.add(new Double(66.67));
%>
<h2>Array Object is</h2> <%=arrayObj%>
<br><hr>
<%  for(int i=0;i<arrayObj.size();i++){  %>
  <%=arrayObj.getString(i)%>
<%
  }
%>