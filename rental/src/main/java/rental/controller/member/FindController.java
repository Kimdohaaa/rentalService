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

@WebServlet("/member/find")
public class FindController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Member Find Post");
		
		ObjectMapper mapper = new ObjectMapper();
		
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class);
		
		MemberDto mpwd = MemberDao.getInstance().findMpwd(memberDto);
		System.out.println(mpwd);
		
		String jsonResult = mapper.writeValueAsString(mpwd);
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
		
		
	}
}
