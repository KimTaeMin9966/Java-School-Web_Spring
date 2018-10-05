<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
	.fileDrop {
		width: 80%;
		height: 100px;
		border: 1px solid gray;
		background-color: lightslategray;
		margin: auto;
	}
</style>
<!-- Main Content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				
				<form id="registerForm" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="title">Title</label>
							<input type="text" name="title" 
								class="form-control" placeholder="ENTER Title"/>						
						</div>
						<div class="form-group">
							<label for="content">content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="ENTER.. CONTENT"></textarea>						
						</div>
						<div class="form-group">
							<label for="writer">WRITER</label>
							<input type="text" name="writer" 
								class="form-control" placeholder="ENTER writer"/>						
						</div>
						<div class="form-group">
							<label>FILE DROP HERE</label>
							<div class="fileDrop"></div>
						</div>
					</div>
					<div class="box-footer">
						<div><hr/></div>
						<ul class="mailbox-attachments clearfix uploadedList"></ul>
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
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
<script type="text/javascript">
	var template = Handlebars.compile($("#template").html());

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
</script>
</div> <!-- end wrapper -->
<%@include file="../include/footer.jsp" %>