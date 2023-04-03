package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // 기본 생성자 만드는 롬복
@AllArgsConstructor // 필드변수 사용한 생성자 만드는 롬복
@Getter				// 게터 만들어주는 롬복
@Setter				// 세터 만들어주는 롬복

public class BoardDTO {
	private int board_no;
	private String title;
	private String content;
	private Date modified_date;
	private Date created_date;
}

