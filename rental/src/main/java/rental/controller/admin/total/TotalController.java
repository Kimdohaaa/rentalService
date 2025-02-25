package rental.controller.admin.total;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.TotalDao;

@WebServlet("/admin/total")
public class TotalController extends HttpServlet {
    
    // TotalDao 인스턴스 생성
    private TotalDao totalDao = new TotalDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 총매출 조회
        List<Map<String, Integer>> totalSalesList = totalDao.getTotalSales();

        // 2. 응답을 JSON 형식으로 처리하기 위한 ObjectMapper 생성

        SendResponse.JsonResponse(resp, totalSalesList);
    }
}
