package rental.controller.admin.rental;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.AdminRentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/admin/cancel")
public class CancelController extends HttpServlet{
	// 대여 상태(취소) 수정 컨트롤러
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
	boolean result = AdminRentalDao.getInstance().updateState(rentalDto);
	resp.setContentType("application/json");
	resp.getWriter().print(result);
	}
	// 총 대여 취소 사유 조회 컨트롤러
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RentalDto result = AdminRentalDao.getInstance().cancelFindAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(result);
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
}
