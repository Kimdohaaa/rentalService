package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaymentDto {
	private int dno;
	private String imp_uid;
	private String PG_num;
	private int dprice;
	private int rno;
}
