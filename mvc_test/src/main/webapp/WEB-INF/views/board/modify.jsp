<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function listPage() {
		location.href = "/board/listPage";
	}
</script>
</head>
<body>
	<h1>MODIFY PAGE</h1>
	<form action="modifyAccept" method="post">
		TITLE : <input type="text" name="title" value="${boardVo.title}" /><br/>
		WRITER : <input type="text" readonly name="writer" value="${boardVo.writer}" /><br/>
		CONTENT : <textarea name="content">${boardVo.content}</textarea><br/>
		<input type="hidden" name="bno" value="${boardVo.bno}" />
		<input type="submit" />
		<input type="button" onclick="listPage();" value="LIST ALL" />
	</form>
</body>
</html>