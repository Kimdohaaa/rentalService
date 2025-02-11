package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.AdminDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AdminDao extends Dao {
	
	@Getter
	private static AdminDao instance = new AdminDao();

	
	//boolean response
	public boolean login(AdminDto adminDto) {
		try {
			String sql = "select ano from member admin where aid = ? and apw = ? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, adminDto.getAid());
			ps.setString(2, adminDto.getApwd());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				return true;
			}
		
				
		}catch (Exception e) {
			
		}
		return false;
	}//f end
	
	
}//class end
