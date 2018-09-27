<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3>SEARCH</h3>
				</div>
				<div class="box-body">
					<div class="col-lg-2">
						<select class="form-control" name="searchType">
							<option value="n" <c:out value="${cri.searchType == null ? 'selected' : ''}" />>
								---
							</option>
							<option value="t" <c:out value="${cri.searchType == 't' ? 'selected' : ''}" />>
								TITLE
							</option>
							<option value="c" <c:out value="${cri.searchType == 'c' ? 'selected' : ''}" />>
								CONTENT
							</option>
							<option value="w" <c:out value="${cri.searchType == 'w' ? 'selected' : ''}" />>
								WRITER
							</option>
							<option value="tc" <c:out value="${cri.searchType == 'tc' ? 'selected' : ''}" />>
								TITLE & CONTENT
							</option>
							<option value="cw" <c:out value="${cri.searchType == 'cw' ? 'selected' : ''}" />>
								CONTENT & WRITER
							</option>
							<option value="tcw" <c:out value="${cri.searchType == 'tcw' ? 'selected' : ''}" />>
								TITLE & CONTENT & WRITER
							</option>
						</select>
					</div>
					<div class="col-lg-3">
						<input type="text" id="keywordInput" name="keyword" class="form-control" value="${cri.keyword}" />
					</div>
					<div class="col-lg-3">
						<input id="searchBtn" type="button" class="btn btn-warning" value="SEARCH"/>
						<input id="newBtn" type="button" class="btn btn-primary" value="NEWBOARD"/>
					</div>
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3>LIST ALL</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>UPDATEDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:forEach var="reBoardVO" items="${list}">
							<tr>
								<td>${reBoardVO.bno}</td>
								<td>
									<c:if test="${reBoardVO.depth != 0}">
										<c:forEach var="i" begin="1" end="${reBoardVO.depth}">&nbsp;&nbsp;&nbsp;</c:forEach>
										▶
									</c:if>
									<a href="/replyboard/read?bno=${reBoardVO.bno}&searchType=${cri.searchType}&keyword=${cri.keyword}">${reBoardVO.title}</a>
								</td>
								<td>${reBoardVO.writer}</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${reBoardVO.regdate}" />
								</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${reBoardVO.updatedate}" />
								</td>
								<td>
									<span class="badge bg-red">${reBoardVO.viewcnt}</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div><!-- end-body -->
				<div class="box-footer">footer</div>
			</div>
		</div>
	</div>
</section>
</div><!-- end - wrapper -->
<script>
	var result = '${result}';
	if (result == 'SUCCESS') {
		alert("처리가 완료 되었습니다.");
	}
	
	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			location.href = "/replyboard/listShow?"
			+ "searchType=" + $("select option:selected").val()
			+ "&keyword=" + $("#keywordInput").val();
		});
		
		$("#newBtn").click(function() {
			location.href = "/replyboard/register";
		});
	});
</script>
<%@include file="../include/footer.jsp"%>