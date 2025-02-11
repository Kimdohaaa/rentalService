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
}
