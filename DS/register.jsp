<html>
<body>
<h2>Register</h2>

<FORM METHOD=POST ACTION="Register">

<table cellspacing="10">

<tr>
<td>Username: </td><td><INPUT TYPE=TEXT NAME=rusername SIZE=20></td>
</tr>
<tr>
<td>Password: </td><td><INPUT TYPE=PASSWORD NAME=rpassword SIZE=20></td>
</tr>
<tr>
<td>Firstname: </td><td><INPUT TYPE=TEXT NAME=rfirstname SIZE=20></td>
</tr>
<tr>
<td>Lastname: </td><td><INPUT TYPE=TEXT NAME=rlastname SIZE=20></td>
</tr>

<tr>
<td><INPUT TYPE=SUBMIT VALUE="Register"></td>
</tr>

</table>
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