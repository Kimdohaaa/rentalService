package rental.controller.member.rental;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.controller.clean.Pagination;
import rental.controller.clean.RequestParsing;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.RentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/member/rental")
public class RentalController extends HttpServlet {

	// [1] 대여 신청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Post");
		
		RentalDto rentalDto = RequestParsing.jsonToDto(req, RentalDto.class);
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		
		
		int result = 0;
		if(obj != null) {
			int loginMno = (Integer)obj;
			rentalDto.setMno(loginMno);
			result = RentalDao.getInstance().addRental(rentalDto);
		}
		
		SendResponse.JsonResponse(resp, result);
	}
	
	// [2] 대여 현황 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Get");
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		
		int mno = 0;
		
		
		if(obj != null) {
			mno = (Integer)obj;
		}
		///
		int page = Integer.parseInt(req.getParameter("page"));
		int display = 10;
		int totalSize = RentalDao.getInstance().getTotalSize(mno);
		
		
		PageDto pageDto = Pagination.getPageDto(page, display, totalSize, 10);
		///
		
		
		ArrayList<RentalDto> rentalDto = RentalDao.getInstance().find(mno, pageDto.getStartRow(), display);
		
		
		pageDto.setData(rentalDto);
		
		
		SendResponse.JsonResponse(resp, pageDto);
	}
	
	// [3] 대여 신청 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Put");
		
		RentalDto rentalDto = RequestParsing.jsonToDto(req, RentalDto.class);
		
		System.out.println("test마스터수정인원확인 : " + rentalDto.getRcount());
		
		boolean result = RentalDao.getInstance().update(rentalDto);
		
		SendResponse.JsonResponse(resp, result);
	}
	
}
