<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style type="text/css">
	.popup {
		position: fixed;
	}
	.back {
		left: 0;
		top: 0;
		background-color: gray;
		opacity: 0.5;
		width: 100%;
		height: 100%;
		overflow: hidden;
		z-index: 1000;
	}
	.front {
		left: 0;
		top: 0;
		z-index: 1110;
		opacity: 1;
		margin: auto;
	}
	#popup_img {
		width: 700px;
		height: 500px;
	}
</style>
<div class="popup back" style="display: none"></div>
<div class="popup front" style="display: none">
	<a id="img" href=""><img id="popup_img" /></a>
</div>

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
					<div><hr/></div>
					<ul class="mailbox-attachments clearfix uploadedList"></ul>
					<c:if test="${!empty userInfo}">
						<c:if test="${userInfo.uno == boardVo.uno}">
							<input type="button" class="btn btn-warning" id="modifyBtn" value="MODIFY"/>
							<input type="button" class="btn btn-danger" id="deleteBtn" value="DELETE"/>
						</c:if>
					<input type="button" class="btn btn-default" id="replyBtn"value="REPLY"/>
					</c:if>
					<input type="button" class="btn btn-primary" id="listBtn" value="LIST"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<c:if test="${!empty userInfo}">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">ADD NEW COMMENT</h3>
							</div>
							<div class="box-body">
								<div class="form-group">
									<label>WRITER</label>
									<input type="text" class="form-control" name="commentAuth" id="newCommentAuth" placeholder="USER" value="${userInfo.uname}" readonly />
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
					</c:if>
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
									<span class="bg-green">COMMENT LIST<small id="commentCntSmall">[${boardVo.commentCnt}]</small></span>
								</li>
							</ul>
							<div class="text-center">
								<ul id="pagination" class="pagination pagination-sm no-margin"></ul>
							</div>
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
										<button type="button" id="commentDelBtn" class="btn btn-danger">DELETE</button>
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
					{{#isCheckAuth uno}}
						<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
					{{else}}
						댓글 작성자가 아닙니다.
					{{/isCheckAuth}}
				</div>
			</div>
		</li>
	{{/each}}
</script>
<script id="templateAttach" type="text/x-handlebars-template">
	<li data-src="{{fullName}}">
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name" target="_blank">{{fileName}}</a>
		</div>
	</li>
</script>
<script>
	$(document).ready(function() {
		var formObj = $("#readForm");
		console.log(formObj);
		
		$("#modifyBtn").on("click", function() {
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#deleteBtn").on("click", function() {
			var commentCnt = ${boardVo.commentCnt};
			
			if(commentCnt > 0) {
				alert('댓글이 달린 게시물을 삭제할수 없습니다.');
				return;
			}
			
			var arr = [];
			$('.uploadedList li').each(function(index) {
				arr.push($(this).attr('data-src'));
			});
			
			if(arr.length > 0) {
				$.post('/deleteAllFiles', {files : arr}, function(result) {
					alert(result);
				});
			}
			
			formObj.attr("action", "/board/removePage");
			formObj.submit();
		});

		$("#listBtn").on("click", function() {
			formObj.attr("action", "/board/listReply");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#replyBtn").on("click", function() {
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
	
	Handlebars.registerHelper('isCheckAuth', function(uno, options) {
		var userUno = '${userInfo.uno}';
		
		if(userUno == uno) { return options.fn(this); }
		else { return options.inverse(this); }
	});
	
	function getPage(pageInfo) {
		//pageInfo : /comments/bno/page
		$.getJSON(pageInfo, function(data) {
			printPage(data.list, $('#commentDiv'), $('#template'));
			//printPaging(data.pageMaker, $("#pagination"));
			$("#modifyModal").modal("hide");
			$("#commentCntSmall").html("[ " + data.pageMaker.totalCount + " ]");
		});
	}
	
	var printPage = function(commentData, target, templateObj) {
		var template = Handlebars.compile(templateObj.html());
		var html = template(commentData);
		
		//$('.commentLi').remove();
		//target.after(html);
		target.parent().append(html);
	}
	
	var printPaging = function(pageMaker, target) {
		var str = "";
		
		if(pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
		}
		
		for(var i = pageMaker.startPage, len = pageMaker.endPage; i<= len; i++) {
			var strClass = (pageMaker.cri.page == i) ? 'class=active' : '';
			str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
		}			
		
		if(pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1 ) + "'> >> </a></li>";
		}
		target.html(str);
	}
	
	var bno = ${boardVo.bno};
	var commentPage = 1;
	
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
		var uno = "${userInfo.uno}";
		
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
				commentAuth : auth,
				uno : uno
			}),
			success : function(result) {
				if(result == "SUCCESS") {
					alert("등록 완료");

					$("#newCommentText").val("");
					
					var pageInfo = "/comments/" + bno + "/" + commentPage;
					getPage(pageInfo);
				}
			}
		});
	});
	
	$('.timeline').on('click', '.commentLi', function() {
		var comment = $(this);
		$(".modal-title").html(comment.attr("data-cno"));
		$("#commentText").val(comment.find('.timeline-body').text());
	});
	
	$('#commentModBtn').on('click', function() {
		var cno = $(".modal-title").html();
		var commentText = $("#commentText").val();
		
		$.ajax({
			type: 'put',
			url : '/comments/' + cno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override": "PUT"
			},
			dataType : "text",
			data : JSON.stringify({
				commentText : commentText
			}),
			success : function(result){
				if(result == "SUCCESS") {
					alert("작업 성공");
					
					var pageInfo = "/comments/" + bno + "/" + commentPage;
					getPage(pageInfo);
				}
			}
		});
	});
	
	$('#commentDelBtn').on('click', function() {
		var cno = $(".modal-title").html();
		
		$.ajax({
			type: 'delete',
			url : '/comments/' + cno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override": "DELETE"
			},
			dataType : "text",
			data : JSON.stringify({
				commentText : commentText
			}),
			success : function(result){
				if(result == "SUCCESS") {
					alert("작업 성공");
					
					var pageInfo = "/comments/" + bno + "/" + commentPage;
					getPage(pageInfo);
				}
			}
		});
	});
	
	$(".pagination").on("click", "li a", function(event) {
		event.preventDefault();
		commentPage = $(this).attr("href");
		getPage("/comments/" + bno + "/" + commentPage);
	});
	
	$(window).scroll(function() {
		var dh = $(document).height();
		var wt = $(window).scrollTop();
		var wh = $(window).height();
		
		if((wt + wh) > (dh - 10)) {
			if($('.timeline li').size() <= 1) return;
			commentPage++;
			var pageInfo = "/comments/" + bno + "/" + commentPage;
			getPage(pageInfo);
		}
	});

	var temp = Handlebars.compile($('#templateAttach').html());
	$.getJSON("/board/getAttach/" + bno, function(list) {
		$(list).each(function() {
			var fileInfo = getFileInfo(this);
			var html = temp(fileInfo);
			$('.uploadedList').append(html);
		});
	});
	
	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event) {
		var fileLink = $(this).attr("href");
		if(checkImageType(fileLink)) {
			event.preventDefault();
			
			var imgTag = $('#popup_img');
			imgTag.attr('src', fileLink);
			
			imgTag.load(function() {
				var height = $(this).height();
				var width = $(this).width();
				
				$('.front').css({
					"top" : "50%",
					"left" : "50%",
					"margin-left" : -(width / 2),
					"margin-top" : -(height / 2)
				});
				$(".popup").show("slow");
			});
		}
	});
</script>
<%@include file="../include/footer.jsp"%>