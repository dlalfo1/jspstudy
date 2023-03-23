package ex07_ajax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AirKoreaApiServlet")
public class AirKoreaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8"); // 예외처리가 필요하지만 doGet메소드에서 발생한 예외는 IOException이 받아주기 때문에 따로 할 필요 없다.
		
		// 요청 파라미터
		String sidoName = request.getParameter("sidoName");
		String returnType = request.getParameter("returnType");
		
		// 서비스키 (인코딩되지 않은 상태)
		String serviceKey = "+h82WwRALt+3L4awyNYZmmtJWZkKscUT/Fm7NDCD2dinRfTWm+jbUFT7xCUVAENXwgn1So0f2X0WpNuE69Gymw==";
		
		// 요청 주소
		String apiURL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8"); // 서비스키를 인코딩 시켜서 요청주소에 파라미터로 붙인다.
		apiURL += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8"); 	   // sidoName에 들어올 값들은 한글이기 때문에 인코딩 필수
		apiURL += "&returnType=" + URLEncoder.encode(returnType, "UTF-8");  // ruturnType에 들어올 값은 영어라 인코딩할 필요가 없지만 코드 통일 위해서 똑같이 한다.
		
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청 메소드
		con.setRequestMethod("GET"); // 요청방식은 꼭 대문자로 전달해야 한다.
		
		// returnType에 따른 Content-Type
		con.setRequestProperty("Content-Type", "application/" + returnType + "; charset=UTF-8"); 
															// html <option> 태그에서 선택한 값이 returnType으로 전달된다.
		
		// 입력 스트림 생성(api가 전달해주는 데이터 읽기 위함)
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 입력 (API의 응답 결과를 StringBuilder에 저장)
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null ) { // 읽을 내용이 남아 있다면
			sb.append(line);
		}
		
		// 입력 스트림, 접속 종료
		reader.close();
		con.disconnect();
		
		// API결과를 ajax 응답 처리하기
		response.setContentType("application/" + returnType + "; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
		System.out.println(sb);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
