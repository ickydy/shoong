<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메시지 전송</title>
<link href="<c:url value="/resource/style/style.css"/>" rel="stylesheet" />
<link href="<c:url value="/resource/style/style2.css"/>"
	rel="stylesheet" />


</head>
<body>
	<div class="align-center">
		<div class="header" style="border-bottom: 2px solid #444">
			<div
				style="display: flex; justify-content: right; align-items: flex-end;"
				class="align-center">
				<div style="flex: 1;">
					<a href="<c:url value="/index"/>"> <img alt="title"
						src="<c:url value="/resource/titleImage/title.png" />"
						style="width: 200px;" />
					</a>
				</div>
				<a href="<c:url value="/logout"/>" class="mg-s">로그아웃</a> <a
					href="<c:url value="/private/friends"/>" class="mg-s">🙎‍♂️</a> <a
					href="<c:url value="/private/msg/receive"/>" class="mg-s"> <c:choose>
						<c:when test="">📩</c:when>
						<c:otherwise>✉</c:otherwise>
					</c:choose>
				</a>
			</div>
		</div>
	</div>


	<div class="container" style="margin-top: 50px;">
		<form action="${pageContext.servletContext.contextPath}/private/msg"
			method="post">
			<div style="margin-bottom: 10px;">
				받는 사람:<input type="text" name="friendId" />
			</div>
			<div style="margin-bottom: 10px;">
				<textarea name="contents"
					style="width: 100%; height: 400px; resize: none; padding: 4px"
					placeholder="보낼 메시지를 작성해주세요.."></textarea>
			</div>
			<div style="text-align: right;">
				<button type="submit">보내기</button>
			</div>
		</form>
	</div>


	<div class="container" style="margin-top: 50px;">
		<c:forEach var="one" items="${friends}">
			<p>${one.user.name}/ ${one.user.gender}/ ${one.confirmAt}</p>
		</c:forEach>
	</div>

</body>
</html>








