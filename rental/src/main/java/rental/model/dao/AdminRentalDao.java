package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.RentalDto;
import rental.model.dto.StoreDto;


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
    		String sql = "update rental set rdate = ?, rtime = ?, rcount = ?, rprice = ?*10000, sno = ?, rstate = 1 where rno = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, rentalDto.getRdate());
    		ps.setString(2, rentalDto.getRtime());
    		ps.setInt(3, rentalDto.getRcount());
    		ps.setInt(4, rentalDto.getRcount());
    		ps.setInt(5, rentalDto.getSno());
    		ps.setInt(6, rentalDto.getRno());
    		int count = ps.executeUpdate();
    		if(count == 1) {return true;}
    	}catch (Exception e) {System.out.println(e);}
    	return false;
    }
    
    // 대여 상태(취소) 수정 SQL 처리 메소드
    public boolean updateState(RentalDto rentalDto) {
        try {
            // 기본 SQL 문
            String sql = "UPDATE rental SET rstate = 0, rprice = 0, rreason = ? WHERE rno = ?";
            
            // rreason 값이 '기타'일 경우 rreason_detail도 함께 업데이트
            if ("reason".equals(rentalDto.getRreason())) {
                sql = "UPDATE rental SET rstate = 0, rprice = 0, rreason = ?, rreason_detail = ? WHERE rno = ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            
            // '기타'일 경우와 아닐 경우에 따라 파라미터 설정
            if ("reason".equals(rentalDto.getRreason())) {
                ps.setString(1, rentalDto.getRreason());
                ps.setString(2, rentalDto.getRreasonEtc()); // rreason_detail 값 설정
                ps.setInt(3, rentalDto.getRno());
            } else {
                ps.setString(1, rentalDto.getRreason());
                ps.setInt(2, rentalDto.getRno());
            }
            
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
    // 총 대여 취소 사유 조회 SQL 처리 메소드
    public RentalDto cancelFindAll(){
    	RentalDto rentalDto = new RentalDto();
    	try {
    		String sql = "SELECT \r\n"
    				+ "    COUNT(CASE WHEN rreason = '0' THEN 1 END) AS 공간_협소,\r\n"
    				+ "    COUNT(CASE WHEN rreason = '1' THEN 1 END) AS 위생,\r\n"
    				+ "    COUNT(CASE WHEN rreason = '2' THEN 1 END) AS 기구_부족,\r\n"
    				+ "    COUNT(CASE WHEN rreason NOT IN ('0', '1', '2') AND rreason IS NOT NULL THEN 1 END) AS 기타_개수\r\n"
    				+ "FROM rental";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			rentalDto.setRreason0(rs.getInt("공간_협소"));
    			rentalDto.setRreason1(rs.getInt("위생"));
    			rentalDto.setRreason2(rs.getInt("기구_부족"));
    			rentalDto.setRreasonEtcCount(rs.getInt("기타_개수"));
    			return rentalDto;
    		}
    	}catch (Exception e) {System.out.println(e);}
    	return null;
    }
 // 대여 취소 전체 개수 조회 SQL 메소드
    public int getTotalSize2() {
    	try {
    		String sql = "select count(*) from rental where rreason NOT IN('0', '1', '2') AND rreason IS NOT NULL";
    		PreparedStatement ps = conn.prepareStatement(sql);
    	
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {return rs.getInt(1);}
    	}catch (Exception e) {System.out.println(e);}
    	return 0;
    }
    
    // 기타 사유 조회 SQL 처리 메소드
    public ArrayList<RentalDto> cancelFindEtc(int startRow, int display){
        ArrayList<RentalDto> list = new ArrayList<RentalDto>();
        try {
            String sql = "SELECT \r\n"
            		+ "    r.rreason, \r\n"
            		+ "    r.rreason_detail, \r\n"
            		+ "    m.mid, \r\n"
            		+ "    s.sname\r\n"
            		+ "FROM \r\n"
            		+ "    rental r\r\n"
            		+ "INNER JOIN \r\n"
            		+ "    member m ON r.mno = m.mno\r\n"
            		+ "INNER JOIN \r\n"
            		+ "    store s ON r.sno = s.sno\r\n"
            		+ "WHERE \r\n"
            		+ "    r.rreason NOT IN ('0', '1', '2') \r\n"
            		+ "    AND r.rreason IS NOT NULL order by r.rno desc limit ?, ?";
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setInt(1, startRow);
            ps.setInt(2, display);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                RentalDto rentalDto = new RentalDto();
                rentalDto.setRreason(rs.getString("rreason"));
                rentalDto.setRreasonEtc(rs.getString("rreason_detail"));
                rentalDto.setMid(rs.getString("mid"));
                rentalDto.setSname(rs.getString("sname"));
                list.add(rentalDto);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    // 드롭다운 SQL 처리 메소드
    public ArrayList<StoreDto> dropdown(){
    	ArrayList<StoreDto> list = new ArrayList<StoreDto>();
    	try {
    		String sql = "select sno, sname from store order by sno ASC";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			StoreDto storeDto = new StoreDto();
    			storeDto.setSno(rs.getInt("sno"));
    			storeDto.setSname(rs.getString("sname"));
    			list.add(storeDto);
    		}
    	}catch (Exception e) {System.out.println(e);}
    	return list;
    }
    
    
}
