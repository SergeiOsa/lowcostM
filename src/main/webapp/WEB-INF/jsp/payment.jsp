<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Payment</title>
</head>
<body>
	

     <p>Your Ticket:</p>
   
	<table cellspacing=3>
			<tr bgcolor="paleturquoise">
        		<td>Ticket Id</td><td><c:out value="${ticket.ticketId}"/></td>
        	</tr>
        	<tr bgcolor="yellow">
        		<td>Flight Number</td><td><c:out value="${ticket.flightNumber}"/></td>
        	</tr>	
        	<tr bgcolor="paleturquoise">
        		<td>Origin</td><td><c:out value="${flight.origin}"/></td>
        	</tr>
        	<tr bgcolor="yellow">
        		<td>Destination</td><td><c:out value="${flight.destination}"/></td>
        	</tr>	
        	<tr bgcolor="paleturquoise">
        		<td>Departure</td><td><c:out value="${flight.departure}"/></td>
        	</tr>
         	<tr bgcolor="yellow">
        		<td>Name</td><td><c:out value="${ticket.name}"/></td>
        	</tr>		
        	<tr bgcolor="paleturquoise">
       			<td>Surname</td><td><c:out value="${ticket.surname}"/></td>
       	 	</tr>	
       	 	<tr bgcolor="yellow">
        		<td>Passport</td><td><c:out value="${ticket.passport}"/></td>
        	</tr>
        	<tr bgcolor="paleturquoise">
       			<td>Priority registration</td><td><c:out value="${ticket.priorityRegistration}"/></td>
       	 	</tr>
       	 	<tr bgcolor="yellow">
        		<td>Extra baggage</td><td><c:out value="${ticket.isBaggage}"/></td>
        	</tr>	
     </table>
     
     <c:if test="${paymentStatus eq false}">
     	<p><font size = "5" color = "red">Payment failed!</font></p>
     </c:if>
     
     <form action="controller" method="post">
     <input type="hidden" name="command" value="payment" />
     <table>
     	<tr>
     		<td>Card Number</td><td><input type="text" name="cardNumber" value=""></td>
     	</tr>
     	<tr>
     		<td>Card Holder</td><td><input type="text" name="cardHolder" value=""></td>
     	</tr>
     	<tr>
     		<td>Valid thru</td><td><input type="text" name=" validThru" value=""></td>
     	</tr>
     	<tr>
     		<td>cvv/cvc</td><td><input type="text" name="cvvCvc" value=""></td>
     	</tr>
     </table>
     <input type="submit" value="Pay">
	</form>

</body>
</html>