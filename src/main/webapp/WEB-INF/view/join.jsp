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
				<form class="form-join" action="<c:url value="/join" />" method="post">
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
					<div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; width:100%;">
						<div class="mg-l">
							<label for="admin">
								<img alt="admin" src="<c:url value="/resource/avatars/admin.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="admin"/>
						</div>
						<div class="mg-l">
							<label for="a1">
								<img alt="a1" src="<c:url value="/resource/avatars/a1.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a1"/>
						</div>
						<div class="mg-l">
							<label for="a2">
								<img alt="a2" src="<c:url value="/resource/avatars/a2.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a2"/>
						</div>
						<div class="mg-l">
							<label for="a3">
								<img alt="a3" src="<c:url value="/resource/avatars/a3.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a3"/>
						</div>
						<div class="mg-l">
							<label for="a4">
								<img alt="a4" src="<c:url value="/resource/avatars/a4.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a4"/>
						</div>
						<div class="mg-l">
							<label for="a5">
								<img alt="a5" src="<c:url value="/resource/avatars/a5.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a5"/>
						</div>
						<div class="mg-l">
							<label for="a6">
								<img alt="a6" src="<c:url value="/resource/avatars/a6.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a6"/>
						</div>
						<div class="mg-l">
							<label for="a7">
								<img alt="a7" src="<c:url value="/resource/avatars/a7.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a7"/>
						</div>
						<div class="mg-l">
							<label for="a8">
								<img alt="a8" src="<c:url value="/resource/avatars/a8.png"/>" style="width:80px">
							</label>
							<input type="radio" name="avatarId" id="a8"/>
						</div>
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