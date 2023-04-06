package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsAddService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		BbsDTO bbs = new BbsDTO();
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int insertResult = BbsDAO.getInstance().insertBbs(bbs);
		
		try {
	
			PrintWriter out = response.getWriter();
			if(insertResult == 1) { // INSERT 성공시 IF문안의 코드들이 작성된다.
				out.println("<script>");
				out.println("alert('BBS가 등록되었습니다.')");
				out.println("loction.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null; // 성공시 out코드로 응답을 만들었기 때문에 null값을 반환한다.
				 			 // BbsController를 통한 이동을 방지
							 // 어떤 서비스든 응답이 끝나면 다시 컨트롤러로 돌아가는데
				             // null값이 반환해야 컨트롤러에서 다시 이동시키지 않는다.
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// BBS 등록 실패
		return new ActionForward(request.getContextPath() + "/list.do", true); // INSERT 실패시 여기로 온다.
	}

}
