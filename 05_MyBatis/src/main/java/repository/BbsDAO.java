package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {
	
	// field
	private SqlSessionFactory factory; // 필드로 선언을 빼야 모든 메소드들이 factory를 가져다 쓸 수 있다.
									   // 왜냐 DAO의 모든 메소드들이 세션을 얻어서 DB의 쿼리문을 실행시켜야 하니까.
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO(); // 1. 미리 객체를 생성해 놓은 후 private로 막는다.
	private BbsDAO() { 						  // 2. 미리 생성자를 만들어 놓은 후 private로 막는다.
											  // 이렇게 하면 외부에서 새로운 객체를 만들 수도 생성자를 호출할 수도 없다.
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추다. */  
	
	// mapper의 namespace 변수로 만들어두기. (자주 사용하기 때문이다.)
	private final String NS = "mybatis.mapper.bbs.";
	
	// 1. 목록
	public List<BbsDTO> selectAllBbsList() {
		SqlSession ss = factory.openSession();
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList"); // mapper의 이름.실행할 쿼리문의 id
		// selectList() 메소드 안에 preparedStatement, resultSet, while문 기능이 전부 담겨져 있다.
		// dbcp, cp 기능을 전부 얘가 하는거임.
		ss.close();
		return bbsList;	
	}
	
	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs (BbsDTO bbs) { // 삽입 쿼리문엔 title과 content가 필요하니 그게 담겨 있는 BbsDTO를 전달한다.
		SqlSession ss = factory.openSession(false); // auto commit 방지
		int insertResult = ss.insert(NS + "insertBbs", bbs); // 여기도 insert()메소드만 실행하면 알아서 지가 다해줌.(뭘 다해주는지는 추후 생각해보기로 하자.)
		if(insertResult == 1) { // 삽입 성공시
			ss.commit();        // commit 하시오.
		}
		ss.close();
		return insertResult;
		
	}
	
	// 4. 수정
	public int updateBbs(BbsDTO bbs) { // 수정 쿼리문엔 title, content, bbsNo가 필요하니 그게 담겨 있는 BbsDTO를 전달한다.
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateBbs", bbs);
		if(updateResult == 1) { 
			ss.commit();        
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제 
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if (deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	
	
}
