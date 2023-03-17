package practice04;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Practice04_1")
public class Practice04_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 리다이렉트로 파라미터를 전달하려면 다시 파라미터를 붙여야한다.
		String model = request.getParameter("model");
		//r response에서 시작한다.
		response.sendRedirect("/01_Servlet/Practice04_2?model=" + model);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
