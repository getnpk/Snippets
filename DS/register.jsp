<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<c:if test="${not empty register_error}">
	<c:set var="msg" scope="session" value="${register_error}"/>
	<i> You have a problem: ${msg}</i>
</c:if>


<P><a href="index.jsp">Login</a>


</body>
</html>