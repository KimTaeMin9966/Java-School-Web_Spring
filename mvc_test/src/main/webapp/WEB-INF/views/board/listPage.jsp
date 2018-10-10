<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
	var result = '${result}';
	if (result == 'SUCCESS') {
		alert("처리가 완료 되었습니다.");
	}
</script>
</head>
<body>
	<h1>Board List</h1>
	<button id="register">New Board</button>
	<h1>LIST PAGING</h1>
	<table class="table table-bordered" border="1">
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<c:forEach var="boardVo" items="${list}">
			<tr>
				<td>${boardVo.bno}</td>
				<td>
					<a href="/board/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVo.bno}">
						${boardVo.title}
					</a>
				</td>
				<td>${boardVo.writer}</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVo.regdate}" />
				</td>
				<td>
					<span class="badge bg-red">${boardVo.viewcnt}</span>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		$('#register').on('click', function() {
			location.href = '/board/register';
		});
	</script>
</body>
</html>