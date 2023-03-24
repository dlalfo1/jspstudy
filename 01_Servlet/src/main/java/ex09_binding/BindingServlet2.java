package ex09_binding;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BindingServlet2")
public class BindingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HttpServletRequest에 저장된 속성 확인 
		// redirect는 전달이 없는 이동이기 때문에 속성값도 전달이 되지 않는다.
		System.out.println(request.getAttribute("a")); // null
		
		
		// HttpSession에 저장된 속성 확인
		System.out.println(request.getSession().getAttribute("a")); // 200
		
		// ServletContext에 저장된 속성 확인
		System.out.println(request.getServletContext().getAttribute("a")); // 300
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
