<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
*** 메인 화면 ***<br>
<a href="../board/boardList.jj?pg=1">글목록</a><br>
<c:if test="${memId == null}">
	<a href="../member/writeForm.jj">회원가입</a><br>
	<a href="../member/loginForm.jj">로그인</a><br>
</c:if>

<c:if test="${memId != null}">
	<a href="../board/boardWriteForm.jj">글쓰기</a><br>
	<a href="../member/logout.jj">로그아웃</a><br>
	<a href="../member/modifyForm.jj">회원정보수정</a><br>
	<a href="../member/deleteForm.jj">회원탈퇴</a><br>
	<a href="../member/memberList.jj?pg=1">회원목록</a><br>
</c:if>
</body>
</html>












