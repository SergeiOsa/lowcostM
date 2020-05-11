<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="booking_step_1.jsp" />
</jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Booking page</title>
</head>
<body>
	<h1>Booking Step 1:</h1>

	<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Origin</td><td>Destination</td><td>Departure</td><td>Arrival</td><td>Price</td>
        	</tr>		
        		<tr bgcolor="yellow">
        			<td><c:out value="${preOrder.flight.origin}"/></td><td><c:out value="${preOrder.flight.destination}"/></td>
        			<td><c:out value="${preOrder.flight.departure}"/></td><td><c:out value="${preOrder.flight.arrival}"/></td>
        			<td><c:out value="${preOrder.price}"/></td>
       	 		</tr>	
     </table> 	
	
	<form action="controller" method="get">
		
		<input type="hidden" name="command" value="booking_step_1">
		<input type="hidden" name="flightNumber" value="${preOrder.flight.flightNumber }">
		<input type="hidden" name="price" value="${preOrder.price }">
		
		<p><b>Priority registration:</b></p>
		<input name="priorityRegistration" type="radio" value="Yes" > Yes   
		<input name="priorityRegistration" type="radio" value="No" checked > No</br>	
		<a>Priority registration costs </a>
		<c:set var="prPrice" scope="page" value="${preOrder.price / 10 }" />
		<c:out value="${prPrice }" />	
		 
		<p><b>Baggage:</b></p>
		<p>
			<input name="isBaggage" type="radio" value="Yes" > Yes
			<input name="isBaggage" type="radio" value="No" checked > No </br>
			<a>Extra baggage (up to 20kg) cost </a>
			<c:set var="baggagePrice" scope="page" value="${preOrder.price / 10 }" />
			<c:out value="${baggagePrice }" />
		</p>
		
		<input type="submit" value="Submit">
	</form>
	
</body>
</html>