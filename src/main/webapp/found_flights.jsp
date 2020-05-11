<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="found_flights.jsp" />
</jsp:include>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Found flights</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.line.flights" var="flights" />
<fmt:message bundle="${loc}" key="local.line.origin" var="origin" />
<fmt:message bundle="${loc}" key="local.line.destination" var="destination" />
<fmt:message bundle="${loc}" key="local.line.departure" var="departure" />
<fmt:message bundle="${loc}" key="local.line.arrival" var="arrival" />
<fmt:message bundle="${loc}" key="local.line.price" var="price" />
<fmt:message bundle="${loc}" key="local.line.emptySeats" var="emptySeats" />
<fmt:message bundle="${loc}" key="local.line.chooseAFlight" var="chooseAFlight" />
<fmt:message bundle="${loc}" key="local.messege.needLogIn" var="needLogIn" />

</head>
<body>
	<h1>${flights}:</h1>
	
	<c:if test="${user ne null }">
		 	
    	<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>${origin}</td><td>${destination}</td><td>${departure}</td><td>${arrival}</td>
        		<td>${emptySeats}</td><td>${price}</td>
        	</tr>
        	<c:forEach var="preOrder" items="${preOrdersSet}" >		
        		<tr bgcolor="yellow">
        			<td><c:out value="${preOrder.flight.origin}"/></td><td><c:out value="${preOrder.flight.destination}"/></td>
        			<td><c:out value="${preOrder.flight.departure}"/></td><td><c:out value="${preOrder.flight.arrival}"/></td>
        			<td><c:out value="${preOrder.flight.emptySeats}"/></td><td><c:out value="${preOrder.price}"/></td>
       				<td bgcolor="white">
       					<form action="controller" method="get">
	       					<input type="hidden" name="command" value="choose_flight" />
	    	   				<input type="hidden" name="flightNumber" value="${preOrder.flight.flightNumber }"/>
	    	   				<input type="hidden" name="price" value="${preOrder.price }"/> 
    	    				<input type="submit" value="${chooseAFlight}" />  		
		        		</form>
        			</td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
    </c:if>
    
    <c:if test="${user eq null }">
		<p><a href="/lowcostM/logination.jsp">${needLogIn}.</a></p>
		<table cellspacing=3>
        	<tr bgcolor="paleturquoise">
        		<td>${origin}</td><td>${destination}</td><td>${departure}</td><td>${arrival}</td>
        		<td>${emptySeats}</td><td>${price}</td>
        	</tr>
        	<c:forEach var="preOrder" items="${preOrdersSet}" >		
        		<tr bgcolor="yellow">
        			<td><c:out value="${preOrder.flight.origin}"/></td><td><c:out value="${preOrder.flight.destination}"/></td>
        			<td><c:out value="${preOrder.flight.departure}"/></td><td><c:out value="${preOrder.flight.arrival}"/></td>
        			<td><c:out value="${preOrder.flight.emptySeats}"/></td><td><c:out value="${preOrder.price}"/></td>
       	 		</tr>	
       		 </c:forEach>			
        </table> 	
    </c:if>
    
</body>
</html>