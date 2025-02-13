package rental.controller.member;

import java.io.Console;
import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.RentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/rental/delete")
public class DeleteController extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Delete");
		
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		
		System.out.println(rentalDto);
		
		boolean result = RentalDao.getInstance().delete(rentalDto);
		
		System.out.println("결과 : " + result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
}
