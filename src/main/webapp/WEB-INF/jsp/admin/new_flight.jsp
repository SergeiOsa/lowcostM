<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>New flight Page</title>
</head>
<body>
 <p>Add New Flight:</p>
 	
 	<form action="controller" method="post">
 	<input type="hidden" name="command" value="add_new_flight">
	<Table cellspacing=3>
		<tr><td>Departure Place: </td><td><input type="text" name="origin" value=""></td></tr>
		<tr><td>Arrival Place: </td><td><input type="text" name="destination" value=""></td></tr>
		<tr><td>Time Departure: </td><td><input type="text" name="timeDeparture" value=""></td></tr>
		<tr><td>Time Arrival: </td><td><input type="text" name="timeArrival" value=""></td></tr>
		<tr><td>Number Of Seats: </td><td><input type="text" name="numberOfSeats" value=""></td></tr>
		<tr><td>Empty Seats: </td><td><input type="text" name="emptySeats" value=""></td></tr>
		<tr><td>Distance: </td><td><input type="text" name="distance" value=""></td></tr>	
		<tr><td><input type="submit" value="Add New Flight"></td></tr>
	</Table>
	</form>
</body>
</html>