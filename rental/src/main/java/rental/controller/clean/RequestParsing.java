package rental.controller.clean;

import java.io.IOException;
import java.net.http.HttpRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

public class RequestParsing {

	// JSON -> 자바 객체로 변환할 ObjectMapper 인스턴스 생성
	private static final ObjectMapper mapper = new ObjectMapper();
	
	// 다양한 타입의 클래스를 매개변수로 받고 반환하기 위해 제네릭타입 사용
	public static <T> T jsonToDto(HttpServletRequest req, Class<T> dtoClass) throws IOException {
	       return mapper.readValue(req.getReader(), dtoClass);
	}
}
