<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
<jsp:include page="/WEB-INF/jsp/header_for_web_inf.jsp">
    <jsp:param name="pageName" value="/WEB-INF/jsp/auth_user.jsp" />
</jsp:include>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User info</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.line.id" var="id" />
<fmt:message bundle="${loc}" key="local.line.name" var="name" />
<fmt:message bundle="${loc}" key="local.line.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.line.email" var="email" />
<fmt:message bundle="${loc}" key="local.line.role" var="role" />

<fmt:message bundle="${loc}" key="local.messege.hello" var="hello" />

<fmt:message bundle="${loc}" key="local.button.findYourFlights" var="findYourFlights_button" />
<fmt:message bundle="${loc}" key="local.button.editProfile" var="editProfile_button" />
<fmt:message bundle="${loc}" key="local.button.yourOrders" var="yourOrders_button" />
<fmt:message bundle="${loc}" key="local.button.getAllUsers" var="getAllUsers_button" />
<fmt:message bundle="${loc}" key="local.button.getAllFlights" var="getAllFlights_button" />
<fmt:message bundle="${loc}" key="local.button.getOrders" var="getOrders_button" />
<fmt:message bundle="${loc}" key="local.button.addNewFlight" var="addNewFlight_button" />
<fmt:message bundle="${loc}" key="local.button.deleteFlight" var="deleteFlight_button" />

</head>
<body>
	<h1>${hello }</h1>

	<table cellspacing=3>
	
		<tr  bgcolor="paleturquoise">
			<td>${id}</td><td><c:out value="${user.id}" /></td>
		</tr>
		<tr  bgcolor="yellow">
			<td>${name}</td><td><c:out value="${user.name}" /></td>
		</tr>
		<tr  bgcolor="paleturquoise">
			<td>${surname }</td><td><c:out value="${user.surname}" /></td>
		</tr>
		<tr  bgcolor="yellow">
			<td>${email }</td><td><c:out value="${user.email}" /></td>
		</tr>
		<tr  bgcolor="paleturquoise">
			<td>${role }</td><td><c:out value="${user.role}" /></td>
		</tr>

	</table>

	<form action="controller" method="get">
		<input type="hidden" name="command" value="find_flight">
		<input type="submit" value="${findYourFlights_button }">
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_edit_user_profile">
		<input type="submit" value="${editProfile_button }">
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="get_report">
		<input type="submit" value="${yourOrders_button}">
	</form>
	
	<c:if test="${user.role eq 'admin' }">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="get_all_users">
			<input type="submit" value="${getAllUsers_button}">
		</form>
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_get_all_flights">
			<input type="submit" value="${getAllFlights_button }">
		</form>
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_get_all_orders">
			<input type="submit" value="${getOrders_button }">
		</form>
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_add_new_flight">
			<input type="submit" value="${addNewFlight_button}">
		</form>
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_delete_flight">
			<input type="submit" value="${deleteFlight_button }">
		</form>
	</c:if>

</body>
</html>