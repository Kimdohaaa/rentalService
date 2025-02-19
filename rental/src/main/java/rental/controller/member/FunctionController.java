package rental.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.FunctionDao;

@WebServlet("/sales")
public class FunctionController extends HttpServlet {

	
	// 매출 가장 높은 매장
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(">> sales Get");
		String top = FunctionDao.getInstance().sales();
		
		System.out.println(top);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(top);
		
		System.out.println("ㅎㅎ" + jsonResult);
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
	
	
	
	
}
