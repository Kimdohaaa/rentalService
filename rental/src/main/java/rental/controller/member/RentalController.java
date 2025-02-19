package rental.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rental.model.dao.RentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/member/rental")
public class RentalController extends HttpServlet {

	// [1] 대여 신청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Post");
		
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("loginMno");
		
		
		int result = 0;
		if(obj != null) {
			int loginMno = (Integer)obj;
			rentalDto.setMno(loginMno);
			result = RentalDao.getInstance().addRental(rentalDto);
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
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
		int startRow = (page-1) * display;
		int totalSize = RentalDao.getInstance().getTotalSize(mno);
		
		
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
		
		///
		
		
		ArrayList<RentalDto> rentalDto = RentalDao.getInstance().find(mno, startRow, display);
		
		
		PageDto pageDto = new PageDto();
		pageDto.setTotalCount(totalSize);
		pageDto.setPage(page);
		pageDto.setTotalpage(totalPage);
		pageDto.setStartbtn(startBtn);
		pageDto.setEndbtn(endBtn);
		pageDto.setData(rentalDto);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(pageDto);
		
		System.out.println(jsonResult);
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
		
	}
	
	// [3] 대여 신청 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> Member Rental Put");
		
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		
		System.out.println("수정인원확인 : " + rentalDto.getRcount());
		
		boolean result = RentalDao.getInstance().update(rentalDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	
}
