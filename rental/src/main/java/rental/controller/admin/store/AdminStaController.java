package rental.controller.admin.store;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.RequestParsing;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminDao;
import rental.model.dto.StoreDto;


// 수정 http://localhost:8080/rental/store/info/status
@WebServlet("/store/info/status")
public class AdminStaController extends HttpServlet {

	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("확인");
		
		StoreDto storeDto = RequestParsing.jsonToDto(req, StoreDto.class);
		
		boolean result = AdminDao.getInstance().status(storeDto);
		
		SendResponse.JsonResponse(resp, result);
		
	}//f end
	
	
	
	
	
	
}//class end
