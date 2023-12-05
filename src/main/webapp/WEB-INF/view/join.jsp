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
	<div>
		<div class="header">
			<div style="display:flex; justify-content:right; align-items:center;" class="align-center">
				<a href="<c:url value="/login"/>" class="mg-l">로그인</a>
				<a href="<c:url value="/index"/>" class="mg-l">메인화면</a>
			</div>
		</div>
		<div class="container">
			<div class="w100 align-center">
				<h1>회원가입</h1>
			</div>
			<c:if test="${e ne null }">
				<div class="w100">
					<p class="e-msg">${e }</p>
				</div>
			</c:if>
			<div class="w460">
				<form class="form-join">
					<div class="mg-l">
						<input type="text" name="id" placeholder="아이디" required class="w100"/>
					</div>
					<div class="mg-l">
						<input type="password" name="password" placeholder="비밀번호" required class="w100"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>