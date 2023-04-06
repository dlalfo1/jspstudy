package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Member;
import repository.MemberDAO;

public class MemberUnitTest {

	 @BeforeClass // MemberUnitTest 클래스(테스트 파일) 실행 이전에 한 번 먼저 실행된다. (static 처리가 되어 있어야 한다.)
	 			  // 객체 이전에 만들어지는건 static뿐이기 때문이다.
	 
	 // 테스트 전에 삽입실행을 한번 해보겠다. 이 후에 5개 메소드 전부 테스트 하고 삽입한걸 삭제한다.
	 // 기존엔 미리 테이블에 행을 삽입해놨어서 삽입테스트를 먼저 실행할 필요가 없었는데
	 // 6장은 테이블의 데이터가 아무것도 없어 삽입테스트를 다른테스트들보다 먼저 한번 실행해준다.
	 
	 
	 public static void 삽입테스트() {
		 Member member = new Member(0, "admin", "관리자", "M", "seoul");
		 
		 // memberNo는 쿼리문에서 시퀀스가 만들어줄거니까 사용하지 않는다는 0값을 넣어준다.
		 assertEquals(1, MemberDAO.getInstance().insertMember(member));
		 
	 }
	 
	 @Test
	 public void 목록테스트() {
		 assertEquals(1, MemberDAO.getInstance().selectAllMembers().size()); // 가져옥 목록의 개수가 1개인지 테스트.
	 }
	 
	 @Test
	 public void 상세테스트() {
		 Member member = new Member(1, "admin", "관리자", "M", "seoul");
		 assertEquals(member, MemberDAO.getInstance().selectMemberByNo(1));
		 // 정상적으로 삽입 쿼리문이 돌아갔으면 회원번호가 1번이니 	1넣어준다.
		 // 가지고온 멤버가 1 member와 동일한지 객체 비교
	 }
	 
	 @Test
	 public void 수정테스트() {
		 Member member = new Member(1, "null", "new관리자", "F", "newseoul"); // 게시글 수정시 id는 동일하니 null값으로 준다.
		 assertEquals(1, MemberDAO.getInstance().updateMember(member));
	
	 }
	 
	  @AfterClass // MemberUnitTest 클래스(테스트파일) 실행 이후에 한 번 먼저 실행된다.(static 처리가 되어 있어야 한다.)
	  public static void 삭제테스트() {
		  assertEquals(1, MemberDAO.getInstance().deleteMember(1));
	  }
	  
	  // 만약 테스트 실패한다? 그럼 member.sql 쿼리 다시 돌리자. 테스트 하기전 기본 테이블로 다시 만들어주는거임.
	  
	  // 삭제테스트까지 마치고 나서 디벨로퍼 켜서 깨끗한지 보거나
	  // member.sql로 돌아가서 SELECT문 하나 실행해보기
	  
	  
	 
}
