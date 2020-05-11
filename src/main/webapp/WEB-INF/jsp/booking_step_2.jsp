<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booking Step 2</title>
</head>
<body>
<h1>Booking Step 2:</h1>

		
	
	<form action="controller" method="post">
		
		<input type="hidden" name="command" value="booking_step_2">
		
		<p><b>Enter Name: </b></p>
		<p><input type="text" name="name" value=""></p>
	
		<p><b>Enter Surname: </b></p>
		<p><input type="text" name="surname" value=""></p>	
	
		<p><b>Enter passport data: </b></p>
		<p><input type="text" name="passport" value=""></p>	
		
		<input type="submit" value="Submit">
	</form>
	
</body>
</html>