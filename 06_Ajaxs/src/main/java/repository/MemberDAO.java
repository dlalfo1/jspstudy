package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDAO {

	private SqlSessionFactory factory;
	
	private static MemberDAO dao =  new MemberDAO();
	private MemberDAO () {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static MemberDAO getInstance() {
		return dao;
	}
	
	// mapper's namespace (어떤 mapper인지 인식하기 위함)
	// 메소드마다 사용해야 하니 변수로 만들어주고 변하지 않을 값이니 상수 final선언을 해준다.
	private final String NS = "mybatis.mapper.member.";
	
	// 메소드명은 쿼리의 id와 동일한 이름을 사용하자
	
	// 목록 (여러개니까 반환타입이 List, 그 안엔 domain-Member(DTO)가 들어있다.)
	public List<Member> selectAllMembers() { // 받아오는 데이터(매개변수)가 없으니 쿼리문으로 전달한 데이터도 없다.
		SqlSession ss = factory.openSession(); // factory에서 세션을 얻어온다.
		List<Member> members = ss.selectList(NS + "selectAllMembers"); // 반환값이 여러개니 selectList() 메소드 사용한다.
										        // mybatis.mapper.member.selectAllMembers
		ss.close();
		return members;
		
		// selectAllMembers() 메소드가 member.xml파일의 id가 selectAllMembers인 쿼리문을 실행한다.
		
	}
	
	// 전체 회원 수
	public int getMemberCount() { // 받아오는 데이터(매개변수)가 없으니 쿼리문으로 전달한 데이터도 없다..
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getMemberCount");
		ss.close();
		return count;
	}
	
	// 상세
	public Member selectMemberByNo(int memberNo) { // 받아온 데이터 memberNo를 쿼리문으로 전달한다.
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(NS + "selectMemberByNo", memberNo); // selectMemberByNo 쿼리문에 memberNo를 넘겨라.
		ss.close();
		return member;
	}
	
	// 삽입
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		// 삽입, 삭제, 수정의 경우 쿼리문 실행시 COMMIT을 해줘야하는데
		// 작업 실패시 오토커밋을 방지하기 위해 false값을 주고 
		// if문을 통하여 결과값이 1(성공)이라면 직접 커밋해주도록 코드를 짠다.
		int insertResult = ss.insert(NS + "insertMember", member);
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
	
	// 수정
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateMember", member);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
		
	}
	
	// 삭제
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteMember", memberNo);
		
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}

	
	
	
	
	
	
	
	
}
