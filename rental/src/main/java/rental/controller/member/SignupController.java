package rental.controller.member;

import java.io.IOException;
import java.util.ArrayList;

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
		
		ArrayList<MemberDto> getMember = MemberDao.getInstance().getMember();
		System.out.println(getMember);
		
		resp.setContentType("application/json");
		
		// 유효성 검사
		int check = check(memberDto, getMember);
		
		int result = 0;
		
		if(check == 0 ) {
			result = MemberDao.getInstance().signup(memberDto);
			resp.getWriter().print(result);
		}else if(check == 2){
			resp.getWriter().print(check);
		}else if(check == 3) {
			resp.getWriter().print(check);
		}else if(check == 4) {
			resp.getWriter().print(check);
		}
		
		
	}
	
	// [2] 회원가입 유효성 검사
	public int check(MemberDto memberDto, ArrayList<MemberDto> getMember) {
		
		int check = 0;
		
		for(int i = 0; i < getMember.size(); i++) {
			
			// 아이디 중복 검사
			if(memberDto.getMid().equals( getMember.get(i).getMid())) {
				check = 2;
				break;
			}
			// 전화번호 중복 검사
			if(memberDto.getMphone().equals( getMember.get(i).getMphone())) {
				check = 3;
				break;
			}
			// 전화번호 길이 검사
			if(memberDto.getMphone().length() < 0 || memberDto.getMphone().length() > 13) {
				check = 4;
				break;
			}

		}
		
		return check;
	}
}
