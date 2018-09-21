<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception 발생</title>
</head>
<body>
	<h1>${exception.getMessage()}</h1>
	<ul>
		<c:forEach var="stack" items="${exception.getStackTrace()}">
			<li>${stack.toString()}</li>
		</c:forEach>
	</ul>
</body>
</html>