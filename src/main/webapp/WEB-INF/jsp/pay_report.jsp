<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Pay Report</title>
</head>
<body>
	<p><font size = "5" color = "green">Payment successful</font></p>

	<table cellspacing=3>
			<tr bgcolor="paleturquoise">
        		<td>Ticket Id</td><td>Flight Number</td><td>Origin</td><td>Destination</td><td>Departure</td><td>Name</td><td>Surname</td>
        		<td>Priority registration</td><td>Extra baggage</td>
        	</tr>
        	<tr bgcolor="yellow">
        		<td><c:out value="${ticket.ticketId}"/></td><td><c:out value="${ticket.flightNumber}"/></td>
        		<td><c:out value="${flight.origin}"/></td><td><c:out value="${flight.destination}"/></td>
        		<td><c:out value="${flight.departure}"/></td><td><c:out value="${ticket.name}"/></td>
        		<td><c:out value="${ticket.surname}"/></td><td><c:out value="${ticket.priorityRegistration}"/></td>
        		<td><c:out value="${ticket.isBaggage}"/></td>
        	</tr>	
      </table>

	<input type="submit" value="Print. nothingWillHappen((">
</body>
</html>