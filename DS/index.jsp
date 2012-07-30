<%@ page import="com.web.model.*, com.web.view.*" %>

<html>
<body>


<%
	String uname = null;
	if (session.getAttribute("username") != null){
		response.sendRedirect("download.jsp");
	}
%>

<h2>Welcome!</h2>
<FORM METHOD=POST ACTION="Login">

Username: <INPUT TYPE=TEXT NAME=username SIZE=20><BR><BR>
Password: <INPUT TYPE=PASSWORD NAME=password SIZE=20><BR><BR>

<P><INPUT TYPE=SUBMIT VALUE="Login">

</FORM>

<%
	
	if (session.getAttribute("error") != null){
		String msg = (String) session.getAttribute("error");
%>
		<i> Message for you: <%= msg %></i>
<%
		session.invalidate();
	}
%>

<P><a href="register.jsp">Register</a>


</body>
</html>