<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER BOARD</title>
</head>
<body>
	<h1>REGISTER BOARD</h1>
	<form action="register" method="post">
		<label>Title</label>
		<input type="text" name="title"><br/>
		<label>Content</label>
		<textarea cols="25" name="Content"></textarea><br/>
		<label>Writer</label>
		<input type="text" name="Writer"><br/>
		<input type="submit" value="확인" />
	</form>
</body>
</html>