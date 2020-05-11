<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Check Booking</title>
</head>
<body>
<h1>Please, check the entered data!</h1>

	<p>Your flight:</p>
	<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Origin</td><td>Destination</td><td>Departure</td><td>Arrival</td>
        	</tr>		
        	<tr bgcolor="yellow">
        		<td><c:out value="${flight.origin}"/></td><td><c:out value="${flight.destination}"/></td>
       			<td><c:out value="${flight.departure}"/></td><td><c:out value="${flight.arrival}"/></td>
   	 		</tr>	
     </table> 	
     <p>Your personal details:</p>
	<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Name</td><td><c:out value="${ticket.name}"/></td>
        	</tr>		
        	<tr bgcolor="yellow">
       			<td>Surname</td><td><c:out value="${ticket.surname}"/></td>
       	 	</tr>	
       	 	<tr bgcolor="paleturquoise">
        		<td>Passport</td><td><c:out value="${ticket.passport}"/></td>
        	</tr>
        	<tr bgcolor="yellow">
       			<td>Priority registration</td><td><c:out value="${ticket.priorityRegistration}"/></td>
       	 	</tr>
       	 	<tr bgcolor="paleturquoise">
        		<td>Extra baggage</td><td><c:out value="${ticket.isBaggage}"/></td>
        	</tr>
        	<tr bgcolor="yellow">
       			<td>Price</td><td><c:out value="${ticket.price}"/></td>
       	 	</tr>	
     </table> 	
     
     <form action="controller" method="post">
     	<input type="hidden" name="command" value="booking">
     	<input type="submit" value="Booking">
     </form>
     
    <p>здесь нужно реализовать редактирование данных</p>     
</body> 
</html>