<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login PAGE</h1>
	<form method="post" action="loginPost">
		<input type="text" name="uid" placeholder="USER ID" /><br/>
		<input type="password" name="upw" placeholder="USER PW" /><br/>
		<label>
			<input type="checkbox" name="useCookie" />
			Remember me
		</label>
		<input type="submit" />
	</form>
</body>
</html>