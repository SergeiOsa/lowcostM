<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
</head>
<body>
 
 <form action="controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<p><strong>Registration </strong></p>
	  	<p><strong>Login:     </strong>
		<input type="text" name="login" value="" /></p>
		<p><strong>Password: </strong>
		<input type="password" name="pass" value=""/></p>
	  	<p><strong>Name:     </strong>
		<input type="text" name="name" value="" /></p>
	  	<p><strong>Surname:     </strong>
		<input type="text" name="surname" value="" /></p>
		<p><strong>Email:     </strong>
		<input type="text" name="email" value="" /></p>
		<input type="submit" value="Register" /><br/>
	</form>
</body>
</html>