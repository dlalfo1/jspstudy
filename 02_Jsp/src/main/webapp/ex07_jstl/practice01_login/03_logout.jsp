<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// session에 저장된 모든 정보 지우기 (보통 로그인할 땐 이렇게 한다. 정보를 모두 초기화한다.)
		session.invalidate(); // 세션 초기화 코드(암기 필수!) invalidate : 무효화하다.
		
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "ex07_jstl/practice01_login/01_form.jsp");
	%>

</body>
</html>