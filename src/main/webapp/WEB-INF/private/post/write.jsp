<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<%@ include file="/WEB-INF/view/popup.jsp" %>
			</div>
		</div>
		<div class="container" style="margin-top: 50px;">
			<form action="<c:url value="/private/post/write" />" method="post">
				<div style="margin-bottom: 10px;" class="align-left mg-l">
					<input type="text" name="title"  placeholder="제목을 작성해주세요.." class="w100"/>
				</div>
				<div style="margin-bottom: 10px;">
					<textarea name="contents" placeholder="내용을 작성해주세요.." class="msg-area"></textarea>
				</div>
				<div style="text-align: right;">
					<button type="submit" class="l-bt">올리기</button>
				</div>
			</form>
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
	
		function showFriends() {
			if ( document.querySelector("#friends").style.display == 'none' ) {
				document.querySelector("#friends").style.display = 'block';
			} else {
				document.querySelector("#friends").style.display = 'none';
			}
		}
	</script>
</body>
</html>