<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

<!-- login.jsp에서 설정한 애트리뷰트가 있는지 확인한 후 로그인시키는걸 담당한다.  -->
	<% if(session.getAttribute("loginId") == null) { %>
	
	
		<div>
			<form method="post" action="<%=request.getContextPath()%>/ex04_session/login/02_login.jsp">
				<div><input type="text" name="id" placeholder="아이디"></div>
				<div><input type="password" name="pw" placeholder="비밀번호"></div>
				<div><button>로그인</button></div>
			</form>
		</div>
		
	<% } else { %>
	
		<div>
			<%=session.getAttribute("loginId")%> 님 반갑습니다.
			<input type="button" value="로그아웃" id="btn_logout">
		</div>	
	<% } %>
	
	<script>
	
		$('#btn_logout').on('click', function(){
			location.href = '<%=request.getContextPath()%>/ex04_session/login/03_logout.jsp';
		}) // 로그아웃은 03_logout.jsp 여기서 처리한다.
	
	</script>
	
	
	
	
	
	
	
			
</body>
</html>