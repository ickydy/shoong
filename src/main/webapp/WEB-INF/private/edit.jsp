<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shoong</title>
<link href="<c:url value="/resource/style/style.css"/>" rel="stylesheet" />
</head>
<body>
	<div class="align-center">
		<div class="header">
			<div class="align-center menu-bar">
				<a href="<c:url value="/index"/>" class="mg-l">
					<img alt="title" src="<c:url value="/resource/titleImage/title.png" />" style="width: 100px;"/>
				</a>
				<a href="<c:url value="/logout"/>" class="mg-l">로그아웃</a>
			</div>
		</div>
		<div class="container">
			<div>
				<h3>정보수정</h3>
			</div>
			<div class="w460">
				<form action="<c:url value="/private/edit" />" method="post">
					<table class="profile-table">
						<tr>
							<th>아이디</th>
							<td>${user.id }</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" value="${user.password }" name="password" class="align-center" /></td>
						</tr>
						<tr>
							<th>생일</th>
							<td><fmt:formatDate value="${user.birth }" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" value="${user.name }" name="name" class="align-center" /></td>
						</tr>
						<tr>
							<th>국가</th>
							<td>${country.name}</td>
						</tr>
						<tr>
							<th>성별</th>
							<td>
								<c:choose>
									<c:when test="${user.gender eq 'M'}">남성</c:when>
									<c:when test="${user.gender eq 'W'}">여성</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>정보공개여부</th>
							<td>
								<c:set var="access" value="${user.openAccess }" />
								<select class="align-center" name="openAccess">
									<option ${access eq 1 ? 'selected':'' } value="1">공개</option>
									<option ${access eq 0 ? 'selected':'' } value="0">비공개</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>아바타</th>
							<td>
								<div style="display: flex">
									<img src="<c:url value="${user.avatar.imgUrl }"/>" style="width: 40px;" id="avatarPreview"/>
									<c:set var="avatarId" value="${user.avatarId }" />
									<select id="avatarSelect" class="align-center" name="avatarId">
										<c:forEach var="avatar" items="${avatars }">
											<option ${avatar.id eq user.avatarId ? 'selected':'' } value="${avatar.id }" data-url="<c:url value="${avatar.imgUrl }"/>">
												${avatar.alt }
											</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
					</table>
					<button type="submit" class="l-bt">수정완료</button>
				</form>
			</div>
		</div>
	</div>
	<script>
		document.querySelector("#avatarSelect").onchange=function(evt ) {
			console.log(evt.target.options);
			const options = evt.target.options;
			console.log(options[options.selectedIndex].dataset.url);
			document.querySelector("#avatarPreview").src=options[options.selectedIndex].dataset.url;
			
		}
	</script>
</body>
</html>