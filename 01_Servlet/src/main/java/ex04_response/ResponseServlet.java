package ex04_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 파라미터 처리하기
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		/*
		 	응답
			1. 서버 → 클라이언트로 보내는 것이 응답(Response)이다.
			2. HttpServletResponse 클래스가 응답을 처리한다.
			3. 어떤 MIME 타입으로 응답할 것인지 결정해야 한다.
			    ⇒ 응답할 데이터가 HTML이냐 JSON이냐 image냐 이런거
			         (반환타입이라 보면됨. int 타입 이런거!)
			     1) HTML : text/html             - (태그를 만들어서 반환하는 경우)
			     2) XML   : application/xml   - (ajax 응답이 XML인 경우)
			     3) JSON : application/json   - (ajax 응답이 JSON인 경우) 
		 */
		
		// 응답 만들기
		
		// 1. 응답할 데이터의 MIME 타입 결정과 UTF-8 인코딩 처리
		// MIME타입이 ContentType이다. 위 처리를 같이 해줄 경우 아래코드와 같이 처리한다.
		response.setContentType("text/html; charset=UTF-8"); // HTML 태그를 만들어서 반환할거야
		
		// 2. 응답 스트림 생성(IOException 처리가 필요하다. -> 이미 처리되어 있다.)
		// doGet() 메소드에서 이미 throws로 IOException예외를 던지고 있다. 즉, try, catch작업을 할 필요 없다.
		// getWriter() 의 반환타입은 PrintWriter이다.
		PrintWriter out = response.getWriter(); // PrintWirter의 출력 메소드 : append(), write(), println() 등 
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">"); // java에서 따옴표 처리하려면 이스케이프문자(\)를 사용해야한다.
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>모델명: " + model + "</h1>");
		out.println("<h1>가격: " + price + "</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush(); // (혹시) 출력 스트림에 남아 있는 데이터를 모두 보내기.
		out.close(); // 출력스트림은 항상 닫아줘야 한다(close() 해줘야 오류가 안난다.)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
