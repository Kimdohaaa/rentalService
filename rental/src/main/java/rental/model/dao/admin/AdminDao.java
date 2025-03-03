package rental.model.dao.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dao.Dao;
import rental.model.dto.AdminDto;
import rental.model.dto.MemberDto;
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
//	
//	
	// 1. 가맹점 등록
		public boolean name( StoreDto storeDto ) {
			
			
			try {
				String sql = "insert into store ( smno , saddr, sname , lon , lat ,simg )"
						+ "values (? , ? , ? , ? , ? , ?)";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, storeDto.getSmno());
				ps.setString(2, storeDto.getSaddr());
				ps.setString(3, storeDto.getSname());
				ps.setString(4, storeDto.getLon());
				ps.setString(5, storeDto.getLat());
				ps.setString(6, storeDto.getSimg());
				int result = ps.executeUpdate();
				
				if (result == 1) { 
					
					return true;
				}
		
			
			}catch (Exception e) {
				System.out.println(e);
			
		}
			return false;
	
	}//f end
		
		
	// 2. 가맹점 조회
		public ArrayList<StoreDto> findAll() {
			ArrayList<StoreDto> list = new ArrayList<StoreDto>();
			try {
				// 전체 조회
				String sql = "select * from store";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					StoreDto storeDto = new StoreDto();
					storeDto.setSno(rs.getInt("sno"));
					storeDto.setSmno( rs.getString("smno"));
					storeDto.setSaddr(rs.getString("saddr"));
					storeDto.setSname(rs.getString("sname"));
					storeDto.setSstate(rs.getInt("sstate"));
					list.add(storeDto);
					System.out.println(storeDto);
					
					
					
				}
				
			}catch (Exception e) {
				System.out.println(e);
			}
			return list;
			
			
			
		}//f end
		
		//3. 가맹점 수정   사진 수정 추가
		public boolean update(StoreDto storeDto) {
			try {
					String sql = "update store set smno =?, saddr = ?, sname =?  where sno =? ";
					PreparedStatement ps = conn.prepareStatement(sql);
					
					
					ps.setString(1, storeDto.getSmno());
					ps.setString(2, storeDto.getSaddr());
					ps.setString(3, storeDto.getSname());
					ps.setInt(4, storeDto.getSno());
					
					
					int count = ps.executeUpdate();
					System.out.println(ps);
					System.out.println(count);
					if (count == 1) {return true;
						
					}
					
					
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
			
		}// f end
		
		
		
		
		
		
		//5. 가맹점 수정  //{ sno: ", smno: " , saddr: " ,sname , sstate: , reson "} //sql문 정리 절대 해야함
		public boolean status(StoreDto storeDto) {
			try {
				String sql = "update store set sstate = ?  where sno = ?";
				PreparedStatement ps = conn.prepareStatement(sql); //쿼리문 수정해야함
				
				System.out.println(storeDto);
				
			
				ps.setInt(1, storeDto.getSstate()); 
				ps.setInt(2, storeDto.getSno());
				
				
				int count = ps.executeUpdate();
				System.out.println(ps);
				System.out.println(count);
				if (count == 1) {return true;
					
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
			
			
		}// f end
		
		
		// 6. 회원 정보 전체 조회
		public ArrayList<MemberDto> memberFind(){
			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
			try {
				String sql = "select * from member";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					MemberDto memberDto = new MemberDto();
					memberDto.setMno(rs.getInt("mno"));
					memberDto.setMid(rs.getString("mid"));
					memberDto.setMphone(rs.getString("mphone"));
					memberDto.setMgender(rs.getInt("mgender"));
					memberDto.setMstate(rs.getInt("mstate"));
					memberDto.setMdate(rs.getString("mdate"));
					list.add(memberDto);
				}
			} catch (Exception e) {System.out.println(e);}
			return list;
		}
	
		
		
	
	
}//class end
