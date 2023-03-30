<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="04_request_parameter2.jsp">
		
		<%-- parameter : String으로 처리 --%>
		<input type="text" name="a" value="1">
		
		<%-- parameterValues : String[] 배열로 처리
		     대표적으로 휴대전화 입력란의 파라미터들은 같은 name을 가지고 파라미터로 넘어간다.
		 --%>
		<input type="text" name="b" value="010">
		<input type="text" name="b" value="1234">
		<input type="text" name="b" value="5678">
	
	
		<%-- 크기 비교 연산을 위한 파라미터 x, y --%>
		<input type="hidden" name="x" value="10">
		<input type="hidden" name="y" value="5">
		<button>전송</button>
		
	</form>

</body>
</html>