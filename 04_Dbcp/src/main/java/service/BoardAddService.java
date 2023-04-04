package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. BoardDTO 객체 생성
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달(boardDAO의 insertBoard 메소드)
		int insertResult = BoardDAO.getInstance().insertBoard(board); // dao 객체 불러서 insertBoard 메소드 호출 후 board 인수로 넣어주기.
		
		System.out.println(insertResult == 1 ? "삽입성공" : "삽입실패");
		
		// 4. 어디로 and 어떻게 이동
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true); // redirect 하겠다.
														  // insert, delete, ... 한 후에는 redirect 이동한다.

	}

}
