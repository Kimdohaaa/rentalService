package rental.controller.admin.rental;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminRentalDao;
import rental.model.dto.StoreDto;

@WebServlet("/admin/dropdown")
public class DropdownController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<StoreDto> result = AdminRentalDao.getInstance().dropdown();
		
		SendResponse.JsonResponse(resp, result);
	}
}
