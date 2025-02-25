package rental.controller.admin.rental;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminRentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/admin/cancel")
public class CancelController extends HttpServlet{
	// 대여 상태(취소) 수정 컨트롤러
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		RentalDto rentalDto = mapper.readValue(req.getReader(), RentalDto.class);
		boolean result = AdminRentalDao.getInstance().updateState(rentalDto);
	
		SendResponse.JsonResponse(resp, result);
	}
	// 총 대여 취소 사유 조회 컨트롤러
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RentalDto result = AdminRentalDao.getInstance().cancelFindAll();
	    
		ObjectMapper mapper = new ObjectMapper();
		    
		// 필요한 필드만 선택
		ObjectNode customJson = mapper.createObjectNode();
	    customJson.put("공간 협소", result.getRreason0());
	    customJson.put("위생", result.getRreason1());
	    customJson.put("기구 부족", result.getRreason2());
	    customJson.put("기타", result.getRreasonEtcCount());

	    SendResponse.JsonResponse(resp, customJson.toString());
	}
}
