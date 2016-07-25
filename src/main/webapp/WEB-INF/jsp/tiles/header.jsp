<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section id="container">
	<!-- **********************************************************************************************************************************************************
			TOP BAR CONTENT & NOTIFICATIONS
	*********************************************************************************************************************************************************** -->
	<!--header start-->
	<header class="header black-bg">
		<div class="sidebar-toggle-box">
			<div id="sidebar-box" name="sidebar-box" class="fa fa-bars tooltips" data-placement="right" data-original-title="메뉴 네비게이션"></div>
		</div>
		<!--logo start-->
		<a href="#" class="logo">
			<b>File2Compare</b>
		</a>
		<!--logo end-->

		<div class="top-menu">
			<ul class="nav pull-right top-menu">
				<li>
					<input type="button" id="logout" name="logout" class="logout" value="Logout" />
				</li>
			</ul>
<%-- 			<div class="pull-right user-name">${adminId} 님</div> --%>
		</div>
	</header>
	<!--header end-->

	<script type="text/javascript">
		$(document).ready(function() {
			$('#logout').click(function() {
				bootbox.confirm('로그아웃 하시겠습니까?', function(result){
					if(result) {
						document.location.href= "${pageContext.request.contextPath}/authenticate/logout";
					}
				});
			});
		});
	</script>
