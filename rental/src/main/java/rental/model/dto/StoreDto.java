package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StoreDto {
	private int sno;
	private String smno;
	private String saddr;
	private String sname;
	private int sstate;
	private String simg;
	
	// 필요한 멤버변수는 추가해주세요.
}
