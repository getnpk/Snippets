<%@ page import = "java.util.*" %>
<html>
	<head></head>
	<body>
		<h2>Upload Files</h2>
		<form action="Upload" enctype="multipart/form-data" method="POST">
			<input type="file" multiple name="file1"><br><br>
			<input type="Submit" value="Upload File"><br>
		</form>
		
		<%
		
		ArrayList<String> thelist = (ArrayList<String>)request.getAttribute("donefiles");
		if (thelist != null){
			for (String name : thelist){
		%>
			<h5>Uploaded: <%= name %></h5>	
		<%		
			}
		}
		%>
			
	</body>
</html>