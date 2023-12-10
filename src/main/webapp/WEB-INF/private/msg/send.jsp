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
		<div class="container w720">
			<table class="msg-table mg-top-m" id="table">
				<tr>
					<th>NO</th>
					<th>읽음</th>
					<th>받는사람</th>
					<th>메세지</th>
					<th>시간</th>
				</tr>
				<c:forEach var="msg" items="${sendMessages }">
					<tr>
						<td>${msg.id }</td>
						<td>
							<c:choose>
								<c:when test="${msg.viewStatus eq 0 }">✉</c:when>
								<c:when test="${msg.viewStatus eq 1 }">📥</c:when>
							</c:choose>
						</td>
						<td>${msg.friendId }</td>
						<td>${fn:substring(msg.contents,0,10) }</td>
						<td><fmt:formatDate value="${msg.writeAt }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
				<c:if test="${empty sendMessages }">
					<tr>
						<th colspan="5">텅~</th>
					</tr>
				</c:if>
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