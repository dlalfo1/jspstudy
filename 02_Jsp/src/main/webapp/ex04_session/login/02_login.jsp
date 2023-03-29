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
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	
			
		// 로그인 시나이로
		// id=admin, pw=1234인 경우에 로그인 성공
		
		// 여긴 java코드니까 id == "admin" 이런코드 안 됨.
		if(id.equals("admin") && pw.equals("1234")){
			
			// 로그인 한 회원 정보를 session에 보관하기
			session.setAttribute("loginId", id);	 // 세션유효시간이 지나면 사라진다.
			session.setMaxInactiveInterval(60 * 10); // 세션유효시간 : 600초(10분)	
													 // 일정시간의 지나면 세션에 저장된 기록이 지워진다. ex) 은행 로그인 유지 시간 : 10분
													 // 반대로 말하자면 10분동안은 세션에 정보가 저장되어있기 때문에 로그인이 풀리지 않는다.
													 // 새로고침해도 괜찮음.
	
		} // id, pw의 파라미터가 "admin", "1234"와 일치할 때 세션에 loginId 애트리뷰트를 저장해준다.
	
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "/ex04_session/login/01_form.jsp" );

	%>

</body>
</html>