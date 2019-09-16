<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Exam</title>

</head>
<body>
<%

try {

String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
Class.forName("oracle.jdbc.OracleDriver").newInstance();
connection = DriverManager.getConnection(connectionURL, "hr", "hr");
statement = connection.createStatement();
HttpSession sess = request.getSession(false);
int exam_id = (Integer)sess.getAttribute("exam_id");
String QueryString = "SELECT * from questions where exam_id = " + exam_id + " order by q_no asc";
rs = statement.executeQuery(QueryString);
//rs2 = statement.executeQuery(query2);
//Timestamp start = rs2.getTimestamp(1);
//Timestamp end = rs2.getTimestamp(2);
//System.out.println(start);
//System.out.println(end);

%>
 <style>
 * { font-size: 70px; font-family: Verdana }    
 </style>

<center><h1>QUESTIONS</h1></center></hr>
 <style>
 * { font-size: 30px; font-family: Verdana }    
 </style>
 <form action="Result" method="post">
	 <%
	int i=1;
	while (rs.next()) {
	%>
	<fieldset>
	<legend><strong><%=rs.getInt(2)%>
	. 
	<%=rs.getString(3)%></strong></br></legend>
	<div></div><input type="radio"  name="<%=i %>" value="1"/>
	: <%=rs.getString(4)%></br></div>
	<div><input type="radio"  name="<%=i %>" value="2"/>
	: <%=rs.getString(5)%></br></div>
	<div><input type="radio"  name="<%=i %>" value="3"/>
	: <%=rs.getString(6)%></br></div>
	<div><input type="radio"  name="<%=i %>" value="4"/>
	: <%=rs.getString(7)%></br></div>
	</fieldset>
	</br>
	</hr>
	<% i++;} %>
	
 
 	<center><input type="submit" name="Submit" value="Submit" height="auto" width="auto"></center>
 	</form>
 
<%
// close all the connections.
rs.close();
statement.close();
connection.close();
} catch (Exception ex) {
%>
</font>
<font size="+3" color="red"></b>
<%
out.println("Unable to connect to database.");
ex.printStackTrace();
}
%>



</body>
</html>
