package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentFindService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		double begin = Double.parseDouble(request.getParameter("begin"));
		double end = Double.parseDouble(request.getParameter("end"));
		
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("begin", begin);
		map.put("end", end);
		// begin, end를 하나에 담기 위해 Map에 담는다.
		
		
		StudentDAO dao = StudentDAO.getInstance();
		request.setAttribute("students", dao.findStudentList(map)); // dao의 메소드에 Map을 전달한다.
		request.setAttribute("count", dao.getFindStudentCount(map));
		request.setAttribute("average", dao.getFindStudentAverage(map));
		// 학생을 찾는 FindService로 탑3를 가져오지 않는다.
		
		return new ActionForward("student/list.jsp", false);
		
	}

}
