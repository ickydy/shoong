<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shoong</title>
<link href="<c:url value="/resource/style/style.css"/>" rel="stylesheet"/>
</head>
<body>
	<div class="align-center">
		<div class="header" style="border-bottom: 2px solid #444">
			<div style="display:flex; justify-content:right; align-items:flex-end;" class="align-center">
				<div style="flex:1;">
				<img alt="title" src="<c:url value="/resource/titleImage/title.png" />" style="width:200px;"/>
				</div>
				<a href="<c:url value="/private/friends"/>" class="mg-s">🙎‍♂️</a>
				<a href="<c:url value="/private/msg/receive"/>" class="mg-s">
					<c:choose>
						<c:when test="">📩</c:when>
						<c:otherwise>✉</c:otherwise>
					</c:choose>
				</a>
			</div>
		</div>
		<div class="container align-center">
			<div style="display: flex; justify-content:space-between; align-items:flex-start;">
				<div style="flex:3;">
				
				</div>
				<div style="flex:1;">
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>