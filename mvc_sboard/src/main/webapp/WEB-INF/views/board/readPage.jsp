<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

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
					<input type="button" id="MODIFY" class="btn btn-warning" value="MODIFY"/>
					<input type="button" id="DELETE" class="btn btn-danger" value="DELETE"/>
					<input type="button" id="LIST" class="btn btn-primary" value="LIST"/>
					<input type="button" id="REPLY" class="btn btn-default" value="REPLY"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">ADD NEW COMMENT</h3>
						</div>
						<div class="box-body">
							<div class="form-group">
								<label>WRITER</label>
								<input type="text" class="form-control" name="commentAuth" id="newCommentAuth" placeholder="USER"/>
							</div>
							<div class="form-group">
								<label>COMMENT TEXT</label>
								<input type="text" class="form-control" name="commentText" id="newCommentText" placeholder="CONTENT"/>
							</div>
							<div class="box-footer">
								<input type="button" class="btn btn-primary" id="commentAddBtn" value="ADD COMMENT"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">COMMENTS</h3>
						</div>
						<div class="box-body">
							<ul class="timeline">
								<li class="time-label" id="commentDiv">
									<span class="bg-green">COMMENT LIST</span>
								</li>
							</ul>
							<ul class="pagination"></ul>
						</div>
						<div id="modifyModal" class="modal modal-primary fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title"></h4>
									</div>
									<div class="modal-body">
										<p><input type="text" id="commentText" class="form-control"/></p>
									</div>
									<div class="modal-footer">
										<button type="button" id="commentModBtn" class="btn btn-info">MODIFY</button>
										<button type="button" id="commentDelBtn" class="btn btn-primary">DELETE</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div><!-- close wrapper  -->
<script id="template" type="text/x-handlebars-template">
	{{#each.}}
		<li class="commentLi" data-cno={{cno}}>
			<i class="fa fa-comments bg-blue"></i>
			<div class="timeline-item">
				<span class="time">
					<i class="fa fa-clock-o"></i>
					{{prettifyDate regdate}}
				</span>
				<h3 class="timeline-header"><strong>{{cno}}</strong> - {{commentAuth}}</h3>
				<div class="timeline-body">{{commentText}}</div>
				<div class="timeline-footer">
					<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
				</div>
			</div>
		</li>
	{{/each}}
</script>
<script>
	$(document).ready(function() {
		var formObj = $("#readForm");
		console.log(formObj);
		
		$("#MODIFY").on("click", function() {
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#DELETE").on("click", function() {
			formObj.attr("action", "/board/removePage");
			formObj.submit();
		});

		$("#LIST").on("click", function() {
			formObj.attr("action", "/board/listReply");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#REPLY").on("click", function() {
			formObj.attr("action", "/board/replyRegister");
			formObj.attr("method", "get");
			formObj.submit();
		});
	});
	
	Handlebars.registerHelper('prettifyDate', function(timeValue) {
		var dataObj = new Date(timeValue);
		var year = dataObj.getFullYear();
		var month = dataObj.getMonth() + 1;
		var date = dataObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	function getPage(pageInfo) {
		//pageInfo : /comments/bno/page
		$.getJSON(pageInfo, function(data) {
			printPage(data.list, $('#commentDiv'), $('#template'));
		});
	}
	
	var printPage = function(commentData, target, templateObj) {
		var template = Handlebars.compile(templateObj.html());
		var html = template(commentData);
		
		$('.commentLi').remove();
		target.after(html);
	}
	
	var bno = ${boardVo.bno};
	
	$('#commentDiv').on('click', function() {
		if($(".timeline li").size() > 1) {
			return;
		}
		
		var pageInfo = "/comments/" + bno + "/1";
		getPage(pageInfo);
	});
	
	$("#commentAddBtn").on("click",function() {
		var auth = $("#newCommentAuth").val();
		var text = $("#newCommentText").val();
		
		$.ajax({
			type: 'post',
			url : '/comments',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			dataType : "text",
			data : JSON.stringify({
				bno : bno,
				commentText : text,
				commentAuth : auth
			}),
			success : function(result) {
				if(result == "SUCCESS") {
					alert("등록 완료");
					commentPage = 1;
					
					var pageInfo = "/comments/" + bno + "/" + commentPage;
					getPage(pageInfo);
					
					$("#newCommentAuth").val("");
					$("#newCommentText").val("");
				}
			}
		});
	});
	
	$('.timeline').on('click', '.commentLi', function() {
		var comment = $(this);
		$(".modal-title").html(comment.attr("data-cno"));
		$("#commentText").val(comment.find('.timeline-body').text());
	});
</script>
<%@include file="../include/footer.jsp"%>






