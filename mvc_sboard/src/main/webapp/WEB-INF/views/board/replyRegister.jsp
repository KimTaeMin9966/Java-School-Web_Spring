<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<!-- Main Content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REPLY REGISTER BOARD</h3>
				</div>
				<form id="registerForm" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="title">Title</label>
							<input type="text" name="title" class="form-control" placeholder="ENTER Title"/>						
						</div>
						<div class="form-group">
							<label for="content">content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="ENTER.. CONTENT"></textarea>						
						</div>
					</div>
					<div class="box-footer">
						<input type="hidden" name="bno" value="${boardVo.bno}" />
						<input type="hidden" name="origin" value="${boardVo.origin}" />
						<input type="hidden" name="depth" value="${boardVo.depth}" />
						<input type="hidden" name="seq" value="${boardVo.seq}" />
						<input type="hidden" name="page" value="${cri.page}" />
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
						<input type="hidden" name="searchType" value="${cri.searchType}" />
						<input type="hidden" name="keyword" value="${cri.keyword}" />
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
</div> <!-- end wrapper -->
<%@include file="../include/footer.jsp" %>