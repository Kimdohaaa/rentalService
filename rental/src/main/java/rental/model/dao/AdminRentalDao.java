package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.RentalDto;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AdminRentalDao extends Dao{
	@Getter
	private static AdminRentalDao instance = new AdminRentalDao();
	
	public boolean add(RentalDto rentalDto) {
		try {
			String sql = "INSERT INTO rental (rdate, rtime, rcount, sno, mno, rprice, rstate)\r\n"
					+ "SELECT ?, ?, ?, ?, m.mno, ? * 10000, 1\r\n"
					+ "FROM member m\r\n"
					+ "WHERE m.mphone = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rentalDto.getRdate());
			ps.setString(2, rentalDto.getRtime());
			ps.setInt(3, rentalDto.getRcount());
			ps.setInt(4, rentalDto.getSno());
			ps.setInt(5, rentalDto.getRcount());
			ps.setString(6, rentalDto.getMphone());
			int count = ps.executeUpdate();
			if(count == 1) {return true;}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	 // 예약 여부 확인 (DB에서 조회)
    
	public boolean checkRental(RentalDto rentalDto) {
        
        try {
        	String sql = "SELECT COUNT(*) FROM rental WHERE rdate = ? AND rtime = ? AND sno = ? AND rstate = 1";
             PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rentalDto.getRdate());
            ps.setString(2, rentalDto.getRtime());
            ps.setInt(3, rentalDto.getSno());
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true; // 기존 예약이 있음 (예약 불가)
            }
        } catch (Exception e) {System.out.println(e);}
        return false; // 예약 없음 (예약 가능)
    }
	
    // 대여 전체 개수 조회 SQL 메소드
    public int getTotalSize(int sno) {
    	try {
    		String sql = "select count(*) from rental where sno = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, sno);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {return rs.getInt(1);}
    	}catch (Exception e) {System.out.println(e);}
    	return 0;
    }
    
    // 대여 전체 조회 SQL 메소드
    public ArrayList<RentalDto> findAll(int sno, int startRow, int display){
    	ArrayList<RentalDto> list = new ArrayList<RentalDto>();
    	try {
    		String sql = "select * from rental r inner join member m on r.mno = m.mno where r.sno = ? order by r.rno desc limit ?, ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, sno);
    		ps.setInt(2, startRow);
    		ps.setInt(3, display);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			RentalDto rentalDto = new RentalDto();
    			rentalDto.setRno(rs.getInt("rno"));
    			rentalDto.setRdate(rs.getString("rdate"));
    			rentalDto.setRtime(rs.getString("rtime"));
    			rentalDto.setRstate(rs.getInt("rstate"));
    			rentalDto.setRcount(rs.getInt("rcount"));
    			rentalDto.setRprice(rs.getInt("rprice"));
    			rentalDto.setMno(rs.getInt("mno"));
    			rentalDto.setSno(rs.getInt("sno"));
    			rentalDto.setMid(rs.getString("mid"));
    			list.add(rentalDto);
    		}
    	}catch (Exception e) {System.out.println(e);}
    	return list;
    }
    // 대여 인원수 수정 SQL 처리 메소드
    public boolean updatePerson(RentalDto rentalDto) {
    	try {
    		String sql = "update rental set rcount = ?, rprice = ?*10000 where rno = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, rentalDto.getRcount());
    		ps.setInt(2, rentalDto.getRcount());
    		ps.setInt(3, rentalDto.getRno());
    		int count = ps.executeUpdate();
    		if(count == 1) {return true;}
    	}catch (Exception e) {System.out.println(e);}
    	return false;
    }
    
    // 대여 상태(취소) 수정 SQL 처리 메소드
    public boolean updateState(RentalDto rentalDto) {
    	try {
    		String sql = "update rental set rstate = 0, rreason = ? where rno = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, rentalDto.getRreason());
    		ps.setInt(2, rentalDto.getRno());
    		int count = ps.executeUpdate();
    		if(count == 1) {return true;}
    	}catch (Exception e) {System.out.println(e);}
    	return false;
    }
    
    // 총 대여 취소 사유 조회 SQL 처리 메소드
    public RentalDto cancelFindAll(){
    	RentalDto rentalDto = new RentalDto();
    	try {
    		String sql = "SELECT \r\n"
    				+ "    COUNT(CASE WHEN rreason = '0' THEN 1 END) AS 헬스장이_좁다,\r\n"
    				+ "    COUNT(CASE WHEN rreason = '1' THEN 1 END) AS 헬스장이_더럽다,\r\n"
    				+ "    COUNT(CASE WHEN rreason = '2' THEN 1 END) AS 기구가_별로_안좋다,\r\n"
    				+ "    COUNT(CASE WHEN rreason NOT IN ('0', '1', '2') AND rreason IS NOT NULL THEN 1 END) AS 기타_개수\r\n"
    				+ "FROM rental";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			rentalDto.setRreason0(rs.getInt("헬스장이_좁다"));
    			rentalDto.setRreason1(rs.getInt("헬스장이_더럽다"));
    			rentalDto.setRreason2(rs.getInt("기구가_별로_안좋다"));
    			rentalDto.setRreasonEtcCount(rs.getInt("기타_개수"));
    			return rentalDto;
    		}
    	}catch (Exception e) {System.out.println(e);}
    	return null;
    }
    public ArrayList<RentalDto> cancelFindEtc(){
        ArrayList<RentalDto> list = new ArrayList<RentalDto>();
        try {
            String sql = "SELECT rreason, rreason_detail FROM rental WHERE rreason NOT IN ('0', '1', '2') AND rreason IS NOT NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            List<String> etcReasons = new ArrayList<>();
            while(rs.next()) {
                // rreason과 rreason_detail을 가져와서 하나의 문자열로 합치기
                String reason = rs.getString("rreason");
                String detail = rs.getString("rreason_detail");
                etcReasons.add(reason + ": " + detail);  // 예: "기타: 헬스장이 너무 좁아서"
            }
            
            if (!etcReasons.isEmpty()) {
                RentalDto rentaldto = new RentalDto();
                rentaldto.setRreasonEtc(String.join(", ", etcReasons));  // 리스트를 콤마로 구분하여 저장
                list.add(rentaldto);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    // 대여된 내역 조회 SQL 메소드
    public ArrayList<String> findStateAll(int sno , String rdate) {
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
}
