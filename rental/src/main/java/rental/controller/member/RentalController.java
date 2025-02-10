package rental.controller.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/rental")
public class RentalController extends HttpServlet {

	// [1] 대여 신청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Post");
	}
	
	// [2] 대여 현황 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Get");
	}
	
	// [3] 대여 신청 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Put");
	}
	
	// [4] 대여 취소
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Delete");
	}
}
