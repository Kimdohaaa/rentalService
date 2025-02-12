package rental.controller.member;

import java.io.IOException;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.AdminRentalDao;
import rental.model.dao.RentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/rental/state")
public class RListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Rental List Get");
		
		int sno = Integer.parseInt(req.getParameter("sno"));
		String rdate = req.getParameter("rdate");
		
		System.out.println(sno);
		System.out.println(rdate);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<String> result = RentalDao.getInstance().findAll(sno , rdate);
		
		String jsonResult = mapper.writeValueAsString(result);
		
		System.out.println(jsonResult);
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
		
	}
}
