<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다."); // 파라미터에 title값이 전달되지 않으면 환영합니다를 표시해준다.
%>

<title><%=title%></title>
<%--  request.getContextPath() == /02_Jsp 
	  jsp파일엔 이 주석을 사용하자. (오류방지)
--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>

</head>
<body>

	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>
	
	<hr>