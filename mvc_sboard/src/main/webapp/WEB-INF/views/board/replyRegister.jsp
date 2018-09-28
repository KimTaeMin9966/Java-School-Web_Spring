<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

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
					</div>
					<div class="box-footer">
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</div>
				</form>
			</div>
		</div>
	</div>

</section>



</div> <!-- end wrapper -->
<%@include file="../include/footer.jsp" %>