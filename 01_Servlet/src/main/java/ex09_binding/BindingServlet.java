package ex09_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BindingServlet")
public class BindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
									// 아무 클래스나 request를 사용할 수 있는건 아니다. 사용자의 요청을 직접 받는 HttpServletRequest 클래스라 가능하다.
		/*
			1. stateless
			      1) 상태 없음
			      2) 웹 페이지간의 이동은 stateless한 이동이다.
			      3) 현재 페이지에는 이전 페이지의 정보가 없다.
			
			2. binding
			      1) 값을 저장할 수 있는 영역에 속성(Attribute)의 형태로 값을 저장하는 것을 말한다.
			      2) 저장 영역은 서버가 제공한다.
			      3) 3개 영역
			             (1) HttpServletRequest       :   하나의 요청 내에서 값을 저장할 수 있다. (1회용)
			             (2) HttpSession              :   웹 브라우저 종료 전까지 값을 저장할 수 있다. (시간 지정 가능)
			             (3) SevletContext            :   컨텍스트(프로젝트, 애플리케이션) 종료 전까지 값을 저장할 수 있다.
			      4) 속성(Attribute) 관련 메소드
			             (1) getAttribute(’속성’)     : 값 가져오기 (캐스팅이 필요할 수 있다.)
			             (2) setAttribute(’속성’, 값) : 값 저장하기 (Object 타입으로 저장한다.
			                   => Object에 저장시킨걸 꺼내올 때 캐스팅이 필요하다.
			             (3) removeAttribute(’속성’)  : 값 제거하기
		 */
		
		// HttpServletRequest에 저장하기
		request.setAttribute("a", 100);
		
		// HttpSession에 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("a", 200); // 저장소가 다르니까 위 코드와 같은 이름(a)으로 저장이 가능하다.
		
		// ServletContext에 저장하기
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("a", 300);
		
		// 페이지 이동하기
		
		// 1. HttpServletRequest의 전달이 없는 이동 : redirect, <a href="">, location.href=""
		// response.sendRedirect("/01_Servlet/BindingServlet2"); => 전달이있는 forward코드 실행위해 주석처리
		
		// 2. HttpServletRequest의 전달이 있는 이동 : forward
		// forward 요청시엔 ContextPath(프로젝트명)는 적으면 안 된다.
		request.getRequestDispatcher("/BindingServlet2").forward(request, response);
		// 애트리뷰트에 저장된 값 100, 200, 300이 BindingServlet2로 잘 전달된다. 
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}