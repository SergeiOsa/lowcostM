<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Your orders</title>
</head>
<body>
		
        <table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Ticket Id</td><td>Origin</td><td>Destination</td><td>Departure</td>
        		<td>Name</td><td>Surname</td><td>Priority Registration</td><td>Baggage Id</td><td>Price</td>
        	</tr>
        	<c:forEach var="report" items="${reportList}" >		
        		<tr bgcolor="yellow">
        			<td><c:out value="${report.ticketId}"/></td><td><c:out value="${report.origin}"/></td>
        			<td><c:out value="${report.destination}"/></td><td><c:out value="${report.departure}"/></td>
        			<td><c:out value="${report.name}"/></td><td><c:out value="${report.surname}"/></td>
        			<td><c:out value="${report.priorityRegistration}"/></td><td><c:out value="${report.baggageId}"/></td>
        			<td><c:out value="${report.price}"/></td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
        
</body>
</html>