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
	<div class="align-center" id="container">
		<div class="header">
			<div class="align-center menu-bar">
				<a href="<c:url value="/index"/>" class="mg-s">
					<img alt="title" src="<c:url value="/resource/titleImage/title.png" />" style="width: 100px;"/>
				</a>
				<span style="cursor: pointer;" class="mg-s" id="openPopBt">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
 						<path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
					</svg>
				</span>
			</div>
			<!-- 팝업창 안 부분 -->
			<div id="popup" class="popup" style="display: none">
				<%@ include file="/WEB-INF/view/popup.jsp" %>
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
		
		// 팝업창 띄우기
		document.querySelector("#openPopBt").addEventListener("click",
				function(e) {
	
					const $popup = document.querySelector("#popup");
					if ($popup.style.display == 'none') {
						$popup.style.display = 'block';
					} else {
						$popup.style.display = 'none';
					}
					e.stopPropagation();
		});

		// 버블링 막기
		document.querySelector("#popup").addEventListener("click",
				function(e) {
					e.stopPropagation();
		});

		// 팝업창 바깥 클릭시 꺼지도록
		document.querySelector("#container").addEventListener("click",
				function(e) {
					const $popup = document.querySelector("#popup");
	
					$popup.style.display = 'none';
	
		});
		
	</script>
</body>
</html>