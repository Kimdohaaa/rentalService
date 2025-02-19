package rental.controller.admin.board;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.AdminDao;
import rental.model.dto.StoreDto;


// 수정 http://localhost:8080/rental/store/info/status
@WebServlet("/store/info/status")
public class AdminStaController extends HttpServlet {

	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("확인");
		
		ObjectMapper mapper = new ObjectMapper();
		StoreDto storeDto = mapper.readValue( req.getReader() , StoreDto.class);
		
		boolean result = AdminDao.getInstance().status(storeDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	}//f end
	
	
	
	
}//class end
