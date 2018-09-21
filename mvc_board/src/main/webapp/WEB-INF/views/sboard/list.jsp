<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/header.jsp" %>
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
						 --%></select>
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
					<h3>SEARCH BOARD</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:forEach var="boardVo" items="${list}">
							<tr>
								<td>${boardVo.bno}</td>
								<td><a href="/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVo.bno}">${boardVo.title}</a></td>
								<td>${boardVo.writer}</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVo.regdate}" />
								</td>
								<td><span class="badge bg-red">${boardVo.viewcnt}</span></td>								
							</tr>
						</c:forEach>					
					</table>
				</div><!-- end-body -->
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
							</c:if>
							<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li <c:out value="${pageMaker.cri.page == i ? 'class=active' : ''}"/>><a href="list${pageMaker.makeSearch(i)}">${i}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
							</c:if>						
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div><!-- end - wrapper -->
<form id="pageForm">
	<input type="hidden" name="page" value="${pageMaker.cri.page}"/>
	<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
</form>
<script>
	var result = '${result}';
	if(result == 'SUCCESS') {
		alert("처리가 완료 되었습니다.");
	}
	
	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			location.href = "/sboard/list"
			+ "${pageMaker.makeQuery(1)}"
			+ "&searchType=" + $("select option:selected").val()
			+ "&keyword=" + $("#keywordInput").val();
		});
		
		$("#newBtn").click(function() {
			location.href = "/sboard/register";
		});
	});
</script>
<%@include file="../include/footer.jsp" %>