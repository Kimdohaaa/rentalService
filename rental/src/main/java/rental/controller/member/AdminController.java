package rental.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.model.dao.AdminDao;
import rental.model.dto.AdminDto;

// 반응 확인 http://localhost:8080/rental/admin/login
@WebServlet("/admin/login")
public class AdminController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sucess");
		ObjectMapper mapper = new ObjectMapper();
		AdminDto adminDto = mapper.readValue(req.getReader(), AdminDto.class);
		
		boolean result = AdminDao.getInstance().login(adminDto);
		
		HttpSession session = req.getSession();
		
		session.setAttribute("result", result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
		
	}
	
	

}//f end
