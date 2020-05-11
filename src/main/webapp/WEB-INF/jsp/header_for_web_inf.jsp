<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ОАО "ЛоуКостик"</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.lang" var="lang_button" />
<fmt:message bundle="${loc}" key="local.button.main" var="main_button" />
<fmt:message bundle="${loc}" key="local.button.login" var="login_button" />
<fmt:message bundle="${loc}" key="local.button.logout" var="logout_button" />

 <style>
   .layer1 {
    	margin-left: 70%; /* Отступ  */   
   }
   .layer2 {
   		margin-right: 10;
   }
  </style>
</head>
<body>
	<div class="layer1">

		<c:if test="${user eq null }" >
			<table cellspacing=10>
				<tr>
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="find_flight"/>
							<input type="submit" value="${main_button}"/>
						</form>
					</td>		
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="localization_web_inf"/>
							<c:if test="${local eq ru or local eq null}">
								<input type="hidden" name="local" value="en"/>	
							</c:if>
							<c:if test="${local eq en}">
								<input type="hidden" name="local" value="ru"/>	
							</c:if>
							<input type="hidden" name="path" value="${param.pageName }"/>
 							<input type="submit" value="${lang_button}"/>
						</form> 
					</td>
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="go_to_login"/>
							<input type="submit" value="${login_button}"/>
						</form>
					</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${user ne null }" >
			<table cellspacing=10>
				<tr>
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="find_flight"/>
							<input type="submit" value="${main_button}"/>
						</form>
					</td>	
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="localization_web_inf"/>
							<c:if test="${local eq ru or local eq null}">
								<input type="hidden" name="local" value="en"/>	
							</c:if>
							<c:if test="${local eq en}">
								<input type="hidden" name="local" value="ru"/>	
							</c:if>
							<input type="hidden" name="path" value="${param.pageName }"/>
 							<input type="submit" value="${lang_button}"/>
						</form> 
					</td>
					<td><form action="controller" method="post">
							<input type="hidden" name="command" value="go_to_auth_user" />
							<c:set var="buttonName" scope="page" value="${user.name } ${user.surname}" />
							<input type="submit" value="${buttonName}" />
						</form>
					</td>
					<td>
						<form action="controller" method="get">
							<input type="hidden" name="command" value="logout" />
							<input type="submit" value="${logout_button}" />
						</form>
					</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>