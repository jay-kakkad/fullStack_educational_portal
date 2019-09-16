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
<body background="clipb.jpg">
<%
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
String QueryString = "SELECT * from questions";
rs = statement.executeQuery(QueryString);
%>
 <style>
 * { font-size: 55px; font-family: Verdana }    
 </style>

<center><h1>QUESTIONS</h1></center><hr>
 <style>
 * { font-size: 20px; font-family: Verdana }    
 </style>
<%
int i=0;
while (rs.next()) {
%>
<fieldset>
<legend><strong><%=rs.getInt(2)%>
. 
<%=rs.getString(3)%></strong><br></legend>
<div><input type="radio"  name="n"+i value="q1"+i/>
: <%=rs.getString(4)%><br></div>
<div><input type="radio"  name="n"+i value="q2"+i/>
: <%=rs.getString(5)%><br></div>
<div><input type="radio"  name="n"+i value="q3"+i/>
: <%=rs.getString(6)%><br></div>
<div><input type="radio"  name="n"+i value="q4"+i/>
: <%=rs.getString(7)%><br></div><br>
<div>Correct option:<%=rs.getString(8)%></div>
</fieldset>
<br>
<hr>
<% i++;} %>
<%
// close all the connections.
rs.close();
statement.close();
connection.close();
} catch (Exception ex) {
%>

<font size="+3" color="red"><b>
<%
out.println("Unable to connect to database.");
}
%></font>

<a href='facultyWelcome.jsp'>Go to Examination Cell Home Page</a>





</body>
</html>