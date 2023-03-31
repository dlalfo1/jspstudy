<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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

	<c:if test="${sessionScope.cart == null}">
		<div>장바구니가 비었습니다.</div>	
	</c:if>
	
	<c:if test="${sessionScope.cart != null}">
		<c:forEach var="element"  items="${sessionScope.cart}">
			<div>${element.item} ${element.itemCount}개</div>
		</c:forEach>	
	</c:if>
	
	<div>
		<input type="button" value="계속쇼핑하기" onclick="goShopping()">
		<input type="button" value="장바구니비우기" onclick="removeCart()">
	</div>
	<script>
		function goShopping(){
			location.href = '01_form.jsp';
		}
		function removeCart(){
			if(confirm('장바구니를 비울까요?')){
				location.href = '04_remove_cart.jsp';
			} else {
				alert('취소되었습니다.');
			}
		}
	</script>
	
</body>
</html>