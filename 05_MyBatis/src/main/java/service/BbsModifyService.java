package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsModifyService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// bbsNo는 input태그의 name속성에 있기 때문에 값이 없으면 빈문자열이 넘어오므로 
		// null체크 말고 공백체크를 해준다. 공백이라면 0, 아니라면 넘어온 파라미터 그대로.
		
		
		BbsDTO bbs = new BbsDTO();
		// 동일한 파라미터값을 여러군데 써야하니까 아예 변수처리해준다.
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo").isEmpty() ? "0" : request.getParameter("bbsNo"));
		bbs.setBbsNo(bbsNo);
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int updateResult = BbsDAO.getInstance().updateBbs(bbs);
		
		if(updateResult == 1) {
			try {				
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('BBS가 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/detail.do?bbsNo=" + bbsNo + "'");	
				out.println("</script>");
				out.flush();
				out.close();
				return null;
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ActionForward(request.getContextPath() + "/list.do", false);
		
	}

}
