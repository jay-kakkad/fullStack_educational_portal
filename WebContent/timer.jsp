	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Exam Details</title>
</head>
<body>
<form action="Timer" method="post">
<center>


<h2><i>Enter Exam Details</i><br></h2>


<b>
<br>
Subject:<select name="subject">
<option value="Maths">Maths</option>
<option value="Data Structure">Data Structure</option>
<option value="Java">Java</option>
<option value="RDBMS">RDBMS</option>
</select>

<br><br>
Exam ID :<%="   "%><input type="text" name="ExamID" size="2"/><br><br>

Start Date and Time: <input type="text" placeholder="yyyy-mm-dd hh:mm:ss" name="start"><br><br><br>

End Date and Time: <input type="text" placeholder="yyyy-mm-dd hh:mm:ss" name="end"> 
</b><br><br><br>
<input type="submit" value="Set"/>
</center><br>
</form>
</body>
</html>