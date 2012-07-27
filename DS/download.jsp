<%@ page import="com.web.model.*, com.web.view.*, java.io.*, java.util.*" %>

<html>
<body>
<P><a align = "right" href="Logout">Logout</a>

<h2>Download Page</h2> 


<FORM METHOD=POST ACTION="download.jsp">

Filetype/Filename: <INPUT TYPE=TEXT NAME=filename SIZE=20><BR><BR>


<P><INPUT TYPE=SUBMIT VALUE="Filter"><BR>

</FORM>


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
	String filename = request.getParameter("filename");
	
	
	ArrayList<DBFile> files = new FileDisplay().getFiles(filename);
	
%>
	
<%
	
	if (!files.isEmpty()){
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
%>

</body>
</html>