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
			<div style="display: flex; justify-content:space-between;">
				<div style="flex:1;">
					<h3>친구 목록</h3>
					<c:forEach var="one" items="${friends }">
						<p class="f-l">
							<img alt="${one.user.avatar.alt }" 
							src="<c:url value="${one.user.avatar.imgUrl }" />" 
							style="width:30px;"/>
							<span>${one.friendId }</span>
							<button type="button" id="spam">차단</button>
						</p>
					</c:forEach>
				</div>
				<div style="flex:1; border-left:2px solid #444;">
					<div style="min-height:250px; border-bottom:2px solid #444;">
						<h3>받은 요청</h3>
						<c:forEach var="one" items="${receiveRequests }">
							<p class="f-l">
								<img alt="${one.user.avatar.alt }" 
								src="<c:url value="${one.user.avatar.imgUrl }" />" 
								style="width:30px;"/>
								<span>${one.friendId }</span>
								<button type="button" id="confirm">수락</button>
							</p>
						</c:forEach>
					</div>
					<div style="min-height: 250px;">
						<h3>보낸 요청</h3>
						<c:forEach var="one" items="${sendRequests }">
							<p class="f-l">
								<img alt="${one.user.avatar.alt }" 
								src="<c:url value="${one.user.avatar.imgUrl }" />" 
								style="width:30px;"/>
								<span>${one.friendId }</span>
							</p>
						</c:forEach>
					</div>
				</div>
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
	
	// 친구요청 수락 버튼
	document.querySelector("#confirm").addEventListner("click",
			function(e) {
				const friendId = e.target.previousElementSibling.textContent;
				location.href = "<c:url value='/private/friends/confirm'/>?friendId=" + encodeURIComponent(friendId);
	});
	
	// 친구 차단 버튼
	document.querySelector("#spam").addEventListner("click",
			function(e) {
				let result = window.confirm("차단하시겠습니까?");
				if(result) {
					const friendId = e.target.previousElementSibling.textContent;
					location.href = "<c:url value='/private/friends/spam'/>?friendId=" + encodeURIComponent(friendId);
				}
	});
	
	</script>
</body>
</html>