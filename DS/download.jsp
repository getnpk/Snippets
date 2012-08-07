<%@ page import="com.web.model.*, com.web.view.*, java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stats" uri="customTags" %>
<html>
<body>

<P><a href="<c:url value='upload.jsp'/>">Upload</a>
<a style= "float:right" href="Logout">Logout</a><BR>


<h2>Download Page</h2> 


<FORM METHOD=POST ACTION="Download">

Filetype/Filename: <INPUT TYPE=TEXT NAME=filename SIZE=20>
User: <INPUT TYPE=TEXT NAME=user SIZE=20><BR><BR>

<P><INPUT TYPE=SUBMIT VALUE="Filter"><BR>

</FORM>

<c:set var="user" scope="session" value="${username}"/>
<h4> Hello, ${user}!</h4>




<c:if test="${not empty dbfiles }">


<c:choose>

	<c:when test="${not empty dbfiles}">
		<table border="1" width = 100%>
		<tr>
			<th width = 50%>Filename</th>
			<th width = 10%>Filetype</th>
			<th width = 10%>Filesize (KB)</th>
			<th width = 10%>User</th>
			<th width = 20%>File age</th>
		</tr>
		</table>
	<table border="1" width = 100%>
	<c:forEach var="dbfile" items="${dbfiles}">
	
		<tr>
		<td width = 50%><a href="Download?filename=${dbfile.filename}">${dbfile.filename}</a></td>
		<td width = 10%>${dbfile.filetype}</td>
		<td width = 10%>${dbfile.filesize}</td>
		<td width = 10%>${dbfile.userRequested}</td>
		<td width = 20%><stats:age filename="${dbfile.filename }"/></td>
		</tr>
	</c:forEach>
	</table>		
	
	</c:when>

	<c:otherwise>
		<h4>Could not find any matching files!</h4>		
	</c:otherwise>

</c:choose>


</c:if>


</body>
</html>