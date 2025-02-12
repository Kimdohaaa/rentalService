package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDto {
	
	
	private int mno;
	private String mid;
	private String mpwd;
	private String mphone;
	private String maddr;
	private int mgender;
	private int mstate;
	private String mdate;
	
	// 필요한 멤버변수는 추가해주세요.
}
