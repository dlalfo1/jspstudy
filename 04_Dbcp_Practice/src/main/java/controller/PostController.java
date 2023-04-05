package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IPostService;
import service.PostDeleteService;
import service.PostDetailService;
import service.PostListService;
import service.PostSaveService;

@WebServlet("*.post") // 	/list.post	/detail.post	/save.post	/change.post	/edit.post	delete.pose
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청, 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// urlMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 서비스 타입 선언
		IPostService service = null;
		
		// forward 경로
		String path = null;

		// urlMapping에 따른 서비스 선택(생성)
		switch(urlMapping) { // urlMapping값이 없을 경우 switch문이 돌지 않는다.
		case "/list.post" :
			service = new PostListService();
			break;
		case "/save.post" :
			service = new PostSaveService();
			break;
		
		// 위 2가지 case는 service가 필요한 케이스고 아래 case는 service가 필요없다. 
		// 그러니 case에서 "/write.post"가 선택되면 sevice는 null값이니 서비스 실행 코드는 돌지 않고
		// 이동할 경로로 이동하는 forward 코드가 실행된다.
			
		case "/write.post":
			path = "post/write.jsp";
			break;		
		case "/detail.post":
			service = new PostDetailService();
			break;
		case "/delete.post" :
			service = new PostDeleteService();
			break;
		}
		
		// 선택된 서비스 실행
		if(service != null) { // service가 null일 경우 if문안의 execute메소드가 돌지 않는다.
			try {
				
				// redirect가 필요한 서비스(삽입, 수정, 삭제)는 서비스 내에서 직접 redirect하고(location.href를 이용) path에 null을 반환한다.
				// 즉 서비스 내에서 redirect 코드는 들어가지 않고 javascript로 location.href를 이용해서 redirect 이동을 할 것이다.
				// 그러니 계속 만들던 ActionForward 클래스도 만들 필요가 없다.
				
				path = service.execute(request, response); // try-cath가 필요하다.
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			/* 
			  위에서 execute 메소드 실행시 redirect라면 path엔 null값이 들어가고
			  아래 코드에선 path가 null이니 forward 코드가 실행되지 않는다.
			*/
			
		// 이동할 경로(path)로 forward
		if(path != null) {
			request.getRequestDispatcher(path).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
