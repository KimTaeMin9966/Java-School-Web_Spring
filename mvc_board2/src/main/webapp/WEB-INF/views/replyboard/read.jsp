<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<div class="box-body">
					<form id="readForm" method="post">
						<input type="hidden" name="bno" value="${reBoardVO.bno}"/>
					</form>
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" class="form-control" readonly value="${reBoardVO.title}"/>
					</div>
					<div class="form-group">
						<label>CONTENT</label>
						<textarea class="form-control" rows="3" readonly>${reBoardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label>WRITER</label>
						<input type="text" class="form-control" readonly value="${reBoardVO.writer}"/>
					</div>
				</div>
				<div class="box-footer">
					<input type="button" class="btn" value="MODIFY"/>
					<input type="button" class="btn btn-warning" value="REPLY"/>
					<input type="button" class="btn btn-danger" value="DELETE"/>
					<input type="button" class="btn btn-primary" value="LIST"/>
				</div>
			</div>
		</div>
	</div>
</section>
</div><!-- close wrapper  -->
<script>
	$(document).ready(function() {
		var formObj = $("#readForm");
		console.log(formObj);

		$(".btn:first-child").on("click", function() {
			formObj.attr("action", "/replyboard/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-warning").on("click", function() {
			formObj.attr("action", "/replyboard/reply");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function() {
			formObj.attr("action", "/replyboard/remove");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function() {
			location.href = "/replyboard/listShow";
		});
	});
</script>
<%@include file="../include/footer.jsp"%>






