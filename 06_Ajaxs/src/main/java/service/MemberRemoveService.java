package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

import java.io.PrintWriter;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		int deleteResult = MemberDAO.getInstance().deleteMember(Integer.parseInt(request.getParameter("memberNo")));
		
		
		JSONObject obj = new JSONObject();
		obj.put("deleteResult", deleteResult);
		
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();
		
	}

}
