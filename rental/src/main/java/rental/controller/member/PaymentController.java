package rental.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.RentalDao;
import rental.model.dto.PaymentDto;

@WebServlet("/rental/pay")
public class PaymentController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> 결제 POST");
		
		ObjectMapper mapper = new ObjectMapper();
		PaymentDto paymentDto = mapper.readValue(req.getReader(), PaymentDto.class);
		
		System.out.println(paymentDto);
		boolean result = RentalDao.getInstance().pay(paymentDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	}
}
