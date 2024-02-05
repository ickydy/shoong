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
			<form action="<c:url value="/private/user/search"/>">
				<input type="text" name="keyword" placeholder="아이디 혹은 이름을 입력하세요" class="w460"/>
				<button type="submit">검색</button>
			</form>
		</div>
		<div class="w460 mg-top-l">
			<table class="w100 msg-table" id="table">
				<c:forEach var="one" items="${recommendedUsers }">
					<tr>
						<td><img alt="${one.avatar.alt }" src="<c:url value="${one.avatar.imgUrl }"/>" style="width:20px;"/></td>
						<td>${one.id }</td>
						<td>${one.name }</td>
						<td>
							<form action="<c:url value="/private/friends/add" />" method="post">
								<input type="hidden" name="friendId" value="${one.id }" />
								<button type="submit" class="l-bt">+친구요청</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="one" items="${searchUsers }">
					<tr>
						<td><img alt="${one.avatar.alt }" src="<c:url value="${one.avatar.imgUrl }"/>" style="width:20px;"/></td>
						<td>${one.id }</td>
						<td>${one.name }</td>
						<td>
							<form action="<c:url value="/private/friends/add" />" method="post">
								<input type="hidden" name="friendId" value="${one.id }" />
								<button type="submit" class="l-bt">+친구요청</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
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
		
		document.querySelector('#table').addEventListener('click', function (e) {
			if (e.target.tagName.toLowerCase() == 'td') {
				const value = e.target.parentNode.firstElementChild.nextElementSibling.textContent;
				location.href = '<c:url value="/private/user/profile?id=' + value + '"/>';
			}
		});
		
	</script>
</body>
</html>