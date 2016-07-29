<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<%
	String url = (String)request.getAttribute("javax.servlet.forward.servlet_path");
	pageContext.setAttribute("pageUrl", url);
%>

<!-- **********************************************************************************************************************************************************
			MAIN SIDEBAR MENU
*********************************************************************************************************************************************************** -->
<!--sidebar start-->
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">
			<li class="sub-menu">
				<a id="main" href="${pageContext.request.contextPath}/jquery/compare/main">
					<i class="fa fa-tachometer"></i> <span>파일비교</span>
				</a>
			</li>

			<li class="sub-menu">
				<a id="main2" href="${pageContext.request.contextPath}/jquery/compare/makeFile">
					<i class="fa fa-bar-chart-o"></i> <span>비교파일 만들기</span>
				</a>
			</li>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->