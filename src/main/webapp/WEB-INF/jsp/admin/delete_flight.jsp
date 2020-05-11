<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Delete Flight Page</title>
</head>
<body>
	<p>hi/ delete flight</p>
	
	<!--  Подключить библиотеку jQuery -->
	<script src="js/jquery.min.js"></script>
	<!--  Подключить библиотеку moment -->
	<script src="js/moment-with-locales.min.js"></script>
	<!--  Подключить js-файл фреймворка Bootstrap 3 -->
	<script src="js/bootstrap.min.js"></script>
	<!--  Подключить js-файл библиотеки Bootstrap 3 DateTimePicker -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete_flight">
			<strong>Date from: </strong> <input type="date" name="dateFrom"
				value="" />
				<strong>Date to: </strong> <input type="date"
				name="dateTo" value="" />
		<input type="submit" value="Get flights">
	</form>
	
	<c:if test="${isSuccessful eq true}">
	<form action="controller" method="get">
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
	       					<input type="hidden" name="command" value="delete_selected" />
							<input type="checkbox" name="checkboxTest" value="${flight.flightNumber}"/>
        			</td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
        <input type="submit" value="Delete">
      </form>
	</c:if>
	
</body>
</html>