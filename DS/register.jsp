<html>
<body>
<h2>Register</h2>

<FORM METHOD=POST ACTION="Register">

Username: <INPUT TYPE=TEXT NAME=rusername SIZE=20><BR><BR>
Password: <INPUT TYPE=PASSWORD NAME=rpassword SIZE=20><BR><BR>
Firstname: <INPUT TYPE=TEXT NAME=rfirstname SIZE=20><BR><BR>
Lastname: <INPUT TYPE=TEXT NAME=rlastname SIZE=20><BR>

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