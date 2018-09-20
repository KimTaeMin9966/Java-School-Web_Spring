<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<div class="box-body">
					<form id="modifyForm" method="post">
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" class="form-control" name="title" value="${boardVo.title}"/>
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea class="form-control" name="content" rows="3">${boardVo.content}</textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" class="form-control" readonly value="${boardVo.writer}"/>
						</div>
						<input type="hidden" name="bno" value="${boardVo.bno}"/>
					</form>
				</div>
				<div class="box-footer">
					<input type="button" id="btnSave"class="btn btn-warning" value="SAVE"/>
					<input type="button" id="btnCancel"class="btn" value="CANCEL"/>
				</div>
			</div>
		</div>
	</div>
</section>
</div><!-- close wrapper  -->
<script>
	$(document).ready(function() {
		var formObj = $("#modifyForm");
		console.log(formObj);
		
		$("#btnSave").on("click", function() {
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "post");
			formObj.submit();
		});
		
		$("btnCancel").on("click" ,function() {
			location.href="/board/listPage";
		});
	});
</script>
<%@include file="../include/footer.jsp"%>