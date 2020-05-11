<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Logination</title>
</head>
<body>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="logination" />
		<input type='hidden' name="local" value='en'/>
		<p><strong>Sing in </strong></p>
	  	<p><strong>Login:     </strong>
		<input type="text" name="login" value="" /></p>
		<p><strong>Password: </strong>
		<input type="password" name="pass" value=""/></p>
		<input type="submit" value="Sing in" /><br/>
		<c:if test="${user eq null }" >
		<c:out value="${loginErrorMessege }" />
		</c:if>
		
	</form>
	
	<p><a href="/lowcoastM/registration.jsp">don't have an account?</a></p>

</body>
</html>