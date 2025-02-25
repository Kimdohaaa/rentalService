package rental.controller.member.user;

import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.MemberDao;
import rental.model.dto.MemberDto;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{

	// [1] 회원 로그인
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Login Post");
		
		// 로그인 상태 저장 변수
		boolean result = false;
		
		ObjectMapper mapper = new ObjectMapper();
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class);
		
		int loginMno = MemberDao.getInstance().login(memberDto);

		if(loginMno > 0) {
			HttpSession session = req.getSession();
			
			session.setAttribute("loginMno", loginMno);
			
			// 세션 객체 활성화 시간 설정 시 추가하기~
			
			Object object = session.getAttribute("loginMno");
			
			if(object != null) {
				result = true;
			}
		}
		
		SendResponse.JsonResponse(resp, result);
	}
	
	// [2] 회원 로그아웃
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Logout Delete");
		
		// 로그아웃 상태 저장 변수 
		boolean result = false;
		
		// 세션 객체
		HttpSession session = req.getSession();
		
		// 로그인된 회원번호 가져오기
		Object obj = session.getAttribute("loginMno");
		
		if(obj != null) { // 로그인 상태라면 
			// 세션에서 지우기
			session.removeAttribute("loginMno");
			// 로그아웃 상태 true
			result = true;
		}
		
		SendResponse.JsonResponse(resp, result);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ewfwf");
	}
	
	
}
