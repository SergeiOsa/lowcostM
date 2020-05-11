<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>All orders Page</title>
</head>
<body>
	<p>All orders:</p>
	<!--  Подключить библиотеку jQuery -->
	<script src="js/jquery.min.js"></script>
	<!--  Подключить библиотеку moment -->
	<script src="js/moment-with-locales.min.js"></script>
	<!--  Подключить js-файл фреймворка Bootstrap 3 -->
	<script src="js/bootstrap.min.js"></script>
	<!--  Подключить js-файл библиотеки Bootstrap 3 DateTimePicker -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="get_all_orders">
			<strong>Date from: </strong> <input type="date" name="dateFrom"
				value="" /> 
			<strong>Date to: </strong> <input type="date"
				name="dateTo" value="" />
		<input type="submit" value="Get orders">
	</form>

	<c:if test="${isSuccessful eq true}">

		<table cellspacing=3>
			<tr bgcolor="paleturquoise">
				<td>Order Id</td>
				<td>Client Id</td>
				<td>Order Time</td>
			</tr>
			<c:forEach var="order" items="${ordersList}">
				<tr bgcolor="yellow">
					<td><c:out value="${order.orderId}" /></td>
					<td><c:out value="${order.clientId}" /></td>
					<td><c:out value="${order.orderTime}" /></td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
</body>
</html>