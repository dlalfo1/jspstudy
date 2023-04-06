<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function () {
		$('.bbs').on('click', function(){ // 클릭하 하나의 bbs대상은 this라고 부른다. 이벤트 대상이 this이다.
								          // 이건 클릭이벤트니까 클릭한 대상이 this인 것.
			location.href = '${contextPath}/detail.do?bbsNo=' + $(this).data('bbs_no');
			// data-속성을 사용하기 위한 data() 전용메소드가 있다. 변수이름을 넣으면 저장한 값을 가져온다.
		})												
	})
</script>
</head>
<body>
	<div>
		<c:forEach var="bbs" items="${bbsList}">
			<div class="bbs" data-bbs_no="${bbs.bbsNo}"> <%-- data- 속성 : 어떠한 변수를 저장해주는 속성 --%>
				<span>${bbs.title}</span> <%-- jsp에 의해서 bbs.getTitle(); 코드로 돌아간다. --%>
				<span>...</span>
				<span>${bbs.createdDate}</span>
			</div>
		</c:forEach>
	</div>
	<div>
		<a href="${contextPath}/write.do">작성하러 가기</a>
	</div>
</body>
</html>