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

@WebServlet("/rowD")
public class RowDController extends HttpServlet{

	// 취소율 낮은 매장
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String rowD = FunctionDao.getInstance().rowD();
		
		
		SendResponse.JsonResponse(resp, rowD);
	}
}
