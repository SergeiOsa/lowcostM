<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="index.jsp" />
</jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.line.flights" var="flights" />
<fmt:message bundle="${loc}" key="local.line.origin" var="origin" />
<fmt:message bundle="${loc}" key="local.line.destination" var="destination" />
<fmt:message bundle="${loc}" key="local.line.departure" var="departure" />
<fmt:message bundle="${loc}" key="local.button.search" var="search" />

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
	
	<form action="controller" method="get">
		<input type="hidden" name="command" value="search" />
		<p><strong>${flights} </strong></p>
		<p>
			<strong>${origin}: </strong>
				<select name="origin">
    		    	<option value="Berlin">Berlin</option>
        			<option value="London">London</option>
        			<option value="Minsk">Minsk</option>
	        		<option value="Moscow">Moscow</option>
    	    		<option value="New York">New York</option>
        			<option value="Paris">Paris</option>
        			<option value="Pinsk">Pinsk</option>       
    			</select>
			
			<strong>${destination}: </strong>
			<select name="destination">
    		    	<option value="Berlin">Berlin</option>
        			<option value="London">London</option>
        			<option value="Minsk">Minsk</option>
	        		<option value="Moscow">Moscow</option>
    	    		<option value="New York">New York</option>
        			<option value="Paris">Paris</option>
        			<option value="Pinsk">Pinsk</option>       
    			</select>
		</p>
		<p><strong>${departure}: </strong>
		<input type="date" name="departure" value=""/></p>
		<input type="submit" value="${search}" /><br/>
		
	</form>
	
</body>
</html>