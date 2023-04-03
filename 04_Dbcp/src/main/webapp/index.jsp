<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 
	<a href="getAllBoardList.do">게시판</a>
	<a href="<%=request.getContextPath()%>getAllBoardList.do">게시판</a>
	
	<%
		pageContext.setAttribute("contextPath", request.getContextPath());
	%>
	 EL문법을 사용하려면 저장소 한곳엔 무조건 속성을 저장해야한다.
		 위 코드에선 pageContext 저장소에 저장한것이다.
		 하지만 이것또한 자바스크립트 요소를 사용하고 있기때문에 JSTL태그 문법을 사용한다.
--%>	 
	
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />
	 <a href="${contextPath}/getAllBoardList.do">게시판</a>
	 
	 <a href="<c:url value="/getAllBoardList.do" />">게시판</a>
	 	
</body>
</html>