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
									<a href="/replyboard/read?bno=${reBoardVO.bno}">${reBoardVO.title}</a>
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
</script>
<%@include file="../include/footer.jsp"%>