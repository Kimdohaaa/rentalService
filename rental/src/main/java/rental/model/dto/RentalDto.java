package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	
	// 필요한 멤버변수는 추가해주세요.
	private String sname;
}
