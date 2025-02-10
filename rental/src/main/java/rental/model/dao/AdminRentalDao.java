package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.RentalDto;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AdminRentalDao extends Dao{
	@Getter
	private static AdminRentalDao instance = new AdminRentalDao();
	
	public boolean add(RentalDto rentalDto) {
		try {
			String sql = "INSERT INTO rental (rdate, rtime, rcount, sno) VALUES			\r\n"
					+ "(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rentalDto.getRdate());
			ps.setString(2, rentalDto.getRtime());
			ps.setInt(3, rentalDto.getRcount());
			ps.setInt(4, rentalDto.getSno());
			int count = ps.executeUpdate();
			if(count == 1) {return true;}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	 // ✅ 예약 여부 확인 함수 (DB에서 조회)
    @SuppressWarnings("unused")
	private boolean checkExistingReservation(RentalDto rentalDto) {
        
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
        } catch (Exception e) {
            System.out.println("예약 조회 중 오류 발생: " + e.getMessage());
        }
        return false; // 예약 없음 (예약 가능)
    }
}
