package practice07;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

@WebServlet("/PapagoServlet")
public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String source = request.getParameter("source"); // 원본 언어 코드(ko, en, ja 중 하나)
		String target = request.getParameter("target"); // 목적 언어 코드(ko, en, ja 중 하나)
		String text = request.getParameter("text");		// 번역할 텍스트(인코딩 해줘야 함)
		
		// 클라이언트 아이디, 시크릿
		String clientId = "M_Nmt932AK4Lp3FFTpyn";
		String clientSecret = "kQFt9nvEBJ";
		
		// API 주소
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청메소드
		con.setRequestMethod("POST");
		
		// 요청 헤더에 포함하는 내용
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		
		// Papago API로 보내야 하는 파라미터(source, target, text)
		// 파라미터 보낼 때 보통 변수명은 param이다. 여러개면 params
		String params = "source=" + source + "&target=" + target + "&text=" + URLEncoder.encode(text, "UTF-8");
		
		// Papago API로 파라미터를 보내기 위해서 출력 스트림 생성
		// con이 얻을 수 있는 건 바이트 스트림인 getOutputStream 뿐이다.
		con.setDoOutput(true);
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
		
		// Papago API로 파라미터 보내기
		dos.write(params.getBytes()); // String params를 바이트로 바꿔서 그대로 보낸다.
									  // writeUTF메소드 사용해서 인코딩해서 보내면 source 파라미터에 문제가 생기는 것 같음.
		dos.flush();
		dos.close();
		
		// Papago API로부터 번역 결과를 받아 올 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			// con은 바이트스트림만 얻어올 수 있으니까 문자스트림인 리더를 사용하는 것이다.
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// Papago API로부터 번역 결과를 받아서 StringBuilder에 저장
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		// StringBuilder의 번역 결과를 client.html의 ajax로 보내기
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
