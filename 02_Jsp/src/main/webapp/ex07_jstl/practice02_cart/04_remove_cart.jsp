<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- session에 저장된 cart 속성 지우기 --%>
	<c:remove var="cart" scope="session" />
	
	<%-- 장바구니 목록 확인하러 가기 --%>
	<c:redirect url="03_cart_list.jsp" />

</body>
</html>