package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이전(첫 번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet 1 : " + model);
		
		// 포워드(전달) : request에서 시작한다.
		request.getRequestDispatcher("/ForwardServlet2").forward(request, response);
		// 이미 /01_Servlet안에서 움직이는것이기 때문에 주소는 서블릿부터 시작한다.
		// ForwardServlet2 여기로 request(요청), response(응답) 모두 전달하겠다.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
