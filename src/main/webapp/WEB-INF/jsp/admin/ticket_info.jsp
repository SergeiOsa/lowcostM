<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Order Info</title>
</head>
<body>
		<p>All flight number <c:out value="${flightNumber}"></c:out> tickets:</p>
        <table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Ticket Id</td><td>Flight Number</td><td>Price</td><td>Booked</td>
        		<td>Paid</td><td>Priority Registration</td><td>Baggage Id</td><td>Order Id</td><td>Name</td>
        		<td>Surname</td><td>Passport</td>
        	</tr>
        	<c:forEach var="ticket" items="${ticketsList}" >		
        		<tr bgcolor="yellow">
        			<td><c:out value="${ticket.ticketId}"/></td><td><c:out value="${ticket.flightNumber}"/></td>
        			<td><c:out value="${ticket.price}"/></td><td><c:out value="${ticket.booked}"/></td>
        			<td><c:out value="${ticket.paid}"/></td><td><c:out value="${ticket.priorityRegistration}"/></td>
        			<td><c:out value="${ticket.baggageId}"/></td><td><c:out value="${ticket.orderId}"/></td>
        			<td><c:out value="${ticket.name}"/></td><td><c:out value="${ticket.surname}"/></td>
        			<td><c:out value="${ticket.passport}"/></td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
        
</body>
</html>