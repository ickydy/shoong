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
				<%@ include file="/WEB-INF/view/popup.jsp" %>
			</div>
		</div>
			<div class="container w720">
				<div style="display: flex; justify-content:space-between; align-content: center;" class="align-left">
					<div style="padding-left: 30px;">
						<form action="<c:url value="/private/community"/>">
							<select name="sort">
								<option value="date">날짜순</option>
								<option value="viewCount">인기순</option>
							</select>
							<button type="submit" class="l-bt">정렬</button>
						</form>
					</div>
					<div style="padding-right: 30px;">
						<a href="<c:url value="/private/post/write"/>" class="l-bt">글작성</a>
					</div>
				</div>
				<div style="min-height:400px;">
					<table class="msg-table mg-top-m" id="table">
						<tr>
							<th>NO</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성시간</th>
							<th>조회수</th>
						</tr>
						<c:forEach var="post" items="${posts }">
							<tr>
								<td>${post.id }</td>
								<td>${post.userId }</td>
								<td>${post.title }</td>
								<td><fmt:formatDate value="${post.writeAt}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${post.viewCount }</td>
							</tr>
						</c:forEach>
						<c:if test="${empty posts }">
							<tr>
								<th colspan="5">텅~</th>
							</tr>
						</c:if>
					</table>
				</div>
				<div>
					<form action="<c:url value="/private/community"/>">
						<select name="search">
							<option value="title">제목</option>
							<option value="titleWithContent">제목+내용</option>
						</select>
						<input type="text" name="keyword"/>
						<button type="submit" class="l-bt">검색</button>
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
		
		// 글상세페이지로 이동
		document.querySelector('#table').addEventListener('click', function (e) {
			if(e.target.tagName.toLowerCase() == 'td') {
				const value = e.target.parentNode.firstElementChild.firstChild.nodeValue;
				location.href = '<c:url value="/private/post?id="/>' + value;
			}
		});

	</script>
</body>
</html>