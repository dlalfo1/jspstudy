package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetServlet")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		  	요청
		  	
		  	1. 클라이언트 -> 서버로 보내는 것이 요청(Request)이다.
		  	2. HttpServletRequest 클래스가 요청을 처리한다.
		  	3. 요청에 포함된 파라미터(Parameter)는 String 타입으로 처리한다.
		  
		  */
		
		// 파라미터로 요청한 model과 price는 request에 담긴다.
		
		// 요청 정보를 UTF-8로 인코딩한다.
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터를 꺼낸다.
		// 모든 파라미터의 타입은 string이다.
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// 요청 파라미터에 null 처리를 한다.
		int price = 0;
		if(strPrice != null) { // Integer.parseInt() 에는 null값이 올 수 없으므로 파라미터값이 없을경우를 위해 if문으로 걸러준다.
			                   // model은 null값이 출력되도 문제가 되지않는데 int에서 오류가 나는 것임.
			                   // 파라미터 price가 없을 경우 price값을 0으로 설정해준다.
								
		price = Integer.parseInt(strPrice); // 실제 price값은 int값이므로 파싱해준다. : "200" -> 200
		
		}
			
		// 이 부분이 화면을 만들어주는 부분이다.
		response.getWriter().append("model: " + model).append(", price :" + price);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
