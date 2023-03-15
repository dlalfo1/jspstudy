package ex01_lifecycle;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	/*
		서블릿
		
		1. Servlet
		2. 웹 화면을 결과로 만드는 클래스이다.
		3. HttpServlet 클래스를 상속 받는다.
		4. Jsp/Servlet Container인 Tomcat에 의해서 실행된다. (Tomcat이 없으면 컴파일 오류 발생)
		5. 서블릿을 실행하면(Ctrl + F11) 웹 브라우저(Chrome)에서 결과가 표시된다.
	*/

	/*
	 	URL
		1. 구성
		       프로토콜://호스트:포트번호/ContextPath/URL Mapping
		2. HelloServlet의 URL
		        http://localhost:9090/01_Servlet/HelloServlet
	 
	 */

@WebServlet("/HelloServlet") // URLMapping
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /*   
	     1. 생성자
		     1) 가장 먼저 호출된다.
		     2) 호출 뒤 자동으로 init() 메소드가 호출된다.
     */
    public HelloServlet() {
        super();
        System.out.println("생성자 호출");
    }

	/*
		 2. init() 메소드
		     1) 서블릿 환경 설정을 담당한다.
		     2) init() 호출 뒤 자동으로 service() 메소드가 호출된다.
	 */
	public void init(ServletConfig config) throws ServletException {
		 System.out.println("init() 호출");
	}

	/*
		3. service()
	      1) 클라이언트가 요청할때마다 자동으로 호출된다.
	      2) service() 메소드가 없으면 doGet() 또는 doPost() 메소드가 자동으로 호출된다.
	      3) 클라이언트의 요청을 직접 처리할 수 있다.
	          ⇒ HttpServletRequest가 매개변수로 들어가있다.
	      4) 클라이언트의 요청을 직접 처리하지 않으려면 요처엥 따라 doGet() 또는
	          doPost()호출해야 한다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("service() 호출");
		
		// HTTP Method(요청 메소드)에 따른 doGet() 또는 doPost() 메소드 호출하기
		switch(request.getMethod()) { // request안의 사용자의 요청정보(Get 또는 Post)는 저장되어 있다.
		case "GET": 
			doGet(request, response); // 서비스 메소드가 처리하는 경우가 아닌 직접 처리하는 경우를 만드는거니까
			break;					  // 사용자의 요청을 doGet() 또는 doPost() 메소드에서 처리해야 한다.
		case "POST":
			doPost(request, response);
			break;
		}
		
	}

	/*
	 	4. doGet() 메소드 (메소드를 생략하면 GET방식이다.)
		     1) GET 방식의 요청을 처리하는 메소드이다.
		     2) GET 방식의 요청의 예시
		           (1) <a href=”http://localhost:9090/01_Servlet/HelloServlet”> - HTML
		                   ⇒ <a> 링크의 클릭은 100% GET방식이다.
		           (2) <form action=”http://localhost:9090/01_Servlet/HelloServlet”> - HTML
		           (3) location.href=’http://localhost:9090/01_Servlet/HelloServlet’> - javascript
		           (4) open(’http://localhost:9090/01_Servlet/HelloServlet’, ‘ ‘, ‘ ‘) - javascript
		           (5) $.ajax({
		                       type: ‘GET’,
		                       url: ’http://localhost:9090/01_Servlet/HelloServlet’
		                 }); - jQuery
		
		            ⇒ GET방식으로 요청하는 5가지 방법인데 어떤 요청을 하든 
		                 서블릿의 doGet()메소드가 받는다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답 정보를 가진 객체 : response
		
		// 클라이언트로 정보(텍스트)를 보내기 위한 출력 스트림 : response.getWriter() = printWriter
		
		// 출력 스트림으로 정보(텍스트)를 보내는 메소드 : append(), writer(), print(), println()
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		/*
		 	client <------ response ------- server(Servlet)
		 
	 		response.getWriter() : 서버가 HTML 코드를 작성해서 서버에 출력하는 메소드.(출력스트림)
	 							   PrintWriter 메소드와 같은 기능을 한다.
	 		append("Served at: ").append(request.getContextPath() : client로 Served at:01_Servlet을 보내주겠다.
		 */
	}

	/*
		5. doPost()
		    1) POST 방식의 요청을 처리하는 메소드이다.
		    2) POST 방식의 요청의 예시      
		           (1) <form method=”POST” action=”http://localhost:9090/01_Servlet/HelloServlet”> 
		                - HTML
		    (2)  $.ajax({
		                type: ‘POST’,
		                url: ’http://localhost:9090/01_Servlet/HelloServlet’
		           }); - jQuery
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST 메소드를 통해서 넘어온 정보를 모두 doGet() 메소드에 넘긴다.
		// 결국 일은 doGet() 메소드가 하는 것이다.
		
		doGet(request, response);
	}

}
