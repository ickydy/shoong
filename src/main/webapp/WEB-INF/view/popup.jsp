<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<h3>개인</h3>
	<a href="<c:url value="/private/profile"/>">내정보</a>
</div>
<div>
	<h3>친구</h3>
	<a href="<c:url value="/private/user/search"/>">유저검색</a>
	<span> / </span>
	<a href="<c:url value="/private/friends"/>">친구목록</a>
</div>
<div>
	<h3>메세지</h3>
	<a href="<c:url value="/private/msg"/>">메세지작성</a>
	<span> / </span>
	<a href="<c:url value="/private/msg/receive"/>">받은메세지</a>
	<span> / </span>
	<a href="<c:url value="/private/msg/send"/>">보낸메세지</a>
</div>
<div>
	<h3>커뮤니티</h3>
	<a href="<c:url value="/private/community"/>">커뮤니티</a>
	<span> / </span>
	<a href="<c:url value="/private/post/write"/>">글작성</a>
</div>
