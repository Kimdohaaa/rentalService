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
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.TotalDao;

@WebServlet("/admin/month")
public class MonthController extends HttpServlet{
	private TotalDao totalDao = new TotalDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. URL 파라미터로부터 sno 값을 받음
        String sno = req.getParameter("sno");

        // 2. 연도별 매출 데이터를 HashMap 형태로 조회
        ArrayList<Integer> monthRevenue = totalDao.MonthController(sno);

        // 3. 응답을 JSON 형식으로 처리하기 위한 ObjectMapper 생성
        SendResponse.JsonResponse(resp, monthRevenue);
    }
}