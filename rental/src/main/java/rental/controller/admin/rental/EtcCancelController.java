package rental.controller.admin.rental;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.Pagination;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminRentalDao;
import rental.model.dto.PageDto;
import rental.model.dto.RentalDto;

@WebServlet("/admin/etc")
public class EtcCancelController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int page = Integer.parseInt(req.getParameter("page"));
		int display = 5;
		int totalSize = AdminRentalDao.getInstance().getTotalSize2();
		
		PageDto pageDto = Pagination.getPageDto(page, display, totalSize, 5);
		
		ArrayList<RentalDto> result = AdminRentalDao.getInstance().cancelFindEtc(pageDto.getStartRow(), display);
		
		
		pageDto.setData(result);


		SendResponse.JsonResponse(resp, pageDto);
	}
}
