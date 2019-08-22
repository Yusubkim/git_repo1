<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border-collapse: collapse;">
	<tr>
		<td colspan="3">
			<font size="5">${boardDTO.subject}</font>
		</td>
	</tr>
	<tr align="center">
		<td width="150">글번호 : ${boardDTO.seq}</td>
		<td width="150">작성자 : ${boardDTO.name}</td>
		<td width="150">조회수 : ${boardDTO.hit}</td>
	</tr>
	<tr>
		<td colspan="3" height="200" valign="top">
			<pre>${boardDTO.content}</pre>
		</td>
	</tr>
</table>
<input type="button" value="목록" 
	onclick="location.href='../board/boardList.jj?pg=${pg}'">

<c:if test="${memId.equals(boardDTO.id)}">
<input type="button" value="글수정" 
	onclick="location.href='../board/boardModifyForm.jj?seq=${seq}&pg=${pg}'">
<input type="button" value="글삭제" 
	onclick="location.href='../board/boardDelete.jj?seq=${seq}&pg=${pg}'">
</c:if>
</body>
</html>











