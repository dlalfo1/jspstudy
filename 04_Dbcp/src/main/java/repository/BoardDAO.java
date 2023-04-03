package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;
import oracle.sql.DATE;

public class BoardDAO {

	// 모든 메소드가 사용할 공통 필드
	private Connection con; 		// 초기값이 기본적으로 null임.
	private PreparedStatement ps;   // 쿼리문 실행
	private ResultSet rs; 			// select(목록보기와 게시글 반환하기)만 쓴다.
	private String sql;				// 쿼리문 자체
	
	
	// Connection 관리를 위한 DataSource 필드 
	private DataSource dataSource;
	
	
	// Single Pattern으로 DAD 생성하기
	// 필드에 미리 BoardDAO 객체를 생성해둔다. (외부에서 못 본다.)
	private static BoardDAO dao = new BoardDAO();
	
	// 외부에선 객체를 못 만들게 막아놓는다. (외부에서 못 본다.)
	// 원래 디폴트 생성자는 굳이 만들지 않아도 자동으로 생성되지만
    // private으로 막아놓기 위해서 생성해준다.
	private BoardDAO() { // 아무도 BoardDAO()를 호출할 수 없게 막아놓는다.
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기 (JNDI 방식)
		try {
			Context context = new InitialContext(); // Context 인터페이스의 InitialContext클래스
			Context envContext = (Context)context.lookup("java:comp/env"); // Context타입으로 캐스팅 해줘야한다.
			// 여기까지 Tomcat의  Context파일을 읽어드릴 준비가 된거다.
			
			// 여기부터 진짜 파일을 읽어드린다.(..?)
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61"); //DataSource으로 캐스팅 해줘야한다.
			
			/*
			 위 3줄이랑 같은 코드이다.
			 Context context = new InitialContext();
			 dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
			*/
		} catch(NamingException e) { // 이름이 없을경우에 발생하는 NamingException
			e.printStackTrace();
		}
		
	
	}
	
	// getInstance 메소드로 내가 만들어놓은 객체를 갖다 쓴다. (해당 클래스의 인스턴스를 반환하는 역할을 수행)
	// static 메소드는 클래스메소드라고도 한다 => 클래스 이름으로만 호출이 가능하기 때문이다.
	// new BoardDAO()로 객체를 생성하는 코드를 막아놨으니 getInstance()를 호출하려면 클래스 이름으로 호출해야 한다.
	// 클래스메소드는 static 키워드를 붙여줘야하고 static메소드는 static 필드만 가져다 쓸 수 있다.
	// 그러므로 private static BoardDAO dao = new BoardDAO(); 이 코드에도 static이 붙는것이다.
	
	public static BoardDAO getInstance() { // 만든 dao를 가져가 쓸 수 있게 dao를 리턴해주는 getInstance() 메소드를 만들어준다.
		return dao;
	}
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시물 목록 반환하기 (DTO 여러개)
	public List<BoardDTO> selectBoardList() {
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList로 만듦.
			while(rs.next()) { // 읽어온 결과가 있다면(ture)
				
				// Step1. Board 테이블의 결과 행(ROW)를 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date =  rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// Step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// Step3. BoardDTO 객체를 ArryList에 추가한다.
				boardList.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
			
		}
		
		return boardList;
	}
	
	// 게시글 반환하기 (DTO 한개)
	public BoardDTO selectByNo(int board_no) {
		
		return null;
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		return 0;
	}
	
}
	
