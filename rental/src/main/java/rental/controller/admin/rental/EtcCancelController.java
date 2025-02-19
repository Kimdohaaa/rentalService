package rental.controller.admin.rental;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.AdminRentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/admin/etc")
public class EtcCancelController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int page = Integer.parseInt(req.getParameter("page"));
		int display = 5;
		int startRow = (page-1) * display;
		int totalSize = AdminRentalDao.getInstance().getTotalSize2();
		int totalPage = 0;
		if(totalSize % display == 0) {
			totalPage = totalSize / display;
		}else {
			totalPage = totalSize / display + 1;
		}
		int btnSize = 5;
		int startBtn = ((page - 1) / btnSize) * btnSize+1;
		int endBtn = startBtn + (btnSize - 1);
		if(endBtn > totalPage) endBtn = totalPage;
		ArrayList<RentalDto> result = AdminRentalDao.getInstance().cancelFindEtc(startRow, display);
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
}
