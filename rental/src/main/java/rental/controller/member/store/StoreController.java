package rental.controller.member.store;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.member.RentalDao;
import rental.model.dto.StoreDto;
@WebServlet("/member/store")
public class StoreController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println(">> Member Store Get");
			
			ArrayList<StoreDto> storeDto = RentalDao.getInstance().findStore();
			
			
			SendResponse.mapping(resp, storeDto);
		}catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        SendResponse.mappingCodeCheck(resp.getStatus());
		}
	}
}
