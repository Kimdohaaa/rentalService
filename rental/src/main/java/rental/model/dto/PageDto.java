package rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class PageDto {
	private int totalCount;
	private int page;
	private int totalpage;
	private int startbtn;
	private int endbtn;
	private Object data;
}
