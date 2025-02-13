package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.RentalDto;
import rental.model.dto.StoreDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RentalDao extends Dao{

	@Getter
	private static RentalDao instance = new RentalDao();
	
	
	// [1] 대여된 내역 조회 (결과에 따라 버튼 예약 가능 여부 처리)
	public ArrayList<String> findAll(int sno , String rdate) {
		ArrayList<String> rList = new ArrayList<>();
		try {
			String sql = "select rtime from rental where rdate = ? and rstate = 1 and sno= ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rdate);
			ps.setInt(2, sno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String rtime = rs.getString("rtime");
				
				rList.add(rtime);
				System.out.println(rList.toString());
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return rList;
	}
	
	// [2] 사용자 대여 신청
	public boolean addRental(RentalDto rentalDto) {
		try {
			String insert = "insert into rental (rcount, rdate, rtime, rstate, sno, mno, rprice)"
					+ "	values (?, ? , ? , 1 , ?, ?, ?*10000);";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, rentalDto.getRcount());
			ps.setString(2, rentalDto.getRdate());
			ps.setString(3, rentalDto.getRtime());
			ps.setInt(4, rentalDto.getSno());
			ps.setInt(5, rentalDto.getMno());
			ps.setInt(6, rentalDto.getRcount());
				
				int count = ps.executeUpdate();
				
				if(count == 1) {
					return true;
				}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// [3] 현재 로그인된 회원의 대여 내역
	public ArrayList<RentalDto> find(int mno) {
		ArrayList<RentalDto> list = new ArrayList<RentalDto>();
		try {
			String sql = "select r.* , s.sname from rental r join store s on r.sno = s.sno where r.mno = ? and rstate = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RentalDto rentalDto = new RentalDto();
				
				rentalDto.setRno(rs.getInt("rno"));
				rentalDto.setRdate(rs.getString("rdate"));
				rentalDto.setRtime(rs.getString("rtime"));
				rentalDto.setRstate(rs.getInt("rstate"));
				rentalDto.setRcount(rs.getInt("rcount"));
				rentalDto.setRprice(rs.getInt("rprice"));
				rentalDto.setSname(rs.getString("sname"));
				
				list.add(rentalDto);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	// [4] 대여 수정
	public boolean update(RentalDto rentalDto) {
		try {
			String sql = "update rental set rcount=? , rprice = ? * 10000 where rno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rentalDto.getRcount());
			ps.setInt(2, rentalDto.getRcount());
			ps.setInt(3, rentalDto.getRno());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// [4] 대여 삭제
	public boolean delete(RentalDto rentalDto) {
		try {
			System.out.println("취소사유" + rentalDto.getRreason());
			
			String sql = "update rental set rstate = 0 , rprice = 0 , rreason=? where rno=? " ;
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, rentalDto.getRreason());
			ps.setInt(2, rentalDto.getRno());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				System.out.println("취소사유" + rentalDto.getRreason());
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	// 가맹점 조회 (index 페이지 출력하기)
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
				storeDto.setSimg(rs.getString("simg"));
			
				sList.add(storeDto);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return sList;
	}
}
