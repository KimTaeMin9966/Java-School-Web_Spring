<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<%@include file="../include/header.jsp"%>
<style>
	.fileDrop {
		width: 80%;
		height: 100px;
		border: 1px solid gray;
		background-color: lightslategray;
		margin: auto;
	}
</style>
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
							<input type="text" name="writer" class="form-control"
							 readonly value="${boardVo.writer}"/>
						</div>
						<div class="form-group">
							<label>FILE DROP HERE</label>
							<div class="fileDrop"></div>
						</div>
						<input type="hidden" name="bno" value="${boardVo.bno}"/>
						<input type="hidden" name="page" value="${cri.page}"/>
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
						<input type="hidden" name="searchType" value="${cri.searchType}"/>
						<input type="hidden" name="keyword" value="${cri.keyword}"/>
					</form>
				</div>
				<div class="box-footer">
					<div><hr/></div>
					<ul class="mailbox-attachments clearfix uploadedList"></ul>
					<input type="button" id="btnSave"class="btn btn-warning" value="SAVE"/>
					<input type="button" id="btnCancel"class="btn" value="CANCEL"/>
				</div>
			</div>
		</div>
	</div>
</section>
</div> <!-- close wrapper  -->
<script id="template" type="text/x-handlebars-template">
	<li>
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
				<i class="fa fa-fw fa-remove"></i>
			</a>
		</div>
	</li>
</script>
<script id="templateAttach" type="text/x-handlebars-template">
	<li data-src="{{fullName}}">
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name" target="_blank">{{fileName}}</a>
			<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
				<i class="fa fa-fw fa-remove"></i>
			</a>
		</div>
	</li>
</script>
<script>
	var bno = ${boardVo.bno};
	var template = Handlebars.compile($("#template").html());
	
	$.getJSON("/board/getAttach/" + bno, function(list) {
		$(list).each(function() {
			var fileInfo = getFileInfo(this);
			var html = template(fileInfo);
			$('.uploadedList').append(html);
		});
	});

	$(".uploadedList").on("click", '.delBtn', function(event) {
		event.preventDefault();
		var fileLink = $(this).attr("href");
		var target = $(this);
		
		$.ajax({
			type : "post",
			url : "/deleteFile",
			dataType : "text",
			data : { fileName : fileLink },
			success : function(result) {
				if(result == 'deleted') { target.closest("li").remove(); }
			}
		});
	});
	
	$(".fileDrop").on("dragover dragenter", function(event) {
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event) {
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		var file = files[0];
		console.log(file);
		
		var formData = new FormData();
		formData.append('file', file);
		
		$.ajax({
			type : "post",
			url : "/uploadAjax",
			dataType : "text",
			data : formData,
			processData : false,
			contentType : false,
			success : function(result) {
				alert(result);
				var fileInfo = getFileInfo(result);
				var html = template(fileInfo);
				console.log(html);
				$(".uploadedList").append(html);
			}
		});
	});
	
	$(document).ready(function() {
		var formObj = $("#modifyForm");
		console.log(formObj);
		
		$("#btnSave").on("click", function() {
			var str = '';
			
			$('.uploadedList .delBtn').each(function(index) {
				str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr('href') + "' />";
			});
			formObj.append(str);
			
			formObj.attr("action", "/board/modifyPage");
			formObj.submit();
		});
		
		$("#btnCancel").on("click",function() {
			location.href = "/board/listReply?page=${cri.page}&"
					+ "perPageNum=${cri.perPageNum}&"
					+ "searchType=${cri.searchType}&"
					+ "keyword=${cri.keyword}";
		});
	});
</script>
<%@include file="../include/footer.jsp"%>