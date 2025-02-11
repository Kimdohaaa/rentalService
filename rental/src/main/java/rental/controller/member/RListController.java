package rental.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.RentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/rental/state")
public class RListController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Rental List Get");
		
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		
		ArrayList<String> result = RentalDao.getInstance().findAll(rentalDto);
		
		System.out.println(result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	}
}
