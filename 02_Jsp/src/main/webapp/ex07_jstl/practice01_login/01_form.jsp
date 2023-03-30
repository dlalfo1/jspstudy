<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"></c:set>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

<!-- login.jsp에서 설정한 애트리뷰트가 있는지 확인한 후 로그인시키는걸 담당한다.  -->
<!-- loginId는 유일한 이름이기 때문에 sessionScope에서 부르지 않아도 되긴한다.  -->

	<c:choose>
		<c:when test="${sessionScope.loginId == null}"> 
			<div>
				<form method="post" action="${contextPath}/ex04_session/login/02_login.jsp">
					<div><input type="text" name="id" placeholder="아이디"></div>
					<div><input type="password" name="pw" placeholder="비밀번호"></div>
					<div><button>로그인</button></div>
				</form>
			</div>
		</c:when>
		<c:otherwise>
		<div>
			${loginId} 님 반갑습니다.
			<input type="button" value="로그아웃" id="btn_logout">
		</div>	
		</c:otherwise>
	</c:choose>	
	

	<script>
	
		$('#btn_logout').on('click', function(){
			location.href = '${contextPath}/ex07_jstl/practice01_login/login/03_logout.jsp';
		}) // 로그아웃은 03_logout.jsp 여기서 처리한다.
	
	</script>

			
</body>
</html>