package rental.controller.member.rental;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.controller.clean.SendResponse;
import rental.model.dao.admin.AdminRentalDao;
import rental.model.dao.member.RentalDao;
import rental.model.dto.RentalDto;

@WebServlet("/rental/state")
public class RListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
	    try {
	        System.out.println(">> Rental List Get");

	        int sno = Integer.parseInt(req.getParameter("sno"));
	        String rdate = req.getParameter("rdate");

	        System.out.println(sno);
	        System.out.println(rdate);

	       
	        ArrayList<String> result = RentalDao.getInstance().findAll(sno, rdate);

	        // 정상적인 요청인 경우 상태 코드 200 (OK)
	        //resp.setStatus(HttpServletResponse.SC_OK);

	        // SendResponse.mapping으로 JSON 응답을 전송
	        SendResponse.mapping(resp, result);

	        // 상태 코드 확인
	        int stateCode = resp.getStatus();
	   

	        // 상태 코드에 맞는 출력 처리
	        SendResponse.mappingCodeCheck(stateCode);

	    } catch (Exception e) {
	        // 예외 발생 시 상태 코드 400 (Bad Request)
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        SendResponse.mappingCodeCheck(resp.getStatus());
	    }
	}

		
}
