package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// if(str == null || str.isEmpty())
		// => 보통 null 처리는 이런식으로 둘 다 걸리게끔 한다. 
		
		// <form> 태그에 포함된 입력 요소들이 name 속성을 가지고 있다면, null 처리를 할 수 없다. 빈 문자열로 처리해야 한다.
		
		int price = 0;
		if(strPrice.isEmpty() == false)  { // 빈문자열 점검(빈 문자열이 아니라면 조건문을 실행하겠다.)
			price = Integer.parseInt(strPrice);
		}
		
		
		
		response.getWriter().append("model :" + model).append(", price: " + price);
		//  price에 아무것도 입력하지 않았으면 price의 초기값인 0이 출력되는것이고
		// 입력한 값이 있다면 if문의 조건문이 실행되어 strPrice이 int타입으로 파싱된 price가 출력되는것이다.
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청이 들어옴"); // POST 요청이 들어왔다는걸 확인하기 위함.
		doGet(request, response); // POST 요청이 들어와도 결국 모든 처리는 doGet() 메소드에서 한다.
								  
	}

}
