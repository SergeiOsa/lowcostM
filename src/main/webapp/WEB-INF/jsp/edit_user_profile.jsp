<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Profile</title>
</head>
<body>
	
	<p>C.A. ужасное оформление страниц у тебя</p>
	
	<table>
	
		<tr>
			<td>Name:</td><td><c:out value="${user.name }"/></td>
		</tr>
		<tr>
			<td>Surname:</td><td><c:out value="${user.surname }"/></td>
		</tr>
		<tr>
			<td>Email:</td><td><c:out value="${user.email }"/></td>
		</tr>
	
	</table>
	
	<form action="controller" method="post">
		<c:if test="${isSuccessful eq true}">
			<p><font size = "5" color = "green">Successful!</font></p>
		</c:if>
		<c:if test="${isSuccessful eq false}">
			<p><font size = "5" color = "red">Wrong password!</font></p>
		</c:if>
		<input type="hidden" name="command" value="edit_user_profile">
		<p>
			<strong>Name: </strong>
			<input type="text" name="name" value="Change name"/>	
		</p>
		<p>
			<strong>Surname: </strong>
			<input type="text" name="surname" value="Change surname"/>	
		</p>
		<p>
			<strong>Email: </strong>
			<input type="text" name="email" value="Change email"/>	
		</p>
		<input type="submit" value="Edit">
	</form>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_change_password">
		<input type="submit" value="Change password">
	</form>

</body>
</html>