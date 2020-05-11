<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/header_for_web_inf.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Delete Selected Page</title>
</head>
<body>
<c:if test="${isTrue eq true }">
<p>Flights removed</p>
</c:if>
</body>
</html>