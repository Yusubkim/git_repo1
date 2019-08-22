<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkIdClose() {
		opener.writeForm.id.value = "${id}";
		window.close();
		opener.writeForm.pwd.focus();
	}
	function isInsert() {
		if (!document.chkForm.id.value) {
			alert("아이디를 입력하세요!!");
			document.chkForm.id.focus();
		} else {
			document.chkForm.submit();
		}
	}
</script>
</head>
<body>
<form action="../member/checkId.jj" method="post" name="chkForm">
<c:if test="${name != null}">
	<p>${id}는 사용중입니다.</p>	
	<p>
		아이디 <input type="text" name="id">
		<input type="button" value="중복체크" onclick="isInsert()">
	</p>
</c:if>
<c:if test="${name == null}">
	<p>${id}는 사용 가능합니다.</p>
	<p><input type="button" value="사용" onclick="checkIdClose()"></p>
</c:if>
</form>
</body>
</html>









