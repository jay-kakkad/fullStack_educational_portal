<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Exam</title>
<style>
body{
background-image:url("../img/bg.jpg");
background-assigned:fixed;
background-repeat:no-repeat;
background-size:100% 100%;
}
</style>
</head>
<% 
String sec;
try {
/* Create string of connection url within specified format with machine
name, port number and database name. Here machine name id localhost and 
database name is student. */
String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
// declare a connection by using Connection interface
Connection connection = null;
/* declare object of Statement interface that is used for executing sql 
statements. */
Statement statement = null;
// declare a resultset that uses as a table for output data from tha table.
ResultSet rs = null;
// Load JBBC driver "com.mysql.jdbc.Driver"
Class.forName("oracle.jdbc.OracleDriver").newInstance();
/* Create a connection by using getConnection() method that takes parameters 
of string type connection url, user name and password to connect to database.*/
connection = DriverManager.getConnection(connectionURL, "hr", "hr");
/* createStatement() is used for create statement object that is used for 
sending sql statements to the specified database. */
statement = connection.createStatement();
		
// sql query to retrieve values from the secified table.
HttpSession sess = request.getSession(false);
int exam_id = (Integer)sess.getAttribute("exam_id");
String QueryString = "SELECT start_time,end_time from exam where exam_id= " + exam_id;
rs = statement.executeQuery(QueryString);
Timestamp st=null,et=null;
while(rs.next())
{
	st = rs.getTimestamp(1);
	et = rs.getTimestamp(2);
}




Timestamp timestamp = new Timestamp(System.currentTimeMillis());
RequestDispatcher rd;
if(timestamp.after(st) && timestamp.before(et)){
	rd = request.getRequestDispatcher("test.jsp");
	rd.forward(request, response);
	
}
else if(timestamp.after(et)){
	out.print("<marquee><h1>Sorry you can't give the test now!!! Test over at "+et+".</h1></marquee>");
}
else if(timestamp.before(st)){
	out.print("<marquee><h1>The test starts today at "+st+". Refresh the page at "+ st +".</h1></marquee>");
}


}catch(Exception e)
{
	out.println(e);
}

%>

<body>

</body>
</html>