<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>All Users Page</title>
</head>
<body>
	<p>All users:</p>

	<c:if test="${isSuccessful eq true}">
		<table cellspacing=3>
			<tr bgcolor="paleturquoise">
				<td>Id</td>
				<td>Name</td>
				<td>Surname</td>
				<td>Role</td>
				<td>Email</td>
			</tr>
			<c:forEach var="user" items="${usersList}">
				<tr bgcolor="yellow">
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.surname}" /></td>
					<td><c:out value="${user.role}" /></td>
					<td><c:out value="${user.email}" /></td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
</body>
</html>