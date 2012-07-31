<%@ page import = "java.util.*" %>
<html>
	<head></head>
	<body>

<%
	String username = null;

	if (session.getAttribute("username") == null){
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	else{
		username = (String) session.getAttribute("username");
	}
%>
		<a style= "float:right" href="Logout">Logout</a><BR>
		<h2>Upload Files, <%= username %></h2>
		<form action="Upload" enctype="multipart/form-data" method="POST">
			<input type="file" multiple name="file1"><br><br>
			<input type="Submit" value="Upload File"><br>
		</form>
		

		<%
		
		ArrayList<String> thelist = (ArrayList<String>)request.getAttribute("donefiles");
		if (thelist != null){
			for (String name : thelist){
		%>
			<h5 style="color:red">Status: <%= name %></h5>	
		<%		
			}
		}
		%>
		
		<P><a href="download.jsp">Download Page</a><BR>	
	</body>
</html>