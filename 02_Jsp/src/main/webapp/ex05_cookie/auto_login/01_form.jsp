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


<% 
	// 자동 로그인 확인
	// 쿠키에 session_id, login_id 있으면 자동 로그인을 해야한다.
	Cookie[] cookies = request.getCookies();
	if(cookies != null){ // 쿠키값이 비어있지 않다면
		outer:
		for(int i = 0; i < cookies.length; i++) { // 쿠기값을 순회한다.
			if(cookies[i].getName().equals("session_id")){ // 만약 session_id라는 쿠키값이 들어있다면 
				for(int j = 0; j < cookies.length; j++){ // 쿠키값을 또 순회하라
					if(cookies[j].getName().equals("login_id")){ // 만약 login_id라는 쿠키값이 들어있다면
						session.setAttribute("loginId", cookies[j].getValue()); // 쿠키에 저장된 사용자 아이디로 로그인 처리하기
						break outer; // 바깥쪽 for문을 끝낸다.
					}
				}
			}
		}
	}
%>

<% if(session.getAttribute("loginId") == null) { %>
		<div>
			<form action="02_login.jsp">
				<div><input type="text" id="id" name="id" placeholder="아이디"></div>
				<div><input type="password" name="pw" placeholder="비밀번호"></div>
				<div>
					<input type="checkbox" name="chk_auto_login" id="chk_auto_login">
					<label for="chk_auto_login">자동 로그인</label>
				</div>
				<div><button>로그인</button></div>
			</form>
		</div>
<%  } else { %>
		<div>
			<%=session.getAttribute("loginId")%>님 반갑습니다.
			<input type="button" value="로그아웃" onclick="fnLogout()">
		</div>
<% } %>	
	
<script>1
	function fnLogout() {
		location.href = '03_logout.jsp';
	}
</script>

	
</body>
</html>

