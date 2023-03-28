<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 동적 include : 포함되는 페이지에  파라미터를 전달할 수있다. (jsp 액션 태그)
	 정적인 include 지시어 더 많은 작업이 수행 가능하다. 
--%>
<jsp:include page="header.jsp">
	<jsp:param value="body1" name="title"/>
</jsp:include>

<h1>body1</h1>
<script>
	$('h1').css('color', 'red'); // jquery 라이브러리의 동작 확인용
</script>

<%-- 정적 include : 항상 같은 모습의 페이지를 포함한다. (include 지시어) --%>
<%@ include file="footer.jsp" %>