<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			<div class="w460 mg-top-l align-left profile-table" style="line-height: 3">
				<div style="display:flex; justify-content:space-around; align-items: center; ">
					<div>보낸사람: ${message.userId }</div>
					<div>받는사람: ${message.friendId }</div>
				</div>
				<div><small>받은시간: <fmt:formatDate value="${message.writeAt }" pattern="yyyy-MM-dd HH:mm"/></small></div>
				<div class="profile-table" style="min-height: 400px;">${message.contents }</div>
				<form action="<c:url value="/private/msg/delete"/>" class="align-right pd-l" method="post">
					<input type="hidden" name="id" value="${message.id }" />
					<button type="submit" class="l-bt">삭제</button>
				</form>
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
		
		// 메세지 상세페이지로 이동
		document.querySelector('#table').addEventListener('click', function (e) {
			if(e.target.tagName.toLowerCase() == 'td') {
				const value = e.target.parentNode.firstElementChild.firstChild.nodeValue;
				location.href = '<c:url value="/private/msg/detail?id="/>' + value;
			}
		});
		
	</script>
</body>
</html>