package rental.controller.admin.total;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.toalDao.TotalDao;

@WebServlet("/admin/day")
public class DayController extends HttpServlet{
	private TotalDao totalDao = new TotalDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sno = req.getParameter("sno");
		ArrayList<Integer> dayRevenue = totalDao.DayController(sno);
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json");
		String jsonResult = mapper.writeValueAsString(dayRevenue);
		resp.getWriter().write(jsonResult);
	}
}
