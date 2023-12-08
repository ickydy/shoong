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
		<div class="header" style="border-bottom: 2px solid #444;">
			<div style="display: flex; justify-content: right; align-items: flex-end;" class="align-center">
				<div>
					<span style="cursor: pointer;" id="openPopBt">⚙</span>
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
							<c:when test="">📩</c:when>
							<c:otherwise>✉</c:otherwise>
						</c:choose>
					</a>
				</div>
			</div>
		</div>
		<div class="container align-center" style="position: relative;">
			<div id="popup" class="popup" style="display: none">
				<div>
					<a href="<c:url value="/private/profile"/>">내정보</a>
				</div>
			</div>
			<div style="display: flex; justify-content: space-between; align-items: flex-start;">
				<div style="flex: 3;">
					그을씨이
				</div>
				<div style="flex: 1; border-left: 2px solid #444; min-height: 600px;">
					<div style="width:90%; margin:auto; padding: 20px; border:2px solid #828282; min-height:150px;">
						<div style="display: flex; align-items: center; justify-content:space-around; " >
							<div style="flex:3;">
								<img alt="userAvatar" src="<c:url value="${user.avatar.imgUrl }" />" style="width: 30px;"/>
							</div>
							<div style="flex:3; text-align:left;">
								<span>${user.name }(님)</span>
							</div>
							<div style="flex:4; text-align:center;">
								<a href="<c:url value="/logout"/>" class="mg-s">로그아웃</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		document.querySelector("#openPopBt").addEventListener("click",
				function(e) {

					const $popup = document.querySelector("#popup");
					console.log($popup.style.display);
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
	</script>
</body>
</html>