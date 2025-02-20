package rental.controller.member.store;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.member.RentalDao;
import rental.model.dto.StoreDto;
@WebServlet("/member/store")
public class StoreController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Store Get");
		
		ArrayList<StoreDto> storeDto = RentalDao.getInstance().findStore();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(storeDto);
		
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
}
