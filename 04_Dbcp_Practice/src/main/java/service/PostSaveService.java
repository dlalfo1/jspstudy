package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PostVO;
import repository.PostDAO;

public class PostSaveService implements IPostService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PostVO post = PostVO.builder()
				            .writer(request.getParameter("writer"))
				            .title(request.getParameter("title"))
				            .content(request.getParameter("content"))
				            .ip(request.getRemoteAddr()) // ip는 파라미터에 담겨있지않고 주소에 담겨있다. getRemoteAddr())로 가져오면 된다.
				            .build();
		int saveResult = PostDAO.getInstance().savePost(post);
		PrintWriter out = response.getWriter();
		if(saveResult == 1) { // 1이면 성공
			out.println("<script>");
			out.println("alert('포스트가 작성되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.post'"); // 삽입 후 redirect와 동일한 코드
			// 이렇게 직접 redirect하는 코드를 넣을경우 controller에서 redirect처리를 할 필요가 없다.
			out.println("</script>");
			out.flush();
			out.close();
			
		} else { // 아니면 실패
			out.println("<script>");
			out.println("alert('포스트 작성이 실패했습니다.')");
			out.println("history.back()"); // 실패했으면 이전페이지로 돌아가라.
			out.println("</script>");
			out.flush();
			out.close();	
		}
		
        return null; // 가져오기를 실패했을 경우 null을 반환하므로 controller로 돌아가서 다시 forward이동이 있는 코드가 실행되는걸 막는다.
        
	}

}
