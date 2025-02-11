package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentalDto {
	private int rno;
	private String rdate;
	private String rtime;
	private int rstate;
	private int rcount;
	private int rprice;
	private String rreason;
	private int mno;
	private int sno;

	// + HTML에 출력할때 작성자의 회원번호가 아닌 작성자 ID, 가맹점 번호가 아닌 가맹점 이름 출력
	private String mid;

	// 필요한 멤버변수는 추가해주세요.

	private String sname;
}
