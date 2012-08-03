<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	
	<body>

<c:choose>
	<c:when test="${empty username }">
		<%session.invalidate(); %>
		<c:redirect url="index.jsp"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="user" value="${username }"/>
	</c:otherwise>
</c:choose>


<a style= "float:right" href="Logout">Logout</a><BR>
	
<h2>Hi, ${user} you can now upload! </h2>

<form action="Upload" enctype="multipart/form-data" method="POST">
	<input type="file" multiple name="file1"><br><br>
	<input type="Submit" value="Upload File"><br>
</form>


<c:if test="${not empty donefiles }">

<c:forEach var="thelist" items="${donefiles} ">

	<c:choose>
		<c:when test="${fn:contains(thelist, 'exists') }">
			<h5 style="color:red">Status: ${thelist}</h5>
		</c:when>
		<c:otherwise>
			<h5 style="color:blue">Status: ${thelist}</h5>
		</c:otherwise>
	</c:choose>
	

</c:forEach>

</c:if>

<P><a href="<c:url value='download.jsp'/>">Download Page</a><BR>	

</body>
</html>