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
			<div style="display: flex; justify-content:space-between;">
				<div>
					<a href="<c:url value="/private/friends/spams"/>">차단목록</a>
				</div>
				<div style="flex:1;">
					<h3>친구 목록</h3>
					<c:forEach var="one" items="${friends }">
						<p class="f-l">
							<img alt="${one.user.avatar.alt }" 
							src="<c:url value="${one.user.avatar.imgUrl }" />" 
							style="width:30px;"/>
							<span><a href="<c:url value="/private/user/profile?id=${one.friendId }"/>">${one.friendId }</a></span>
							<button type="button" class="l-bt spam">차단</button>
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
								<span><a href="<c:url value="/private/user/profile?id=${one.userId }"/>">${one.userId }</a></span>
								<button type="button" class="l-bt confirm">수락</button>
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
								<span><a href="<c:url value="/private/user/profile?id=${one.friendId }"/>">${one.friendId }</a></span>
							</p>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="spamForm" style="display: none;" method="post" action="<c:url value="/private/friends/spam"/>">
		<input type="hidden" name="friendId" id="freindId"/>
	</form>
	<form id="confirmForm" style="display: none;" method="post" action="<c:url value="/private/friends/confirm"/>">
		<input type="hidden" name="friendId" id="freindId"/>
	</form>
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
		let confirm = document.querySelectorAll(".confirm");
		if (confirm != null) {
			[...confirm].forEach(function(elm){
				elm.addEventListener("click",
						function(e) {
					let result = window.confirm("수락하시겠습니까?");
					if(result) {
						const friendId = e.target.previousElementSibling.textContent;
						// location.href = "<c:url value='/private/friends/confirm'/>?friendId=" + encodeURIComponent(friendId);
						const $confirmForm = document.querySelector("#confirmForm");
						$confirmForm.querySelector("#freindId").value = friendId;
						$confirmForm.submit();
						
					}
			});
			});
		}
		
		// 친구 차단 버튼
		let spam = document.querySelectorAll(".spam");
		if(spam != null) {
			[...spam].forEach(function(elm){
				elm.addEventListener("click",
					function(e) {
						let result = window.confirm("차단하시겠습니까?");
						if(result) {
							const friendId = e.target.previousElementSibling.textContent;
							// location.href = "<c:url value='/private/friends/spam'/>?friendId=" + encodeURIComponent(friendId);
							const $spamForm = document.querySelector("#spamForm");
							$spamForm.querySelector("#freindId").value = friendId;
							$spamForm.submit();
						}
			});			
			});
		}
		
	</script>
</body>
</html>