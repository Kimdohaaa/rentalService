package rental.controller.admin.rental;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.model.dao.AdminRentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/admin/rental")
public class RentalController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		HttpSession session = req.getSession();
		Object object = session.getAttribute("loginMno");
		if(object != null) {
			int loginMno = (Integer)object;
			rentalDto.setMno(loginMno);
		}
		boolean result = AdminRentalDao.getInstance().add(rentalDto);
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
