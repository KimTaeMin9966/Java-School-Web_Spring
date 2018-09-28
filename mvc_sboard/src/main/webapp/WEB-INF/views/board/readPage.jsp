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
						<input type="hidden" name="bno" value="${boardVo.bno}"/>
						<input type="hidden" name="page" value="${cri.page}"/>
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
						<input type="hidden" name="searchType" value="${cri.searchType}"/>
						<input type="hidden" name="keyword" value="${cri.keyword}"/>
					</form>
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" class="form-control" readonly value="${boardVo.title}"/>
					</div>
					<div class="form-group">
						<label>CONTENT</label>
						<textarea class="form-control" rows="3" readonly>${boardVo.content}</textarea>
					</div>
					<div class="form-group">
						<label>WRITER</label>
						<input type="text" class="form-control" readonly value="${boardVo.writer}"/>
					</div>
				</div>
				<div class="box-footer">
					<input type="button" class="btn btn-warning" value="MODIFY"/>
					<input type="button" class="btn btn-danger" value="DELETE"/>
					<input type="button" class="btn btn-primary" value="LIST"/>
					<input type="button" class="btn btn-default" value="REPLY"/>
				</div>
			</div>
		</div>
	</div>
</section>
</div> <!-- close wrapper  -->
<script>
	$(document).ready(function(){
		var formObj = $("#readForm");
		console.log(formObj);
		
		$(".btn-warning").on("click", function() {
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function() {
			formObj.attr("action", "/board/removePage");
			formObj.submit();
		});

		$(".btn-primary").on("click", function() {
			formObj.attr("action", "/board/listReply");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-default").on("click", function() {
			formObj.attr("action", "/board/replyRegister");
			formObj.attr("method", "get");
			formObj.submit();
		});
	});
</script>
<%@include file="../include/footer.jsp"%>






