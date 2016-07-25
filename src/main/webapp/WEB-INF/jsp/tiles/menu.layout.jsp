<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ko-KR">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title><tiles:insertAttribute name="title"/></title>
	<tiles:insertAttribute name="htmlHead"></tiles:insertAttribute>
</head>
<body>
	<div id="wrap">
		<div id='header'>
			<tiles:insertAttribute name="header" />
		</div>
		<div id="center">
			<div id="side">
				<tiles:insertAttribute name="sidebar" />
			</div>
			<div id='contents'>
				<section id="main-content">
					<section class="wrapper">
						<tiles:insertAttribute name="body" />
					</section>
				</section>
			</div>
		</div>
		<div id='footer'>
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>