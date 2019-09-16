<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%!int i=1; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Question Paper</title>
</head>
<body>

<h2>Set Question Paper</h2>
<form action="MainQue" method="post">

<h3>Set QNo: <%=String.valueOf(i++) %></h3>
<h3>Enter Question:</h3>
<textarea name="que" rows="5" cols="80"></textarea>
<br><br>
<b>
option 1:      <textarea name="opt1" rows="2" cols="55"></textarea><br><br>
option 2:      <textarea name="opt2" rows="2" cols="55"></textarea><br><br>
option 3:     <textarea name="opt3" rows="2" cols="55"></textarea><br><br>
option 4:      <textarea name="opt4" rows="2" cols="55"></textarea><br><br>
<br></b>
<b>Correct Option:</b>
<select name="ans"> 
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>

</select><br><br><input type="submit" value="Next"/><br><br>
<a href='facultyWelcome.jsp'>Go to Examination Cell Home Page</a>
</form>
<br>


</body>
</html>