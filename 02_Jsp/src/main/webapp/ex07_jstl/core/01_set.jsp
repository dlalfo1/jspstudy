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

	<%--
		<c:set>
		1. 속성(Attribute)을 만드는 태그이다.
		2. binding 영역(page(디폴트, 생략가능), request, session, application)을 지정할 수 있다.
		3. 형식
			1) <c:set var="속성명" value="값" scope="영역"></c:set>
			2) <c:set var="속성명" value="값" scope="영역" />
		4. 예시
			<c:set var="a" value="1" scope="page" />
			<c:set var="a" value="10" scope="request" />
			<c:set var="a" value="100" scope="session" />
			<c:set var="a" value="1000" scope="application" />
	--%>
	 
	<c:set var="age" value="46" scope="page" />
	<c:set var="isAlive" value="${age <= 100}" scope="page" />
	 <%-- value의 경우 EL문법을 사용해서 바로 연산하여 true 또는 false를 반환시킬 수 있다. --%>
	 
	 <%-- 
	 bmi = 몸무게(kg) / (키(m) * 키(m))
	 bmi < 20 : 저체중
	 bm1 < 25 : 정상
	 나머지 : 비만
	 --%>
	 
	<c:set var="height" value="1.76" scope="page" />
	<c:set var="weight" value="69" scope="page" />
	<c:set var="bmi" value="${weight div (height * height)}" scope="page" />
	<c:set var="health" value="${(bmi < 20) ? '저체중' : (bmi < 25) ? '정상' : '비만'}" scope="page" />
	 
	<ul>
		<li>나이 : ${age}살</li>
		<li>생존 : ${isAlive ? '살았다' : '죽었다'}</li>
		<li>bmi지수  : ${bmi}</li>
		<li>건강상태 : ${health}</li>
	</ul>

	
</body>
</html>