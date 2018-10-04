<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload Ajax</title>
<style type="text/css">
	.fileDrop {
		width: 100%;
		height: 200px;
		border: 1px solid blue;
	}
</style>
</head>
<body>
	<h1>Upload Ajax</h1>
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	
	<script type="text/javascript">
		$('.fileDrop').on('dragenter dragover', function(event) {
			event.preventDefault();
		});
		
		$('.fileDrop').on('drop', function(event) {
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
					var str = '';

					if(checkImageType(result)) {
						str = "<div>"
							+ "<a href='/displayFile?fileName=" + getImageLink(result) + "' target='_blank'>"
							+ "<img src='/displayFile?fileName=" + result + "'/>"
							+ "</a>"
							+ "<small data-src='" + result + "'>X</small>"
							+ "</div>";
					} else {
						str = "<div>"
							+ "<a href='/displayFile?fileName=" + result + "'>"
							+ getOriginalName(result)
							+ "</a>"
							+ "<small data-src='" + result + "'>X</small>"
							+ "</div>";
					}
					$(".uploadedList").append(str);
				}
			});
		});
		
		function getOriginalName(fileName) {
			if(checkImageType(fileName)) return;
			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
		}
		
		function checkImageType(fileName) {
			var pattern = /jpg|gif|png|jpeg/i
			return fileName.match(pattern);
		}
		
		function getImageLink(fileName) {
			if(!checkImageType(fileName)) return;
			
			var front = fileName.substr(0, 12);
			var end = fileName.substr(14);
			
			console.log(fileName);
			console.log(front + end);
			
			return front + end;
		}
		
		$('.uploadedList').on('click', 'small', function(event) {
			var target = $(this);

			$.ajax({
				type : "post",
				url : "/deleteFile",
				dataType : "text",
				data : { fileName : $(this).attr("data-src") },
				success : function(result) {
					if(result == 'deleted') { alert('작업 성공'); target.parent("div").remove(); }
				}
			});
		});
	</script>
</body>
</html>