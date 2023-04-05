package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import domain.BbsDTO;
import repository.BbsDAO;

public class BbsTest {

	// BbsDAO의 메소드 단위로 테스트를 진행한다.
	private BbsDAO dao = BbsDAO.getInstance(); // dao는 싱글톤패턴이기 때문에 getInstance(); 메소드를 통해서 객체를 만들 수 있다.
	
	// @Test
	// Test하는 메소드
	public void 목록테스트() { // 여긴테스트라 한글도 가능하다.
		assertEquals(2, dao.selectAllBbsList().size()); 
		// 1번째 값 : 얼마가 나올지 기대하는 값 ,  2번째 값 : 실제로 나올 개수의 값
		// dao.selectAllBbsList().size() : 실제로 SELECT문 실행시 얻어지는 ArrayList의 길이.
	}

	// @Test
	public void 상세테스트() {
		assertNotNull(dao.selectBbsByNo(1));
	}
	
	// @Test
	public void 삽입테스트 () {
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle("테스트제목");
		bbs.setContent("테스트내용");
		assertEquals(1, dao.insertBbs(bbs));
	}
}
