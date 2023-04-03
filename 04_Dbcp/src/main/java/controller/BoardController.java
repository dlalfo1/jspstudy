package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;


@WebServlet("*.do") // getAllBoardList.do getBoardByNo.do  writeBoard.do addBoard.do  modifyBoard.do removeBoard.do
					// .do로 끝나는 모든 URLMapping값을 받는 서블릿

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI(); 					/*		/04_Dbcp/getAllBoardList.do 	*/
		String contextPath = request.getContextPath();					/*		/04_Dbcp						*/
		String urlMapping = requestURI.substring(contextPath.length()); /*		/getAllBoardList.do				*/
	
		
		// 모든 서비스의 공통 타입 선언
		IBoardService service = null; // 모든서비스는 IBoardService(인터페이스)타입으로 선언할 수 있다.
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성
		switch(urlMapping) {
		case "/getAllBoardList.do":
			service = new BoardListService();
			break;
		case "/getBoardByNo.do":
			service = new BoardDetailService();
			break;
		case "/addBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do":
			af = new ActionForward("board/write.jsp", false); // board폴더 아래 write.jsp로 forward한다. (단순 이동의 경우 forward한다.)
			break;
		}
		
		// 서비스 실행
		if(service != null) { // service에 null값으로 초기값을 줬기때문이 null체크를 해야한다.
			af = service.execute(request, response);
		}
		
		// 응답View로 이동
		if(af != null) { // service에 null값으로 초기값을 줬기 때문에 null체크를 해야한다.
			if(af.isRedircet()) { // redirect의 상황이라면
				response.sendRedirect(af.getPath()); // ???
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response); //...?
			}
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
