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
	<div class="align-center">
		<div class="header">
			<div style="display:flex; justify-content:space-between; align-items:center; border-bottom:2px groove #969696;" class="align-center">
				<a href="<c:url value="/login"/>" class="mg-l">
					<img alt="title" src="<c:url value="/resource/titleImage/title.png" />" style="width:100px;"/>
				</a>
				<a href="<c:url value="/logout"/>" class="mg-l">로그아웃</a>
			</div>
		</div>
		<div class="container">
			<div>
				<h3>내 정보</h3>
			</div>
			<div>
				<table style="border :1px solid #444">
					<tr>
						<th>아이디</th>
						<td>${user.id }</td>
					</tr>
					<tr>
						<th>생일</th>
						<td><fmt:formatDate value="${user.birth }" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${user.name }</td>
					</tr>
					<tr>
						<th>국가</th>
						<td>${user.countryId }</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>${user.gender }</td>
					</tr>
					<tr>
						<th>정보공개여부</th>
						<td>${user.openAccess }</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>