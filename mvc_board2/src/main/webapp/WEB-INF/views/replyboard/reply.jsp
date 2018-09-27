<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!-- Main Content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REPLY BOARD</h3>
				</div>
				<form id="replyForm" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="title">Title</label>
							<input type="text" name="title" class="form-control" placeholder="ENTER Title" />
						</div>
						<div class="form-group">
							<label for="content">content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="ENTER.. CONTENT"></textarea>
						</div>
						<div class="form-group">
							<label for="writer">WRITER</label>
							<input type="text" name="writer" class="form-control" placeholder="ENTER writer" />
						</div>
					</div>
					<input type="hidden" name="bno" value="${reBoardVO.bno}"/>
					<input type="hidden" name="origin" value="${reBoardVO.bno}"/>
					<input type="hidden" name="depth" value="${reBoardVO.depth + 4}"/>
					<input type="hidden" name="seq" value="${reBoardVO.seq + 4}"/>
				</form>
				<div class="box-footer">
					<input type="button" id="btnSave" class="btn btn-warning" value="SAVE"/>
					<input type="button" id="btnCancel" class="btn" value="CANCEL"/>
				</div>
			</div>
		</div>
	</div>
</section>
</div><!-- end wrapper -->
<script>
	$(document).ready(function() {
		var formObj = $("#replyForm");
		console.log(formObj);
		
		$("#btnSave").on("click", function() {
			formObj.attr("action", "/replyboard/reply");
			formObj.submit();
		});
		
		$("#btnCancel").on("click" ,function() {
			location.href = "/replyboard/listShow";
		});
	});
</script>
<%@include file="../include/footer.jsp"%>