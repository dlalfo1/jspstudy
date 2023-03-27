package ex11_upload_download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 	파일 업로드를 위한 작업
			1. http://servlets.com에 방문한다.
			2. COS File Upload Library 메뉴에 있는 cos-22.05.zip 파일을 다룬로드 받는다.
			3. 압축 해제 후 lib 디렉터리에 있는 cos.jar 파일을 프로젝트의 lib로 등록한다.
		*/
		
		/*
			cos.jar
			1. 파일 업로드가 필요한 경우에 사용하는 라이브러리이다.
			2. HttpServletRequest 클래스는 파일 업로드를 처리할 수 없다.
			3. MultipartRequest 클래스를 이용해서 파일 업로드를 처리할 수 있는데,
			    cos.jar가 MultipartRequest클래스를 지원한다. 
		*/
		
		// 업로드 경로(서버의 real path를 사용하거나 일반 경로를 사용할 수 있다.)
		String realPath = request.getServletContext().getRealPath("storage");
		File dir = new File(realPath);
		if(dir.exists() == false) { // 파일이 없다면
			dir.mkdirs();			// 파일 만들기
		}
		
		// 업로드 진행하기 (HttpServletRequest request를 이용해서 MultipartRequest 객체를 생성하면 업로드가 곧바로 진행된다.)
		// 업로드 코드는 따로 없다. new MultipartRequest 코드로 업로드가 되는것임.
		MultipartRequest multipartRequest = new MultipartRequest(
				request, 		  // HttpServletRequest
				realPath,		  // 업로드 경로(문자열 "storage" 전달)
				1024 * 1024 * 10, // 최대크기
				"UTF-8",		  // 인코딩
				new DefaultFileRenamePolicy()	// 파일명 중복 처리 정책 (파일명 뒤에 넘버링 => 중복된 파일이 존재하면 뒤에 숫자 붙임)
		);

		// 요청 파라미터 (MultipartRequest multipartRequest를 이용해서 파라미터를 가져와야 한다.)
		// 나중에 이 이름들이 db의 칼럼명으로 들어갈 것이다.
		String uploader = multipartRequest.getParameter("uploader");
		String originName = multipartRequest.getOriginalFileName("filename");   // 원래 첨부 파일명
		String filesystemName = multipartRequest.getFilesystemName("filename"); // 저장된 첨부 파일명
		
		// 첨부 파일 정보
		// 파일명, 경로, 최종수정일(yyyy-MM-dd), 파일크기(KB)
		File file = multipartRequest.getFile("filename");
		String name = file.getName();
		String parent = file.getParent(); // 파일경로
		String lastModified = new SimpleDateFormat("yyyy-MM-dd").format(file.lastModified()); // 파일의 최종수정일을 우리가 알 수 있는 날짜형식으로 바꾼다.
		String size = new DecimalFormat("#,##0").format(file.length() / 1024 + (file.length() % 1024 == 0 ? 0 : 1)); // 1024바이트가 1KB, 1KB가 넘으면 2KB이다.
	
	
	// 응답
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<ul style=\"list-style-type: circle; font-size: 24px;\">");
	out.println("<li>작성자 : " + uploader + "</li>");
	out.println("<li>원래 첨부파일명 : " + originName + "</li>");
	out.println("<li>저장된 첨부파일명 : " + filesystemName + "</li>");
	out.println("<li>파일명 : " + name + "</li>");
	out.println("<li>경로 : " + parent + "</li>");
	out.println("<li>최종수정일 : " + lastModified + "</li>");
	out.println("<li>파일크기 : " + size + "KB</li>");
	
	/*
	 	out.println("<li><a href=\"/01_Servlet/FileListServlet?parent=" + parent +"\">첨부된 파일 목록 보기</a></li>");
	    이 상태로 주소(parent)를 넘기면 주소가 정상적으로 전달되지 않는다. 인코딩 해주어야 한다. (받는곳(FileListServlet)에선 디코딩 해줘야함)
	    a링크 태그를 작성하려면 따옴표 안에 주소를 넣어야하는데 자바코드에서 따옴표 쓰려면 역슬래시를 사용해야함.
	    그럼 경로에 문제가 생긴다.
	*/
	
	out.println("<li><a href=\"/01_Servlet/FileListServlet?parent=" + URLEncoder.encode(parent, "UTF-8") +"\">첨부된 파일 목록 보기</a></li>");
	out.println("</ul>");
	out.flush();
	out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
