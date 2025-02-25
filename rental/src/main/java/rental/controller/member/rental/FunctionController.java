package rental.controller.member.rental;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.FunctionDao;

@WebServlet("/sales")
public class FunctionController extends HttpServlet {

	
	// 매출 가장 높은 매장
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(">> sales Get");
		String top = FunctionDao.getInstance().sales();
		
		System.out.println(top);
		
		SendResponse.JsonResponse(resp, top);
	}
	
	
	
	
}
