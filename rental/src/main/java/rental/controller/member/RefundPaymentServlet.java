package rental.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.*;
import java.util.*;

@WebServlet("/rental/refund")
public class RefundPaymentServlet extends HttpServlet {

    // 아임포트 API 키
    private static final String IMP_KEY = "imp51664346";  // 아임포트 가맹점 식별 코드
    private static final String IMP_SECRET = "oMtnC3ipFMPePtD2yNyrppnriwslcpLjbumK6ZHLQOgZadZ2ZXMC3YwJM2MuWcLSySUthRR0FjJ0PgvV"; // 아임포트 비밀키

    // doPost 메소드 (환불 요청을 처리)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 클라이언트에서 전달한 JSON 데이터 읽기
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }
        reader.close();

        // 2. ObjectMapper를 사용해 JSON 문자열을 자바 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        RefundRequest refundRequest = objectMapper.readValue(jsonData.toString(), RefundRequest.class);
        
        String imp_uid = refundRequest.getImp_uid();
        int amount = refundRequest.getAmount();

        // 3. 환불 처리 메소드 호출
        String resultMessage = refundPayment(imp_uid, amount);

        // 4. 응답 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 5. 응답 데이터를 JSON 형식으로 반환
        response.getWriter().write(resultMessage);
    }

    // 아임포트 API를 호출하여 환불을 처리하는 메소드
    private String refundPayment(String imp_uid, int amount) {
        try {
            // 1. 환불 요청을 위한 JSON 데이터 생성
            RefundRequest refundRequest = new RefundRequest(imp_uid, amount);

            // 2. ObjectMapper를 사용해 Java 객체를 JSON으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInputString = objectMapper.writeValueAsString(refundRequest);

            // 3. 아임포트 API 엔드포인트 URL
            String url = "https://api.iamport.kr/payments/cancel";

            // 4. HTTP 요청 보내기
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + getAccessToken()); // 인증 토큰을 사용합니다.
            connection.setDoOutput(true);

            // 5. 요청 바디에 JSON 데이터를 쓰기
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 6. 응답 받기
            try (InputStream is = connection.getInputStream()) {
                // 응답을 JSON으로 파싱
                RefundResponse responseObj = objectMapper.readValue(is, RefundResponse.class);
                
                if (responseObj != null && responseObj.getCode() == 0) {
                    return "{\"success\": true, \"message\": \"환불이 완료되었습니다.\"}";
                } else {
                    return "{\"success\": false, \"message\": \"" + responseObj.getMessage() + "\"}";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"환불 처리 중 오류가 발생했습니다.\"}";
        }
    }

    // 인증 토큰을 가져오는 메소드 (이 예시에서는 가정)
    private String getAccessToken() throws Exception {
        // 실제로 아임포트 인증 API를 호출하여 access token을 받아옵니다.
        return "your_access_token";
    }

    // 환불 요청을 위한 데이터 클래스
    static class RefundRequest {
        private String imp_uid;
        private int amount;

        // 생성자
        public RefundRequest(String imp_uid, int amount) {
            this.imp_uid = imp_uid;
            this.amount = amount;
        }

        // Getter
        public String getImp_uid() {
            return imp_uid;
        }

        public int getAmount() {
            return amount;
        }
    }

    // 환불 응답을 위한 데이터 클래스
    static class RefundResponse {
        private int code;
        private String message;

        // Getter
        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
