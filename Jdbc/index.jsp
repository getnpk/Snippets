<%@ page import="java.io.*,java.sql.*,view.*" %>


<html>
<h2>Test Page</h2>

<%

String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
Hbase ob = new Hbase();

try {
    Class.forName(driverName);
  } catch (ClassNotFoundException e) {
     
    e.printStackTrace();
    System.exit(1);
  }
  Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/default", "hadoop", "hadoop");
  Statement stmt = con.createStatement();
  String tableName = "nasdaq";
  
  
  ResultSet mes = stmt.executeQuery("show tables");
 
%> 

<%
 mes = stmt.executeQuery("select * from " + tableName + " where monyear=\"mar2012\" and logdate=\"20120308\" limit 10");
  while(mes.next()){
%>
	<br>
	Symbol:<%= mes.getString("symbol")%>
  	LogDate:<%= mes.getString("thedate")%>
  	Volume:<%= mes.getString("volume")%><br>
  
<%
  }
%>


<h3><br><%=ob.getvalue("one", "details", "name")%></h3>
<h3><%=ob.getvalue("three", "details", "name")%></h3>
<h3><%=ob.getvalue("four", "details", "name")%></h3>


</html>