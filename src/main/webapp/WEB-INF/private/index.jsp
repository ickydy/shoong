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
					<span style="cursor: pointer;" class="mg-s" id="openPopBt">⚙</span>
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
			<!-- end -->
			<div style="display: flex; justify-content: space-between; align-items: flex-start;">
				<div style="flex: 3;">
					그을씨이
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
	</script>
</body>
</html>