package rental.controller.admin.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.TotalDao;

import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/annual")
public class AnnualController extends HttpServlet {
    private TotalDao totalDao = new TotalDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        Map<String, Integer> annualRevenue = totalDao.AnnualController(sno);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        String jsonResponse = mapper.writeValueAsString(annualRevenue);

        resp.getWriter().write(jsonResponse);
       
    }
}
