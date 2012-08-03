<%@ page import="com.web.model.*, com.web.view.*, java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<P><a align = "right" href="upload.jsp">Upload</a>
<a style= "float:right" href="Logout">Logout</a><BR>


<h2>Download Page</h2> 


<FORM METHOD=POST ACTION="Download">

Filetype/Filename: <INPUT TYPE=TEXT NAME=filename SIZE=20>
User: <INPUT TYPE=TEXT NAME=user SIZE=20><BR><BR>

<P><INPUT TYPE=SUBMIT VALUE="Filter"><BR>

</FORM>


<h4> Hello, <%= session.getAttribute("username") %></h4>


<%
	
	ArrayList<DBFile> files = (ArrayList<DBFile>) request.getAttribute("dbfiles");

	if (files != null){
		if ( !files.isEmpty() ){
%>

	<table border="1" width = 100%>
	<tr>
		<th width = 50%>Filename</th>
		<th width = 10%>Filetype</th>
		<th width = 10%>Filesize (KB)</th>
		<th width = 30%>User Requested</th>
	</tr>
	</table>
<%		
	for (DBFile f : files){
%>	
	
	<table border="1" width = 100%>
	<tr>
		<td width = 50%><a href="Download?filename=<%=f.getFilename()%>"><%=f.getFilename()%></a></td>
		<td width = 10%><%= f.getFiletype() %></td>
		<td width = 10%><%= f.getFilesize() %></td>
		<td width = 30%><%= f.getUserRequested() %></td>
	</tr>
	</table> 
	
<%		
		}
	}else{
%>
	<h2> No files found, empty!</h2>
<%		
	}
	}
%>

</body>
</html>