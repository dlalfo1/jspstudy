<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<!-- request1에서 전달된 파라미터 : model, price -->
	
	<%
		request.setCharacterEncoding("UTF-8"); // 변수선없없이 바로 객체를 가져다 사용할 수 있다.
		String model = request.getParameter("model");
		Optional<String> opt = Optional.ofNullable(request.getParameter("price")); // 값이 null이 아닐 수도 있고, null일수도 있다.
		// 파라미터에 price가 없으면 값으로 0을 쓰겠다. 있으면 전달된 값 그대로 쓰겠다.
		// Optional은 null값은 잡을 수 있지만, 빈 문자열을 잡을 순 없다.
		int price = Integer.parseInt(opt.orElse("0"));
	%>
	
	<h1>모델 : <%=model%></h1>
	<h1>모델 : <%=price%></h1>

</body>
</html>