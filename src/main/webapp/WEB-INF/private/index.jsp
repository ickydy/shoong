<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<div class="header" style="border-bottom: 2px solid #444;">
			<div style="display: flex; justify-content: right; align-items: flex-end;" class="align-center">
				<div>
					<span style="cursor: pointer;" class="mg-s" id="openPopBt">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
  							<path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
						</svg>
					</span>
				</div>
				<div style="flex: 1;">
					<a href="<c:url value="/index"/>">
						<img alt="title" src="<c:url value="/resource/titleImage/title.png" />" style="width: 200px;"/>
					</a>
				</div>
				<div>
					<a href="<c:url value="/private/user/search"/>" class="mg-s">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
						  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
						</svg>
					</a>
					<a href="<c:url value="/private/friends"/>" class="mg-s">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16">
						  <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022ZM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0M6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816M4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0m3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4"/>
						</svg>					
					</a>
					<a href="<c:url value="/private/msg/receive"/>" class="mg-s">
						<c:choose>
							<c:when test="${not empty unreadMsg }">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope-plus" viewBox="0 0 16 16">
	  								<path d="M2 2a2 2 0 0 0-2 2v8.01A2 2 0 0 0 2 14h5.5a.5.5 0 0 0 0-1H2a1 1 0 0 1-.966-.741l5.64-3.471L8 9.583l7-4.2V8.5a.5.5 0 0 0 1 0V4a2 2 0 0 0-2-2zm3.708 6.208L1 11.105V5.383zM1 4.217V4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v.217l-7 4.2z"/>
	  								<path d="M16 12.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0m-3.5-2a.5.5 0 0 0-.5.5v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1a.5.5 0 0 0-.5-.5"/>
								</svg>
							</c:when>
							<c:otherwise>
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
  										<path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
									</svg>
							</c:otherwise>
						</c:choose>
					</a>
				</div>
			</div>
		</div>
		<div class="container align-center" style="position: relative;">
			<!-- 팝업창 안 부분 -->
			<div id="popup" class="popup-index" style="display: none">
				<%@ include file="/WEB-INF/view/popup.jsp" %>
			</div>
			<!-- end -->
			<div style="display: flex; justify-content: space-between; align-items: flex-start;">
				<div style="flex: 3;">
					<div style="display:flex; justify-content:space-between; align-items:center; border-bottom:2px solid #aaaaaa;">
						<div style="flex:1; min-height: 300px; border-right:2px solid #aaaaaa;">
							<h3><a href="<c:url value="/private/friends"/>" class="mg-s">친구 목록</a></h3>
							<c:forEach var="i" begin="0" end="2">
									<p class="f-l">
										<img alt="${friends[i].user.avatar.alt }" 
										src="<c:url value="${friends[i].user.avatar.imgUrl }" />" 
										style="width:30px;"/>
										<span><a href="<c:url value="/private/user/profile?id=${friends[i].friendId }"/>">${friends[i].friendId }</a></span>
									</p>
							</c:forEach>
						</div>
						<div style="flex:1; min-height: 300px;">
							<h3><a href="<c:url value="/private/friends"/>" class="mg-s">받은 요청</a></h3>
							<c:forEach var="i" begin="0" end="2">
								<c:if test="${receiveRequests[i] ne null }">
									<p class="f-l">
										<img alt="${receiveRequests[i].user.avatar.alt }" 
										src="<c:url value="${receiveRequests[i].user.avatar.imgUrl }" />" 
										style="width:30px;"/>
										<span><a href="<c:url value="/private/user/profile?id=${receiveRequests[i].userId }"/>">${receiveRequests[i].userId }</a></span>
										<button type="button" class="l-bt confirm">수락</button>
									</p>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div style="min-height: 300px;" class="w80">
						<h3><a href="<c:url value="/private/community"/>" class="mg-s">커뮤니티</a></h3>
						<table class="msg-table mg-top-m" id="table">
							<c:forEach var="i" begin="0" end="4">
								<tr>
									<td>${posts[i].id }</td>
									<td>${posts[i].userId == null ? '익명' : posts[i].userId }</td>
									<td>${posts[i].title }</td>
									<td><fmt:formatDate value="${posts[i].writeAt}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>${posts[i].viewCount }</td>
								</tr>
							</c:forEach>
							<c:if test="${empty posts }">
								<tr>
									<th colspan="5">텅~</th>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
				<div style="flex: 1; border-left: 2px solid #444; min-height: 600px;">
					<div class="user-info">
						<div style="display: flex; align-items: center; justify-content:space-around; " >
							<div class="mg-s">
								<img alt="userAvatar" src="<c:url value="${user.avatar.imgUrl }" />" style="width: 30px;"/>
							</div>
							<div style="text-align:left;">
								<a href="<c:url value="/private/profile"/>">${user.name }(님)</a>
							</div>
							<div style="flex:1; text-align:center;">
								<a href="<c:url value="/logout"/>">
									<span class="l-bt">로그아웃</span>
								</a>
							</div>
						</div>
						<div style="display: flex; align-items: center; justify-content:space-around; margin-top:20px;" >
							<div>
								<a href="<c:url value="/private/msg/receive"/>">받은메세지함</a>
							</div>
							<div>
								<span>/</span>
							</div>
							<div>
								<a href="<c:url value="/private/msg/send"/>">보낸메세지함</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<form id="confirmForm" style="display: none;" method="post" action="<c:url value="/private/friends/confirm"/>">
		<input type="hidden" name="friendId" id="friendId"/>
	</form>
	</div>
	<script>
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

		document.querySelector("#popup").addEventListener("click",
				function(e) {
					e.stopPropagation();
		});

		document.querySelector("#container").addEventListener("click",
				function(e) {
					const $popup = document.querySelector("#popup");

					$popup.style.display = 'none';

		});
		
		// 글상세페이지로 이동
		document.querySelector('#table').addEventListener('click', function (e) {
			if(e.target.tagName.toLowerCase() == 'td') {
				const value = e.target.parentNode.firstElementChild.firstChild.nodeValue;
				location.href = '<c:url value="/private/post?id="/>' + value;
			}
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
						$confirmForm.querySelector("#friendId").value = friendId;
						$confirmForm.submit();
						
					}
		});
			});
		}
	</script>
</body>
</html>