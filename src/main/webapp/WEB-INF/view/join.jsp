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
				<div class="w460">
					<p class="e-msg mg-l">${e }</p>
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
						<select class="w100" name="countryId" required>
						<!-- foreach 돌려서 옵션 넣기 -->
							<option disabled selected>국가</option>
							<c:forEach var="one" items="${countries }">
								<option value="${one.id }">${one.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="mg-l">
						<select class="w100" name="gender" required>
							<option disabled selected>성별</option>
							<option value="M">남성</option>
							<option value="W">여성</option>
						</select>
					</div>
					<div class="mg-l">
						<select class="w100" name="openAccess" required>
							<option disabled selected>정보공개여부</option>
							<option value="0">공개</option>
							<option value="1">비공개</option>
						</select>
					</div>
					<div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; width:100%;">
						<div class="mg-l">
							<c:forEach var="one" items="${avatars }">
								<label for="${one.id }">
									<img alt="${one.alt }" src="<c:url value="${one.imgUrl }"/>" style="width:80px">
								</label>
								<input type="radio" name="avatarId" id="${one.id }" value="${one.id }" />
							</c:forEach>
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