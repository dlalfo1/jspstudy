package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

@WebServlet("/XMLServlet")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		// 응답할 XML 만들기(JSONObject를 먼저 만든 뒤 XML 변환)
		
		/*
		 	<book>
		 		<title>제목</title>
		 		<author>저자</author>
		 		<price>가격</price>
		 	</book>
		 	=> 이걸 만들려면 JSONObject에 title, author, price 프로퍼티를 넣어줘야 한다.
		 	=> 최종적으로는 book 프로퍼티가 필요하다.
		 */
		
		JSONObject obj = new JSONObject();
		obj.put("title", title);
		obj.put("author", author);
		obj.put("price", price);
		JSONObject obj2 = new JSONObject();
		obj2.put("book", obj);
		
		String resData = XML.toString(obj2); // XML데이터에 obj2를 전달하는 것이다.
			
		// JSON데이터를 toString해서 보내는이유는 JSON관련 라이브러리가 여러개 있을 수 있는데
		// 서로 다른 라이브러리 사용시 오류가 나는것을 방지하기 위해 toString()메소드를 사용해서 문자열로 보내면 안전하기 때문이다.
		
		// 응답 데이터 타입
		response.setContentType("application/xml; charset=UTF-8");
		
		
		// 출력 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 출력
		out.println(resData);
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
