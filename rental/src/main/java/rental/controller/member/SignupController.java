package rental.controller.member;

import java.io.IOException;



import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.MemberDao;
import rental.model.dto.MemberDto;

@WebServlet("/member/signup")
public class SignupController extends HttpServlet{
	
	// [1] 회원 회원가입
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Signup Post");
		
		// JS 에서 성별 입력값 int 타입으로 변환하여 controller 로 보내기!
		
		ObjectMapper mapper = new ObjectMapper();
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class);
		
		System.out.println(memberDto);
		
		boolean result = MemberDao.getInstance().signup(memberDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
}
