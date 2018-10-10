<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jQuery-latest.min.js"></script>
</head>
<body>
	<h1>READ PAGE</h1>
	TITLE : ${boardVo.title}<br/>
	WRITER : ${boardVo.writer}<br/>
	content : ${boardVo.content}<br/>
	<form id="readForm">
		<input type="hidden" name="bno" value="${boardVo.bno}" />
		<input type="button" id="modifyBtn" value="MODIFY" />
		<input type="button" id="deleteBtn" value="DELETE" />
		<input type="button" id="listBtn" value="LIST" />
	</form>
	<script type="text/javascript">
		var formObj = $('#readForm');
		console.log(formObj);
		
		$("#modifyBtn").on("click", function() {
			formObj.attr("action", "/board/modify");
			formObj.submit();
		});
		
		$("#deleteBtn").on("click", function() {
			formObj.attr("action", "/board/delete");
			formObj.submit();
		});

		$("#listBtn").on("click", function() {
			location.href = "/board/listPage";
			/* formObj.attr("method", "get");
			formObj.submit(); */
		});
	</script>
</body>
</html>