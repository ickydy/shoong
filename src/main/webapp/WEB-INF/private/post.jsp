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
		<div class="container" style="margin-top: 50px;">
			<div class="w80">
				<div style="display: flex; align-items: center;">
					<div style="flex:3; margin-bottom: 10px;" class="align-left mg-l">
						제목:${post.title }
					</div>
					<div style="display: flex; align-items: center; flex:1; margin-bottom: 10px;" class="align-left mg-l">
						<div style="flex:1;">
							<a href="<c:url value="/private/user/profile?id=${post.userId }"/>">작성자:${post.userId }</a>
						</div>
						<div style="flex:1;">
							조회수:${post.viewCount }
						</div>
					</div>
				</div>
				<div style="border:2px solid #444;" class="msg-area align-left pd-l">
					${post.contents }
				</div>
			</div>
			<div class="w80 mg-top-l">
					<table class="reply-table">
						<tr>
							<th>no</th>
							<th>내용</th>
							<th>작성자</th>
							<th>작성시간</th>
						</tr>
						<c:forEach var="one" items="${replys }">
							<tr>
								<td>${one.id }</td>
								<td>${one.contents }</td>
								<td>${one.userId }</td>
								<td>${one.writeAt }</td>
							</tr>
						</c:forEach>
					</table>
				<form action="<c:url value="/private/reply/add"/>" method="post" class="align-left">
					<input type="text" name="contents" style="width:90%; border:1px solid #444; border-radius: 2px"/>
					<input type="hidden" name="postId" value="${post.id }"/>
					<button type="submit" class="l-bt">등록</button>
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
		
	</script>
</body>
</html>