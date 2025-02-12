package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.AdminDto;
import rental.model.dto.StoreDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AdminDao extends Dao {
	
	@Getter
	private static AdminDao instance = new AdminDao();

	
	//boolean response admin 로그인
	public boolean login(AdminDto adminDto) {
		System.out.println(adminDto);
		try {
			String sql = "select ano from admin where aid = ? and apwd = ? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, adminDto.getAid());
			ps.setString(2, adminDto.getApwd());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				return true;
			}
		
				
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}//f end
	
	
	// 1. 가맹점 등록
		public void name() {
			
			ArrayList<StoreDto> sList = new ArrayList<StoreDto>();
			try {
				String sql = "insert into store ( smno , saddr, sname )"
						+ "values (? , ? , ?)";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					StoreDto storeDto = new StoreDto();
					
					storeDto.setSno(rs.getInt("sno"));
					storeDto.setSmno(rs.getString("smno"));
					storeDto.setSaddr(rs.getString("saddr"));
					storeDto.setSname(rs.getString("sname"));
				
					sList.add(storeDto);
				}
			}catch (Exception e) {
				System.out.println(e);
			
		}
	
	}//f end
	
	
	
	// 2. 가맹점 조회
		public ArrayList<StoreDto> findStore() {
			ArrayList<StoreDto> sList = new ArrayList<StoreDto>();
			try {
				String sql = "select * from store where sstate = 1";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					StoreDto storeDto = new StoreDto();
					
					storeDto.setSno(rs.getInt("sno"));
					storeDto.setSmno(rs.getString("smno"));
					storeDto.setSaddr(rs.getString("saddr"));
					storeDto.setSname(rs.getString("sname"));
				
					sList.add(storeDto);
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			return sList;
		}//f end
	
	
	
	
}//class end
