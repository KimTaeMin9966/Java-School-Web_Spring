<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/dist/img/puppy.ico">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="/"><b>KOREATE PROJECT</b></a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">Sign Up</p>
			<form action="signUpPost" method="post">
				<div class="form-group has-feedback">
					<input type="text" name="uid" class="form-control" placeholder="USER ID"/>
					<span class="glypicon glypicon-evenlope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="upw" class="form-control" placeholder="USER PW"/>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="upw2" class="form-control" placeholder="RE PW"/>
				</div>
				<div class="form-group has-feedback">
					<input type="text" name="uname" class="form-control" placeholder="nick name"/>
				</div>
				<div class="row">
					<div class="col-xs-8">
					</div>
					<div class="col-xs-4">
						<input type="submit" class="btn btn-primary btn-block btn-flat" value="Sign Up" />
					</div>
				</div>
			</form>
		</div>
	</div>
    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>