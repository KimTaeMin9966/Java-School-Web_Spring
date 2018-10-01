<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST</title>
</head>
<body>
	<div id="modDiv" style="display: none">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="commetText" />
		</div>
		<div>
			<button type="button" id="commentModBtn">MODIFY</button>
			<button type="button" id="commentDelBtn">DELETE</button>
			<button type="button" id="closeBtn">CLOSE</button>
		</div>
	</div>
	<h2>Ajax Text Page</h2>
	<div>
		<div>
			Comment Auth <input type="text" name="commentAuth" id="newCommentAuth" />
		</div>
		<div>
			Comment Text <input type="text" name="commentText" id="newCommentText" />
		</div>
		<button id="commentAddBtn">ADD COMMENT</button>
		<button id="commentListBtn">ADD LIST</button>
	</div>

	<ul id="comments">

	</ul>

	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script>
		var bno = 1;

		//getAllList();

		function getAllList() {
			$.getJSON("/comments/all/" + bno, function(data) {
				var str = "";
				$(data).each(function() {
					str += "<li data-cno='"+this.cno+"' class='commentLi'>"
						+ this.cno + ":"
						+ this.commentText
						+ "<button>MODIFY</button>"
						+ "</li>";
				});
				$("#comments").html(str);
			});
		}

		$("#commentListBtn").on("click", function() {
			getAllList();
		});

		$("#commentAddBtn").on("click", function() {
			var commentText = $("#newCommentText").val();
			var commentAuth = $("#newCommentAuth").val();
			alert("commentText : " + commentText + " \ncommentAuth : " + commentAuth);

			$.ajax({
				type : 'post',
				url : '/comments',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				data : JSON.stringify({
					bno : bno,
					commentText : commentText,
					commentAuth : commentAuth
				}),
				success : function(result) {
					if (result == "SUCCESS") {
						alert("등록되었습니다.");
						getAllList();
					} else {
						alert(result);
					}
				}
			});
		});
		
		$("#comments").on("click", ".commentLi button", function() {
			var comment = $(this).parent();
			var cno = comment.attr('data-cno');
			alert('cno' + cno);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		});
	</script>
</body>
</html>