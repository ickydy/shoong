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
				<form class="form-join" action="<c:url value="/login" />" method="post">
					<div class="mg-l">
						<input type="text" name="id" placeholder="아이디" required class="w100"/>
					</div>
					<div class="mg-l">
						<input type="password" name="password" placeholder="비밀번호" required class="w100"/>
					</div>
					<div class="mg-l">
						<input type="date" name="birth" required class="w100"/>
					</div>
					<div class="mg-l">
						<input type="text" name="name" placeholder="이름" required class="w100"/>
					</div>
					<div class="mg-l">
						<select class="w100" name="country" required>
						<!-- foreach 돌려서 옵션 넣기 -->
							<option disabled selected>국가</option>
							<option>한국</option>
							<option>중국</option>
							<option>일본</option>
						</select>
					</div>
					<div class="mg-l">
						<select class="w100" name="gender" required>
							<option disabled selected>성별</option>
							<option>남성</option>
							<option>여성</option>
						</select>
					</div>
					<div class="mg-l">
						<select class="w100" name="openAccess" required>
							<option disabled selected>정보공개여부</option>
							<option>공개</option>
							<option>비공개</option>
						</select>
					</div>
					<div class="mg-l">
						<img alt="won" src="<c:url value="/resource/teamImg/won.png"/>" style="width:115px">
					</div>
					<div class="mg-l">
						<button class="w100 l-bt" >인증요청</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>