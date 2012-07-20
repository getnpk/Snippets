<html>
<body>
<h2>Register</h2>

<FORM METHOD=POST ACTION="Register">

Username: <INPUT TYPE=TEXT NAME=username SIZE=20><BR>
Password: <INPUT TYPE=PASSWORD NAME=password SIZE=20><BR>
Firstname: <INPUT TYPE=TEXT NAME=firstname SIZE=20><BR>
Lastname: <INPUT TYPE=PASSWORD NAME=lastname SIZE=20><BR>

<P><INPUT TYPE=SUBMIT VALUE="Register">

</FORM>

<%
	if (request.getAttribute("register_error") != null){
		String msg = (String) request.getAttribute("register_error");
%>
	<i> You have a problem: <%= msg %></i>
<%
	}
%>

<P><a href="index.jsp">Login</a>


</body>
</html>