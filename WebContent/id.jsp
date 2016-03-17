<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mancala</title>
</head>
<body>
	Welcome to Mancala!
	<br>
	Use this id to connect with your partner: 
	<%
		out.print(request.getAttribute("id"));
	%>
	
	<br>
	If you have id of your partner, enter it here:
	<form method="post" action="app/start">
		<input type="text" value="" name="id" />
	</form>
</body>
</html>