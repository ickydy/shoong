<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shoong</title>
<link href="<c:url value="/resource/style/style.css"/>" rel="stylesheet"/>
</head>
<body>
	<div class="align-center">
		<div class="header">
			<div style="display:flex; justify-content:right; align-items:center;" class="align-center">
				<a href="<c:url value="/login"/>" class="mg-l">로그인</a>
				<a href="<c:url value="/join"/>" class="mg-l">회원가입</a>
			</div>
		</div>
		<div class="container align-center">
			<div class="align-center">
				<h1 class="index-h">SHOONG</h1>
				<p class="index-p">shoong, 새로운 이야기와 만남이 기다리는 곳</p>
			</div>
		</div>
	</div>
</body>
</html>