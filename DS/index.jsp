<%@ page import="com.web.model.*, com.web.view.*" %>

<html>
<body>

<%-- Jdbc connection setup  --%>
<%

	ServletContext sc = getServletContext();
	String db_database = sc.getInitParameter("db_database");
	String db_username = sc.getInitParameter("db_username");
	String db_password = sc.getInitParameter("db_password");
	JDBCConnect con = JDBCConnect.getObject(db_username, db_password, db_database);
	
	System.out.println("Connection established at Login");
	
	User user = new User();
%>

<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	user.setUsername(username);
	user.setPassword(password);
	Boolean pass = con.pass(user);
	
%>

<h2>Welcome!</h2>
<FORM METHOD=POST ACTION="index.jsp">

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
		session.removeAttribute("error");
		session.setAttribute("error", null);

	}
%>

<P><a href="register.jsp">Register</a>


<%
if (pass){
	
	session.setMaxInactiveInterval(20 * 60); // mins
	
	session.setAttribute( "username", username );
%>
	<jsp:forward page="download.jsp"></jsp:forward>
<%	
}else{
	String error_message = "Something went wrong!";
	session.setAttribute("error", error_message);
}
%>

</body>
</html>