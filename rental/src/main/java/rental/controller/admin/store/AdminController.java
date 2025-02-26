package rental.controller.admin.store;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.controller.clean.RequestParsing;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminDao;
import rental.model.dao.member.MemberDao;
import rental.model.dto.AdminDto;
import rental.model.dto.MemberDto;

// 반응 확인 http://localhost:8080/rental/admin/login
@WebServlet("/admin/login")
public class AdminController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sucess");
		
		AdminDto adminDto = RequestParsing.jsonToDto(req, AdminDto.class);
		
		boolean result = AdminDao.getInstance().login(adminDto);
		
		if(result == true) {
			HttpSession session = req.getSession();
			
			session.setAttribute("loginAno", adminDto.getAid());
		}
		
		SendResponse.JsonResponse(resp, result);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> MyInfo Get");
		
		MemberDto result = null;
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginAno");
		
		String loginAno=null;
		if(obj != null) {
			loginAno = (String)obj;
			
			System.out.println(result);
		}
		
	
		SendResponse.JsonResponse(resp, result);
	}
	
	

}//f end
