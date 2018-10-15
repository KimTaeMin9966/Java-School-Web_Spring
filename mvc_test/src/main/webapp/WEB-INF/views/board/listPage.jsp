<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>READ PAGE</title>
<script src="https://code.jquery.com/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
	var result = '${result}';
	if(result == 'SUCCESS') {
		alert("처리가 완료 되었습니다.");
	}
</script>
<style type="text/css">
	ul {
		list-style: none;
	}
	li {
		float: left;
	}
	a {
		text-decoration: none;
	}
	.pagination li a {
		padding: 3px;
	}
	.active a {
		border: 1px solid blue;
		background: blue;
		color: white;
	}
</style>
</head>
<body>
	<h1>Board List</h1>
	<button id="register">New Board</button>
	<h1>LIST PAGING</h1>
	<table class="table table-bordered" border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%-- <tr>
			<td>${boardVo.bno}</td>
			<td>${boardVo.title}</td>
			<td>${boardVo.writer}</td>
			<td>${boardVo.regdate}</td>
			<td>${boardVo.viewcnt}</td>
		</tr> --%>
		<c:forEach var="boardVo" items="${list}">
			<tr>
				<td>${boardVo.bno}</td>
				<td>
					<a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVo.bno}">
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
	<div class="box-footer">
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li>
						<a href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					<li <c:out value="${(pageMaker.cri.page == i) ? 'class=active' : ''}"/>>
						<a href="listPage${pageMaker.makeQuery(i)}">${i}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next}">
					<li>
						<a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$('#register').on('click', function() {
			location.href = '/board/register';
		});
	</script>
</body>
</html>