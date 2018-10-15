<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME PAGE</title>
</head>
<body>
	<h1>HOME PAGE ${result}</h1>
	<a href="/board/register">글쓰기</a>
	<a href="/board/listPage">전체글목록</a>
	<c:choose>
		<c:when test="${!empty login}">
			${login.uname}
			<a href="/user/logOut">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="/user/login">로그인</a>
		</c:otherwise>
	</c:choose>
</body>
</html>