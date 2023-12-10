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
				<span style="cursor: pointer;" id="openPopBt" class="mg-s">⚙</span>
			</div>
			<!-- 팝업창 안 부분 -->
			<div id="popup" class="popup" style="display: none">
				<div>
					<a href="<c:url value="/private/profile"/>">내정보</a>
				</div>
				<div>
					<a href="<c:url value="/private/msg"/>">메세지작성</a>
				</div>
				<div>
					<a href="<c:url value="/private/msg/receive"/>">받은메세지</a>
				</div>
				<div>
					<a href="<c:url value="/private/msg/send"/>">보낸메세지</a>
				</div>
			</div>
		</div>
		<div class="container">
			<div>
				<h3>내 정보</h3>
			</div>
			<div class="w460">
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
				<button type="button" class="l-bt" id="goEdit">정보수정</button>
			</div>
		</div>
	</div>
	
	<script>
	
		document.querySelector("#goEdit").onclick = function(evt) {
			location.href = '<c:url value="/private/edit"/>';
		}
		
	</script>
</body>
</html>