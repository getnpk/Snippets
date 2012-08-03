<%@ page import="com.web.model.*, com.web.view.*" %>

<html>
<body>

<%
	if (session.getAttribute("username") != null){
		response.sendRedirect("download.jsp");
	}
%>

<h2>Welcome!</h2>
<FORM METHOD=POST ACTION="Login">

<table cellspacing="10">
<tr>
<td>Username: </td> <td><INPUT TYPE=TEXT NAME=username SIZE=20></td>
</tr>

<tr>
<td>Password: </td><td><INPUT TYPE=PASSWORD NAME=password SIZE=20></td>
</tr>

<tr></tr>

<tr>
<td><INPUT TYPE=SUBMIT VALUE="Login"></td>
</tr>

</table>

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