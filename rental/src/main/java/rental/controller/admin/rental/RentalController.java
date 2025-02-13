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
import rental.model.dao.AdminRentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/admin/rental")
public class RentalController extends HttpServlet{
	// 대여 신청 컨트롤러
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
	    
	    HttpSession session = req.getSession();
	    Object object = session.getAttribute("loginMno");
	    
	    if (object != null) {
	        int loginMno = (Integer) object;
	        rentalDto.setMno(loginMno);
	    }

	    // 🔹 기존 예약 여부 확인 (예약 불가능한 경우 처리)
	    if (AdminRentalDao.getInstance().checkRental(rentalDto)) {
	        resp.setContentType("application/json");
	        resp.getWriter().print(false);
	        return; // 더 이상 진행하지 않고 종료
	    }

	    // 🔹 예약 가능하면 추가 진행
	    boolean result = AdminRentalDao.getInstance().add(rentalDto);
	    
	    resp.setContentType("application/json");
	    resp.getWriter().print(result);
	}

	// 가맹점별 대여 현황 조회 컨트롤러
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sno = Integer.parseInt(req.getParameter("sno"));
		int page = Integer.parseInt(req.getParameter("page"));
		int display = 10;
		int startRow = (page-1) * display;
		int totalSize = AdminRentalDao.getInstance().getTotalSize(sno);
		int totalPage = 0;
		if(totalSize % display == 0) {
			totalPage = totalSize / display;
		}else {
			totalPage = totalSize / display + 1;
		}
		int btnSize = 10;
		int startBtn = ((page-1)/btnSize) * btnSize+1;
		int endBtn = startBtn + (btnSize - 1);
		if(endBtn > totalPage) endBtn = totalPage;
		
		ArrayList<RentalDto> result = AdminRentalDao.getInstance().findAll(sno, startRow, display);
		
		PageDto pageDto = new PageDto();
		pageDto.setTotalCount(totalSize);
		pageDto.setPage(page);
		pageDto.setTotalpage(totalPage);
		pageDto.setStartbtn(startBtn);
		pageDto.setEndbtn(endBtn);
		pageDto.setData(result);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(pageDto);
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
	// 대여 인원수 수정 컨트롤러
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
	boolean result = AdminRentalDao.getInstance().updatePerson(rentalDto);
	resp.setContentType("application/json");
	resp.getWriter().print(result);
	}
}
