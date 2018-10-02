<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST</title>
<style type="text/css">
	.pagination {
		width: 100%;
	}
	.pagination li {
		list-style: none;
		float: left;
		padding: 3px;
		border: 1px solid skyblue;
		margin: 3px;
	}
	.pagination li a {
		margin: 3px;
		text-decoration: none;
	}
</style>
</head>
<body>
	<div id="modDiv" style="display: none">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="commentText" />
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

	<ul class="pagination">
	
	</ul>
	
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script>
		var bno = 1;
		
		getPageList(bno);
		//getAllList();

		function getAllList() {
			$.getJSON("/comments/all/" + bno, function(data) {
				var str = "";
				$(data).each(function() {
					str += "<li data-cno='" + this.cno + "' class='commentLi'>"
						+ this.cno + ":" + this.commentText
						+ "<button>MODIFY</button>" + "</li>";
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
			
			$.ajax({
				type : 'POST',
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
			var commentText = comment.text();
			
			$('.modal-title').html(cno);
			$('#commentText').val(commentText);
			$('#modDiv').show("slow");
		});

		// 수정
		$("#commentModBtn").on("click", function() {
			var cno = $(".modal-title").html();
			var commentText = $("#commentText").val();
			
			$.ajax({
				type : 'PUT',
				url : '/comments/' + cno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				dataType : "text",
				data : JSON.stringify({commentText : commentText}),
				success : function(result) {
					if (result == "SUCCESS") {
						alert("처리 완료");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
		
		// 삭제
		$("#commentDelBtn").on("click", function() {
			var cno = $(".modal-title").html();
			
			$.ajax({
				type : 'DELETE',
				url : '/comments/' + cno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : "text",
				success : function(result) {
					if (result == "SUCCESS") {
						alert("처리 완료");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
		
		// CLOSE
		$("#closeBtn").on("click", function() {
			$("#modDiv").hide("slow");
		});
		
		// pageing 처리된 List
		function getPageList(page) {
			$.getJSON("/comments/" + bno + "/" + page, function(data) {
				console.log(data.list.length);
				var str = '';
				
				$(data.list).each(function() {
					str += "<li data-cno='" + this.cno + "' class='commentLi'>"
						+ this.cno + ":" + this.commentText
						+ "<button>MODIFY</button>" + "</li>";
				});
				$('#comments').html(str);
				printPage(data.pageMaker);
			});
		}
		
		function printPage(pageMaker) {
			var str = '';

			if(pageMaker.prev) {
				str+= "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
			}
			
			for(var i = pageMaker.startPage, len = pageMaker.endPage; i<= len; i++) {
				var strClass = (pageMaker.cri.page == i) ? 'class=active' : '';
				str+= "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
			}			
			
			if(pageMaker.next) {
				str+= "<li><a href='" + (pageMaker.endPage + 1 ) + "'> >> </a></li>";
			}
			
			$('.pagination').html(str);
		}
		
		var commentPage = 1;
		
		$(".pagination").on("click", "li a", function(event) {
			event.preventDefault();
			commentPage = $(this).attr("href");
			getPageList(commentPage);
		});
	</script>
</body>
</html>