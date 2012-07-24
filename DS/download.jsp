<%@ page import="com.web.model.*, com.web.view.*, java.io.*, java.util.*" %>

<html>
<body>
<P><a float="right" href="Logout"">Logout</a>

<h2>Download Page</h2> 
<%

	new Loader().load();

	if (session.getAttribute("username") == null)
		response.sendRedirect("index.jsp");
	else{
		String username = request.getParameter( "username" );
	}
%>

<h4> Hello, <%=session.getAttribute("username") %></h4>
<%
	ArrayList<File> files = new FileDisplay().getFiles();

	for (File f : files){
%>
	<a href="Download?filename=<%=f.getName()%>"><%=f.getName()%></a><br>
<%		

	}
%>

</body>
</html>