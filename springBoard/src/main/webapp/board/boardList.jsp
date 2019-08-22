<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<script type="text/javascript">
	function isLogin(seq) {
		if (${memId == null}) {
			alert("먼저 로그인 하세요.");
			location.href="../main/index.jsp";
		} else {
			location.href="boardView.jj?seq=" + seq + "&pg=${pg}";
		}
	}
</script>

<style type="text/css">
#paging {
	color: blue;
	text-decoration: none;	
}
#currentPaging {
	color: red;
	text-decoration: underline;
}

#subjectA:link {color: black; text-decoration: none;}
#subjectA:visited {color: black; text-decoration: none;}
#subjectA:achive {color: black; text-decoration: none;}
#subjectA:hover {color: blue; text-decoration: underline;}
</style>
</head>
<body>
<table border="1">
	<tr bgcolor="#ffff00">
		<th width="70">글번호</th>
		<th width="200">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="70">조회수</th>
	</tr>
<c:forEach var="boardDTO" items="${list}">
	<tr bgcolor="#ffffcc" align="center">
		<td>${boardDTO.seq}</td>
		<td><a id="subjectA" href="#" onclick="isLogin(${boardDTO.seq})">
			${boardDTO.subject}
			</a>
		</td>
		<td>${boardDTO.name}</td>
		<td>${boardDTO.logtime}</td>
		<td>${boardDTO.hit}</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5" align="center">
		<c:if test="${startPage > 3}">
			[<a id="paging" href="boardList.jj?pg=${startPage-1}">이전</a>]
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${pg==i}">
				[<a id="currentPaging" href="boardList.jj?pg=${i}">${i}</a>]
			</c:if>
			<c:if test="${pg!=i}">
				[<a id="paging" href="boardList.jj?pg=${i}">${i}</a>]	
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage < totalP}">
			[<a id="paging" href="boardList.jj?pg=${endPage+1}">다음</a>]
		</c:if>
		</td>
	</tr>
</table>
<a href="../main/index.jsp">메인 화면</a>&nbsp;&nbsp;
<a href="../board/boardWriteForm.jj">글쓰기</a>
</body>
</html>











