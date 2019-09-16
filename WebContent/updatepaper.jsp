<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update question paper</title>
</head>
<body>
<h3>Update the paper</h3>
<form action="UpdatePaper" method="post">
<b>Enter Question No:</b>
<input type="text" size="2" name="qno"/><br><input type="button" name="click" value="Show Question"/>
<br>
<b>Question:</b><br> <textarea name="que" rows="5" cols="80"></textarea>
<br><br>
<b>
option 1:      <textarea name="opt1" rows="2" cols="55"></textarea><br><br>
option 2:      <textarea name="opt2" rows="2" cols="55"></textarea><br><br>
option 3:     <textarea name="opt3" rows="2" cols="55"></textarea><br><br>
option 4:      <textarea name="opt4" rows="2" cols="55"></textarea><br><br>
<br></b>
<b>Correct Option:</b><input type="text" name="ans" size="2"/><br>  <input type="submit" value="Next"/>
<br><a href='facultyWelcome.jsp'>Go to Examination Cell Home Page</a>
</form>

</body>
</html>