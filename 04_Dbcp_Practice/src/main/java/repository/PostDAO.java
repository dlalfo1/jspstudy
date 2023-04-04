package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.PostVO;

public class PostDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql = "";
	
	private DataSource dataSource; // DataSource : CP 관리하는 애임. (Connection Pool 관리한다.)
	
	private static PostDAO dao = new PostDAO();
	private PostDAO() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g"); 
			// lookup으로 이부분을 찾아서 저 파일에 있는 내용으로 데이터소스 만들어주세요 하는 코드임.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PostDAO getInstance () {
		return dao;
	}
	
	public void close() {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PostVO> getAllPosts() throws Exception{ // 원래 목록 반환할 땐 예외처리가 필요한데 여기서도 예외를 던져버림.
														// 결국 DAO가 예외를 던지면 Service에서 받고 Service에서도 던지면 최종적으로 Controller에서 받아서 처리한다.
		
		List<PostVO> posts = new ArrayList<PostVO>();
		con = dataSource.getConnection(); // DB에 접근하기 위해 커넥션 얻어오기
		sql = "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";
		sql += "  FROM POST";
		sql += " ORDER BY POST_NO DESC";
		// FROM과 ORDER절 앞에는 공백이 있어야 한다. 그래야 문자열 합칠 때 오류가 생기지 않는다.
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			PostVO post = PostVO.builder() // PostVO를 만들어주는 builder() 메소드
		// rs.getInt(1) 이렇게 숫자를 넣어서 가지고 올 수 있는데 순번은 SELECT문에 적어둔 순서대로 가져온다. (실제 테이블 칼럼명의 순서가 아니다.)
					            .post_no(rs.getInt(1))   // setPost_no(rs.getInt(1))과 같은 역할이다. 메소드명과 필드명을 일치시키고 메소드체이닝으로 필드를 빠르게 채울 수 있다. 
					            .writer(rs.getString(2))
					            .title(rs.getString(3))
					            .content(rs.getString(4))
					            .ip(rs.getString(5))
					            .modified_date(rs.getDate(6))
					            .created_date(rs.getDate(7))
					            .build(); // 다 만들었으면 마지막에 이 코드를 넣어야 한다.
			 posts.add(post); // ArrayList에 post객체를 만들어서 담는다.
		}
		
		close();
		return posts; // ArrayList를 반환한다.
		
	}
	
	public int savePost(PostVO post) throws Exception {
		con = dataSource.getConnection();
		sql += "INSERT INTO POST(POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE)";
		sql += " VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, NULL, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getWriter());
		ps.setString(2, post.getTitle());
		ps.setString(3, post.getContent());
		ps.setString(4, post.getIp());
		
		int saveResult = ps.executeUpdate();
		close();
		return saveResult;
	}
	
}
