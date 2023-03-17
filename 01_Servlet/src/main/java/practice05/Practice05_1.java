package practice05;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Practice05_1")
public class Practice05_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 냉장고 sysout으로 출력하고 Practice05_2로 리다이렉트 
		// 한글을 보내야 하니까 인코딩 신경써서 하기
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		System.out.println("Practice05_1 :" + model); 
		
		
		response.sendRedirect("/01_Servlet/Practice05_2?model=" +URLEncoder.encode(model, "UTF-8"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
