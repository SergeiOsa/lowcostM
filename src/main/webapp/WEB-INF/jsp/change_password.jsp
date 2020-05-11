<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Change password page</title>
</head>
<body>

	<form action="controller" method="post" >
		<input type="hidden" name="command" value="change_password">
		<c:if test="${isSuccessful eq true}">
			<p><font size = "5" color = "green">Successful</font></p>
		</c:if>
		<c:if test="${isSuccessful eq false}">
			<p><font size = "5" color = "red">Check your password</font></p>
		</c:if>
		<p>
			<strong>Enter the current password: </strong>
			<input type="password" name="pass" value=""/>	
		</p>
		<c:if test="${wrongPass eq true}">
			<p><font size = "5" color = "red">Wrong new password</font></p>
		</c:if>
		<p>
			<strong>Enter the new password: </strong>
			<input type="password" name="new_pass" value=""/>	
		</p>
		<p>
			<strong>Enter the new password again: </strong>
			<input type="password" name="new_pass2" value=""/>	
		</p>
			<input type="submit" value="Change password">
	</form>

</body>
</html>