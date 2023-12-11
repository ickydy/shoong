<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shoong</title>
<link href="<c:url value="/resource/style/style.css"/>" rel="stylesheet"/>
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
				<h3>${user.id }(님)의 정보</h3>
			</div>
			<div class="w460">
			<div>
				<c:if test="${!friend }">
					<form action="<c:url value="/private/friends/add"/>" method="post">
						<input type="hidden" name="friendId" value="${user.id }">
						<button type="submit" class="l-bt">친구요청</button>
					</form>
				</c:if>
			</div>
				<c:choose>
					<c:when test="${result }">
						<table class="profile-table">
							<tr>
								<th>아이디</th>
								<td> : ${user.id }</td>
							</tr>
							<tr>
								<th>생일</th>
								<td> : <fmt:formatDate value="${user.birth }" pattern="yyyy-MM-dd"/></td>
							</tr>
							<tr>
								<th>이름</th>
								<td> : ${user.name }</td>
							</tr>
							<tr>
								<th>국가</th>
								<td> : ${country.name }</td>
							</tr>
							<tr>
								<th>성별</th>
								<td> : 
									<c:choose>
										<c:when test="${user.gender eq 'M'}">남성</c:when>
										<c:when test="${user.gender eq 'W'}">여성</c:when>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>정보공개여부</th>
								<td> : 
									<c:choose>
										<c:when test="${user.openAccess eq '1'}">공개</c:when>
										<c:when test="${user.openAccess eq '0'}">비공개</c:when>
									</c:choose>
								</td>
							</tr>
						</table>
					</c:when>
					<c:when test="${!result }">
						<h1>비공개</h1>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	
	<script>
	
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
		
		document.querySelector("#goEdit").onclick = function(evt) {
			location.href = '<c:url value="/private/edit"/>';
		}
		
	</script>
</body>
</html>