package rental.controller.clean;

import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class SendResponse {
	
	public static void mapping(HttpServletResponse resp, Object result) {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonResponse = mapper.writeValueAsString(result);

	        System.out.println(jsonResponse);

	        // 응답 헤더 설정
	        resp.setContentType("application/json");

	        // 응답 전송
	        resp.getWriter().write(jsonResponse);
	    } catch (IOException e) {
	        // 예외 발생 시, 상태 코드 500 (서버 오류)로 설정
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        mappingCodeCheck(resp.getStatus());
	    }
	}
	
	

	public static void mappingCodeCheck(int stateCode) {
	    // 응답 코드 출력
	    System.out.println("응답 코드: " + stateCode);

	    switch (stateCode) {
	        case HttpServletResponse.SC_OK:
	            System.out.println("200 OK");
	            break;

	        case HttpServletResponse.SC_CREATED:
	            System.out.println("201 Created");
	            break;

	        case HttpServletResponse.SC_ACCEPTED:
	            System.out.println("202 Accepted");
	            break;

	        case HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION:
	            System.out.println("203 Non-Authoritative Information");
	            break;

	        case HttpServletResponse.SC_NO_CONTENT:
	            System.out.println("204 No Content");
	            break;

	        case HttpServletResponse.SC_RESET_CONTENT:
	            System.out.println("205 Reset Content");
	            break;

	        case HttpServletResponse.SC_MULTIPLE_CHOICES:
	            System.out.println("300 Multiple Choices");
	            break;

	        case HttpServletResponse.SC_MOVED_PERMANENTLY:
	            System.out.println("301 Moved Permanently");
	            break;

	        case HttpServletResponse.SC_FOUND:
	            System.out.println("302 Found");
	            break;

	        case HttpServletResponse.SC_SEE_OTHER:
	            System.out.println("303 See Other");
	            break;

	        case HttpServletResponse.SC_NOT_MODIFIED:
	            System.out.println("304 Not Modified");
	            break;

	        case HttpServletResponse.SC_USE_PROXY:
	            System.out.println("305 Use Proxy");
	            break;

	        case HttpServletResponse.SC_TEMPORARY_REDIRECT:
	            System.out.println("307 Temporary Redirect");
	            break;

	        case HttpServletResponse.SC_BAD_REQUEST:
	            System.out.println("400 Bad Request");
	            break;

	        case HttpServletResponse.SC_UNAUTHORIZED:
	            System.out.println("401 Unauthorized");
	            break;

	        case HttpServletResponse.SC_PAYMENT_REQUIRED:
	            System.out.println("402 Payment Required");
	            break;

	        case HttpServletResponse.SC_FORBIDDEN:
	            System.out.println("403 Forbidden");
	            break;

	        case HttpServletResponse.SC_NOT_FOUND:
	            System.out.println("404 Not Found");
	            break;

	        case HttpServletResponse.SC_METHOD_NOT_ALLOWED:
	            System.out.println("405 Method Not Allowed");
	            break;

	        case HttpServletResponse.SC_NOT_ACCEPTABLE:
	            System.out.println("406 Not Acceptable");
	            break;

	        case HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED:
	            System.out.println("407 Proxy Authentication Required");
	            break;

	        case HttpServletResponse.SC_REQUEST_TIMEOUT:
	            System.out.println("408 Request Timeout");
	            break;

	        case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
	            System.out.println("500 Internal Server Error");
	            break;

	        case HttpServletResponse.SC_NOT_IMPLEMENTED:
	            System.out.println("501 Not Implemented");
	            break;

	        case HttpServletResponse.SC_BAD_GATEWAY:
	            System.out.println("502 Bad Gateway");
	            break;

	        case HttpServletResponse.SC_SERVICE_UNAVAILABLE:
	            System.out.println("503 Service Unavailable");
	            break;

	        case HttpServletResponse.SC_GATEWAY_TIMEOUT:
	            System.out.println("504 Gateway Timeout");
	            break;

	        case HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED:
	            System.out.println("505 HTTP Version Not Supported");
	            break;

	        default:
	            
	            System.out.println("알 수 없는 코드: " + stateCode);
	            break;
	    }
	}

}
