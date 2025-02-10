package rental.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.model.dao.MemberDao;
import rental.model.dto.MemberDto;

@WebServlet("/member/myinfo")
public class MyinfoController extends HttpServlet{
	
	// [1] 내정보 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> MyInfo Get");
		
		MemberDto result = null;
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		
		if(obj != null) {
			int loginMno = (Integer)obj;
			
			result = MemberDao.getInstance().myinfo(loginMno);
			
			System.out.println(result);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonresult = mapper.writeValueAsString(result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonresult);
		
		// JS 에서 성별 , 상태 String 으로 변환하여 출력하기
	}
	
	// [2] 내정보 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> MyInfo Put");
		
		// JS 에서 mgender int 타입으로 변환히기
		
		boolean result = false;
		
		ObjectMapper mapper = new ObjectMapper();
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class);
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		

		if(obj != null) {
			int mno = (Integer)obj;
			
			memberDto.setMno(mno);
			
			result = MemberDao.getInstance().update(memberDto);
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	}
	
	// [3] 회원탈퇴
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> MyInfo Delete");
		
		boolean result = false;
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		
		if(obj != null) {
			int mno = (Integer)obj;
			
			result = MemberDao.getInstance().delete(mno);
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
		// mstate 1 일 시 로그인 불가하도록 유효성 검사 넣기
	}
}
