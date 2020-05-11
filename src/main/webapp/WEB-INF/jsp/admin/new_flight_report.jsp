<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>New Flight Report Page</title>
</head>
<body>
	<p>Successfully!</p>
	<table cellspacing=3>
		<tr bgcolor="paleturquoise">
			<td>Flight Number</td><td>Departure Place</td><td>Arrival Place</td><td>Time Departure</td><td>Time Arrival</td>
			<td>Number Of Seats</td><td>Empty Seats</td><td>Distance</td>
		</tr>
		<tr bgcolor="yellow">
			<td><c:out value="${requestScope.flight.flightNumber}"/></td><td><c:out value="${requestScope.flight.origin}"/></td>
			<td><c:out value="${requestScope.flight.destination}"/></td><td><c:out value="${requestScope.flight.departure}"/></td>
			<td><c:out value="${requestScope.flight.arrival}"/></td><td><c:out value="${requestScope.flight.numberOfSeats}"/></td>
			<td><c:out value="${requestScope.flight.emptySeats}"/></td><td><c:out value="${requestScope.flight.distance}"/></td>
		</tr>
	</table>
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go_to_add_new_flight">
		<input type="submit" value="Add New Flight">
	</form>
</body>
</html>