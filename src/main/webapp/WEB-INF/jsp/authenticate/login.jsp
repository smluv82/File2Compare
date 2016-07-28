<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ko-KR">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>File2Compare 로그인</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.css"/>
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/bower-components/" --%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bower_components/bootbox.js/bootbox.js"></script>
</head>

<body>
	<div id="login-page">
		<div class="container">

<!-- 			<form class="form-login" method="post" id="loginFrm" name="loginFrm" action="/common/authenticate/process"> -->
			<form class="form-login" method="post" id="loginFrm" name="loginFrm" action="/authenticate/login/process">
				<h2 class="form-login-heading">File2Compare</h2>
				<div class="login-wrap">
					<input type="text" id="id" name="id" value="pCertPC1" class="form-control" placeholder="User ID" autofocus> <br>
					<input type="password" id="pwd" name="pwd" value="pCertPC!234" class="form-control" placeholder="Password"> <br>
<!-- 					<button class="btn btn-theme btn-block" href="javascript:login();" type="submit"> -->
					<button class="btn btn-theme btn-block" type="submit">
						<i class="fa fa-lock"></i> 로그인
					</button>
				</div>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>