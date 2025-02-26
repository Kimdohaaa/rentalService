package rental.controller.admin.rental;

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
import rental.model.dao.admin.AdminRentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/admin/rental")
public class RentalController extends HttpServlet{
	// 대여 신청 컨트롤러
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		RentalDto rentalDto = RequestParsing.jsonToDto(req, RentalDto.class);
		
	    HttpSession session = req.getSession();
	    Object object = session.getAttribute("loginMno");
	    
	    if (object != null) {
	        int loginMno = (Integer) object;
	        rentalDto.setMno(loginMno);
	    }

	    // 🔹 기존 예약 여부 확인 (예약 불가능한 경우 처리)
	    if (AdminRentalDao.getInstance().checkRental(rentalDto)) {
	       SendResponse.JsonResponse(resp, false);
	    	return; // 더 이상 진행하지 않고 종료
	    }

	    // 🔹 예약 가능하면 추가 진행
	    boolean result = AdminRentalDao.getInstance().add(rentalDto);
	    
	    SendResponse.JsonResponse(resp, result);
	}

	// 가맹점별 대여 현황 조회 컨트롤러
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sno = Integer.parseInt(req.getParameter("sno"));
		int page = Integer.parseInt(req.getParameter("page"));
		int display = 10;
		int totalSize = AdminRentalDao.getInstance().getTotalSize(sno);
		
		PageDto pageDto = Pagination.getPageDto(page, display, totalSize, 10);
		ArrayList<RentalDto> result = AdminRentalDao.getInstance().findAll(sno, pageDto.getStartRow(), display);
		
		pageDto.setData(result);
		
		SendResponse.JsonResponse(resp, pageDto);
	}
	// 대여 인원수 수정 컨트롤러
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RentalDto rentalDto = RequestParsing.jsonToDto(req, RentalDto.class);
		
		boolean result = AdminRentalDao.getInstance().updatePerson(rentalDto);

		SendResponse.JsonResponse(resp, result);
	}
}
