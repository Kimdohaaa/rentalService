package rental.controller.member.user;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.MemberDao;
import rental.model.dto.MemberDto;

@WebServlet("/member/find")
public class FindController extends HttpServlet{
	
	// 비밀번호찾기
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Member Find Post");
		
		ObjectMapper mapper = new ObjectMapper();
		
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class);
		
		MemberDto mpwd = MemberDao.getInstance().findMpwd(memberDto);
		
		// 클린코딩 테스트
		SendResponse.JsonResponse(resp, mpwd);
		
	}
}
