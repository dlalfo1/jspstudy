<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function() {
		$('.post').on('click', function () {
			var post_no = $(this).data('post_no') // 현재 클릭한 <ul>태그의 data속성
			location.href = '${contextPath}/detail.post?post_no=' + post_no;
		})
	})
</script>
</head>
<body>

	
	<a href="${contextPath}/write.post">새 포스트 작성</a>
	
	<hr>
	
	<div class="container">
		<c:forEach var="post" items="${posts}"> <%-- post객체가 담긴 ArryList의 이름이 posts임.  --%>
			<ul class="post" data-post_no="${post.post_no}">
				<li>포스트 번호 ${post.post_no}</li> <%-- 리스트 posts에서 하나씩 꺼내온걸 post라고 칭하기로 했으니 post에서 post_no을 꺼내오는 것이다. --%>
				<li>작성자 ${post.writer}</li>
				<li>제목 ${post.title}</li>
				<li>작성일 ${post.created_date}</li>	
			</ul>
		</c:forEach>
	</div>

</body>
</html>