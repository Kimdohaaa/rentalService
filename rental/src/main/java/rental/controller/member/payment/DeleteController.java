package rental.controller.member.payment;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.RentalDao;
import rental.model.dto.PaymentDto;
import rental.model.dto.RentalDto;

@WebServlet("/rental/delete")
public class DeleteController extends HttpServlet {

	// [1] 아임포트 환불
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Delete");
		
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		
		System.out.println(rentalDto);
		
		boolean result = RentalDao.getInstance().delete(rentalDto);
		
		System.out.println("결과 : " + result);
		
		SendResponse.JsonResponse(resp, result);
	}
	
	// [2] 환불할 정보 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int rno = Integer.parseInt(req.getParameter("rno"));
		
		PaymentDto paymentDto = RentalDao.getInstance().findPay(rno);
		
		System.out.println(paymentDto);

	
		SendResponse.JsonResponse(resp, paymentDto);
	}
	
	// [3] DB 환불 처리
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PaymentDto paymentDto = mapper.readValue(req.getReader(), PaymentDto.class);
		
		boolean result = RentalDao.getInstance().refund(paymentDto);
		
		SendResponse.JsonResponse(resp, result);
	}
	
}
