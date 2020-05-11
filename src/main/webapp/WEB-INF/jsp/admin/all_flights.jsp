<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>All flights Page</title>
</head>
<body>

	<!--  Подключить библиотеку jQuery -->
	<script src="js/jquery.min.js"></script>
	<!--  Подключить библиотеку moment -->
	<script src="js/moment-with-locales.min.js"></script>
	<!--  Подключить js-файл фреймворка Bootstrap 3 -->
	<script src="js/bootstrap.min.js"></script>
	<!--  Подключить js-файл библиотеки Bootstrap 3 DateTimePicker -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="get_all_flights">
		<p>
			<strong>Departure after: </strong> <input type="date" name="departure"
				value="" />
		</p>
		<input type="submit" value="Get all flights">
	</form>

	<c:if test="${isSuccessful eq true}">
		
		<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>Flight Number</td><td>Origin</td><td>Destination</td><td>Departure</td><td>Arrival</td>
        		<td>Number Of Seats</td><td>Empty seats</td><td>Distance</td>
        	</tr>
        	<c:forEach var="flight" items="${flightsSet}" >		
        		<tr bgcolor="yellow">
        			<td><c:out value="${flight.flightNumber}"/></td><td><c:out value="${flight.origin}"/></td>
        			<td><c:out value="${flight.destination}"/></td><td><c:out value="${flight.departure}"/></td>
        			<td><c:out value="${flight.arrival}"/></td><td><c:out value="${flight.numberOfSeats}"/></td>
        			<td><c:out value="${flight.emptySeats}"/></td><td><c:out value="${flight.distance}"/></td>
        			<td bgcolor="white">
       					<form action="controller" method="get">
	       					<input type="hidden" name="command" value="get_ticket_info" />
	    	   				<input type="hidden" name="flightNumber" value="${flight.flightNumber }"/>
    	    				<input type="submit" value="Order information" />  		
		        		</form>
        			</td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
		
	</c:if>

</body>
</html>