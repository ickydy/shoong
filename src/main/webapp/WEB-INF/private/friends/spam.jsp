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
			<h3>차단 목록</h3>
			<form action="<c:url value="/private/friends/spams"/>" method="post">
				<table>
					<c:forEach var="one" items="${spams }">
						<tr>
							<td><img alt="${one.user.avatar.alt }" src="<c:url value="${one.user.avatar.imgUrl }" />" style="width: 30px;"/></td>
							<td>${one.friendId }</td>
							<td>
								<button type="button" class="l-bt unspam" data-target="${one.friendId }">차단해제</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	<form id="unspamForm" style="display: none;" method="post" action="<c:url value="/private/friends/spams"/>">
		<input type="hidden" name="friendId" id="friendId"/>
	</form>
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
		
		// 친구 차단 해제 버튼
		let unspam = document.querySelectorAll(".unspam");
		if(unspam != null) {
			[...unspam].forEach(function(elm){
			 	elm.addEventListener("click",
					function(e) {
						let result = window.confirm("차단해제하시겠습니까?");
						if(result) {
							const friendId = e.target.dataset.target;
							const $unspamForm = document.querySelector("#unspamForm");
							$unspamForm.querySelector("#friendId").value = friendId;
							$unspamForm.submit();
						}
			});			
			});
		}

	</script>
</body>
</html>