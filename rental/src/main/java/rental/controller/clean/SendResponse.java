package rental.controller.clean;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class SendResponse {
	
	// JSON 타입으로 Response 하는 코드
	public static void JsonResponse(HttpServletResponse resp, Object result ) throws IOException {
		resp.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = mapper.writeValueAsString(result);
        
        System.out.println(jsonResponse);
		resp.getWriter().write(jsonResponse);
	}

}
