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
					<a href="<c:url value="/private/friends"/>" class="mg-s">🙎‍♂️</a>
					<a href="<c:url value="/private/msg/receive"/>" class="mg-s">
						<c:choose>
							<c:when test="">&#x1F4EC;</c:when>
							<c:otherwise>&#x1F4EB;</c:otherwise>
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
					<div style="display:flex; justify-content:space-between; align-items:center;">
						<div style="flex:1; min-height: 300px;">
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
									<td>${posts[i].userId }</td>
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
								<span>${user.name }(님)</span>
							</div>
							<div style="flex:1; text-align:center;">
								<a href="<c:url value="/logout"/>">
									<span class="l-bt">로그아웃</span>
								</a>
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