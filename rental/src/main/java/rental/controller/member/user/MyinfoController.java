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
		
		
		SendResponse.JsonResponse(resp, result);
		
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
		
		System.out.println(">> 수정 성별" + memberDto.getMgender());
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		

		if(obj != null) {
			int mno = (Integer)obj;
			
			memberDto.setMno(mno);
			
			result = MemberDao.getInstance().update(memberDto);
		}
		
		SendResponse.JsonResponse(resp, result);
		
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
		
		SendResponse.JsonResponse(resp, result);

	}
}
