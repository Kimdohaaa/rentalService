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

@WebServlet("/admin/state")
public class AdminRListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sno = Integer.parseInt(req.getParameter("sno"));
		String rdate = req.getParameter("rdate");
		
		System.out.println(sno);
		System.out.println(rdate);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<String> result = AdminRentalDao.getInstance().findStateAll(sno , rdate);
		
		String jsonResult = mapper.writeValueAsString(result);
		
		System.out.println(jsonResult);
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
}
