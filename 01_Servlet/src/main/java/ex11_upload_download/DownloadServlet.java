package ex11_upload_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// 다운로드 해야 할 File 객체
		File file = new File(path); // ptath에 경로와 파일명이 다 들어있음
		
		// 다운로드 해야 할 파일을 읽어들일 입력 스트림(
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드용 응답 헤더 작업
		response.setHeader("Content-Disposition", "attachment; filename" + path.substring(path.lastIndexOf("\\"))); // 정해진 코드임. (모질라 개발싸이트에서 확인 가능)
		response.setHeader("Content-Length", file.length() + ""); // file.length()는 정수를 반환해서 문자열 붙여서 String값으로 변환한다.
		
		// 응답 스트림(출력 스트림)
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사 (in에서 1024바이트 단위로 읽은 다음 out으로 보내기)
		// 스프링가면 여기서부턴 내가 직접 짤 필요 없다. 공부할거면 스트림쪽 하기.
		byte [] b = new byte[1024];		// 입력 단위
		int readByte = 0;				// 실제로 읽은 바이트
		while((readByte = in.read(b)) != -1) { // 읽은 바이트만큼만 넘기기(실제 읽은 바이트만큼만). 읽은 건 배열 b에 담겨져 있다.
			out.write(b, 0, readByte);		   // 읽어온게 담겨있는 배열 b의 인덱스 0부터 실제로 읽은 갯수가 담겨 있는 readByte까지 출력하기.
		}
		out.close();
		in.close();
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
