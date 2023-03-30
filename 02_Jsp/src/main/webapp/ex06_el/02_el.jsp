<%@page import="ex06_el.Book"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	 <%--
		Jsp binding 우선 순위
		1. 같은 이름의 속성이 서로 다른 영역에 저장될  수 있다.
		    => 같은 이름이 호출되면 누가 호출될거냐 하는 문제가 발생할 수 있다.
		2. 접근 범위(Scope)가 좁을 수록 높은 우선 순위를 가진다.
             높음                                  낮음
             pageContext > request > session > application
		  => 다 다른데 저장되어있는 a를 부르면 pageContext의  a가 불린다.
		     다른 곳에 저장된 a는 부르는 방법이 따로 있다.
		3. 각 영역을 저장하는 표현언어(EL)내장 객체가 있다. (Expression Language)
		     1) pageContext : pageScope
		     2) request          : requestScope
		     3) session          : sessionScope
		     4) application    : applicationScope
		4. Jsp binding 영역에 저장된 값은 모두 EL로 표현할 수 있다.
		   => 일반 java변수는 EL로 표현할 수 없다.	 
	  --%>
	  
	  
	  <%
  		pageContext.setAttribute("a", 1);
  		request.setAttribute("a", 10);
  		session.setAttribute("a", 100);
  		application.setAttribute("a", 1000);	
	  %>
	  
	  <%-- 우선순위 확인 --%>
	  <%-- 우선순위에 의해서 가장 높은 우선순위인 pageContext에 저장된 1이 표시된다. --%>
	 
	  <h1>${a}</h1> 
	  
	  <%-- 모든 영역의 속성 a 확인 --%>
	  <h3>${pageScope.a}</h3>
	  <h3>${requestScope.a}</h3>
	  <h3>${sessionScope.a}</h3>
	  <h3>${applicationScope.a}</h3>
	  
	  
	  <%--
		    EL 연산자 (java 연산자를 가져다 쓰는 개념으로 이해하자)
			1. 산술 : + - * / (div) %(mod)
			2. 관계 : <(lt)  <= (le)  >(gt)  >=(ge) ==(eq)  !=(ne)
			3. 논리 : &&(and) ||(or) !(not)
			4. 조건 : (조건식) ? 참 : 거짓
			
			
	   --%>
	  
	  <%
	    // EL 사용을 위해서 pageContext에 저장한다.
	  	pageContext.setAttribute("x", 5);
	  	pageContext.setAttribute("y", 2);
	  %>
	  
	  <ul>
	  	<li>${x + y}</li>
	  	<li>${x - y}</li>
	  	<li>${x * y}</li>
	  	<li>${x div y}</li>
	  	<li>${x mod y}</li> 
	  </ul>
	  
	  <ul>
	  	<li>${x lt y}</li>
	  	<li>${x le y}</li>
	  	<li>${x gt y}</li>
	  	<li>${x ge y}</li>
	  	<li>${x eq y}</li>
	  	<li>${x ne y}</li>
	  </ul>
	  
	  <ul>
	  	<li>${x eq 5 and y eq 2}</li>
	  	<li>${x eq 6 or y eq 2}</li>
	  	<li>${not (x eq 5)}</li>
	  </ul>
	  
	<%-- Map 사용하기--%>
	<%
		Map<String, Object> book1 = new HashMap<>();
		book1.put("author", "생텍쥐베리");
		book1.put("title", "어린왕자");
		book1.put("price", 100000);
		pageContext.setAttribute("book1", book1); // EL 사용을 위해서 pageContext 저장한다.
	%>
	
	<ul>
		<li>저자 : ${book1.author}</li>
		<li>제목 : ${book1.title}</li>
		<li>가격 : ${book1.price}</li>
	</ul>
	
	<%-- 객체 사용하기 --%>
	<%
		Book book2 = new Book();
		book2.setAuthor("황순원");
		book2.setTitle("소나기");
		book2.setPrice(10000);
		pageContext.setAttribute("book2", book2);
	%>
	<ul>
		<li>저자 : ${book2.author}</li>
		<li>제목 : ${book2.title}</li>
		<li>가격 : ${book2.price}</li>
	</ul>
	 
</body>
</html>