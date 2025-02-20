package rental.model.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dao.Dao;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FunctionDao extends Dao {
	
	@Getter
	private static FunctionDao instance = new FunctionDao();
	
	// 현재 년도 매출 가장 높은 매장
	public String sales() {
		try {
			String sql = "SELECT s.sname AS store_name, YEAR(r.rdate) AS year, SUM(r.rprice) AS anunnal_revenue \r\n"
					+ "FROM rental r \r\n"
					+ "JOIN store s ON r.sno = s.sno \r\n"
					+ "where YEAR(r.rdate) = YEAR(CURDATE()) \r\n"
					+ "GROUP BY s.sname, YEAR(r.rdate) \r\n"
					+ "ORDER BY anunnal_revenue desc limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String topS = rs.getString("store_name");
				
				return topS;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 취소율 낮은 매장 조회
	public String rowD() {
		try {
			String sql = "SELECT \r\n"
					+ "    s.sno,\r\n"
					+ "    s.sname,\r\n"
					+ "    COUNT(r.rreason) AS 취소_사유_건수\r\n"
					+ "FROM \r\n"
					+ "    store s\r\n"
					+ "LEFT JOIN \r\n"
					+ "    rental r ON s.sno = r.sno\r\n"
					+ "WHERE \r\n"
					+ "    r.rstate = 0 \r\n"
					+ "GROUP BY \r\n"
					+ "    s.sno, s.sname\r\n"
					+ "ORDER BY \r\n"
					+ "    취소_사유_건수 ASC\r\n"
					+ "LIMIT 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String sname = rs.getString("sname");
				
				return sname;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

}
