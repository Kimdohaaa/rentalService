package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalDao extends Dao {

    // [7] 총매출 조회 SQL 함수
    public List<Map<String, Integer>> getTotalSales() {
        List<Map<String, Integer>> result = new ArrayList<>();

        try {
            // SQL 쿼리: rental 테이블에서 총매출 계산
            String sql = "SELECT SUM(rprice) AS total_sales FROM rental";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // 쿼리 실행 후 결과가 있으면
            if (rs.next()) {
                Map<String, Integer> map = new HashMap<>();
                map.put("totalSales", rs.getInt("total_sales"));
                result.add(map);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;  // 총매출을 반환
    }

	public Map<String, Integer> AnnualController(String sno) {
		 Map<String, Integer> annualResult = new HashMap<>();
		 
		 for (int i = 2020; i < 2026; i++) {
			 annualResult.put(String.valueOf(i), 0);  
		    }
		 
		 try {
			 String sql = "SELECT s.sname AS store_name, YEAR(r.rdate) AS year, SUM(r.rprice) AS annual_revenue "
				        + "FROM rental r "
				        + "JOIN store s ON r.sno = s.sno "
				        + "where s.sno = ? "
				        + "GROUP BY s.sname, YEAR(r.rdate) "
				        + "ORDER BY s.sname ASC, year ASC";
			 	PreparedStatement ps = conn.prepareStatement(sql);
			 	ps.setString(1, sno);
			 	
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                String year = String.valueOf(rs.getInt("year")); 
	                int annualRevenue = rs.getInt("annual_revenue"); 
	                annualResult.put(year, annualRevenue);
	            }
		 }catch (Exception e) {
			System.out.println(e);
		}
		return annualResult;
	}

	public ArrayList<Integer> MonthController(String sno) {
	    ArrayList<Integer> monthResult = new ArrayList<>();
	    
	    for (int i = 0; i < 12; i++) {
	        monthResult.add(0);  // 1일부터 31일까지 모두 0으로 초기화
	    }
		
		try {
			String sql = "SELECT s.sname AS store_name, MONTH(r.rdate) AS month, SUM(r.rprice) AS month_revenue "
					+ "FROM rental r "
					+ "JOIN store s ON r.sno = s.sno "
					+ "WHERE s.sno = ? AND YEAR(r.rdate) = YEAR(CURDATE()) "
					+ "GROUP BY s.sname, MONTH(r.rdate)"
					+ "ORDER BY s.sname ASC, month ASC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sno);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int month = rs.getInt("month");
	            int monthRevenue = rs.getInt("month_revenue");  
	            monthResult.set(month-1 , monthRevenue);  
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e);  
	    }
	    
	    return monthResult;  
	}

	public ArrayList<Integer> DayController(String sno) {
		ArrayList<Integer> dayResult = new ArrayList<>();
		
		// 먼저, 해당 월의 모든 날짜에 대해 0으로 초기화
	    for (int i = 0; i < 31; i++) {
	        dayResult.add(0);  // 1일부터 31일까지 모두 0으로 초기화
	    }

		try {
			String sql = "SELECT s.sname AS store_name, DAY(r.rdate) AS date, SUM(r.rprice) AS day_revenue "
					+ "FROM rental r "
					+ "JOIN store s ON r.sno = s.sno "
					+ "WHERE s.sno = ? AND YEAR(r.rdate) = YEAR(CURDATE()) "
					+ "AND MONTH(r.rdate) = MONTH(CURDATE()) "
					+ "GROUP BY s.sname, DAY(r.rdate) "
					+ "ORDER BY s.sname ASC, date ASC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int day = rs.getInt("date");
				int dayRevenue = rs.getInt("day_revenue");
				dayResult.set(day-1, dayRevenue);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return dayResult;
	}
}
	