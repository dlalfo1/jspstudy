<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="str" value="Hello World" scope="page"></c:set>
	
	<h3>${fn:length(str)}</h3>
	<h3>${fn:substring(str, 0, 5)}</h3> <%-- 인덱스 0부터 인덱스 5 전까지 가져오기 --%> 
	<h3>${fn:substringBefore(str, 'W')}</h3> <%-- W 이전의 글자들 가져오기  --%>
	<h3>${fn:substringAfter(str, 'W')}</h3> <%-- W 다음부터 가져오기 --%>
	
	<h3>${fn:indexOf(str, 'l')}</h3> <%-- 첫번째 l의 인덱스를 반환한다. lastindxOf는 없다. --%>
	<h3>${fn:replace(str, 'l', 'z')}</h3> <%-- replace는 전부 바꾼다. 하나만 바꾸는건 자스의 replace가 있다. --%>
	
	<c:set var="str2" value="<script>location.href='/';</script>" scope="page"/>
	
	<h3>${fn:escapeXml(str2)}</h3>	<%-- 사용자가 스크립트 조작하는걸 막기위한 펑션 --%>
	
	<c:if test="${fn:startsWith(str, 'Hello')}">
		<h3>Hello로 시작한다.</h3>
	</c:if>
	<c:if test="${fn:endsWith(str, 'World')}">
		<h3>World로 끝난다.</h3>
	</c:if>
	<c:if test="${fn:contains(str, 'h')}">
		<h3>h를 포함한다.</h3>
	</c:if>
	<c:if test="${fn:containsIgnoreCase(str, 'h')}">
		<h3>H, h를 포함한다.</h3>
	</c:if>

	<c:set var="str3" value="a,b,c,d,e,f,z,h,i,j,k" scope="page" />
	<c:set var="items" value="${fn:split(str3, ',')}" scope="page"/> <%-- , 컴마로 구분해서 배열로 담아달라 --%>
	<c:forEach var="item" items="${items}" varStatus="vs">
		<div>${vs.index} - ${item}</div>
	</c:forEach>   
	
	<c:set var="str4" value="${fn:join(items, ',')}" scope="page" /> <%-- 배열에 담긴거 다시 원상복구	 --%>
	<h3>${str4}</h3>

</body>
</html>