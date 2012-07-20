<%@ page import="com.web.model.*, com.web.view.*, java.io.*" %>

<html>
<body>
<P><a align=right href="Logout">Logout</a>

<h2>Download Page</h2>
<%

	if (session.getAttribute("username") == null)
		response.sendRedirect("index.jsp");
	else{
		String username = request.getParameter( "username" );
	}
%>

<h4> Hello, <%=session.getAttribute("username") %></h4>
<%
	File[] files = new FileDisplay().getFiles();
	for (File f : files){
%>
	<a href="Download?value=<%=f.getName()%>"><%=f.getName()%></a><br>
<%		
	}
%>

</body>
</html>