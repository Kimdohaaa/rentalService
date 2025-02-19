package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.PaymentDto;
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
	
	// [2] 사용자 대여 신청 // boolean
	public int addRental(RentalDto rentalDto) {
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
				//////////
				if(count == 1) {
					
					// return true;
					String sql = "select rno from rental where rdate = ? and rtime = ?";
					PreparedStatement p = conn.prepareStatement(sql);
					p.setString(1, rentalDto.getRdate());
					p.setString(2, rentalDto.getRtime());
							
					ResultSet rs = p.executeQuery();
					
					if(rs.next()) {
						int rno = rs.getInt("rno");
						System.out.println(rno);
						return rno;

					}
					/////////
				}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return 0;
		// return false;
	}
	
	// [3] 현재 로그인된 회원의 대여 내역
	public ArrayList<RentalDto> find(int mno, int startRow, int display) {
		ArrayList<RentalDto> list = new ArrayList<RentalDto>();
		try {
			String sql = "select r.* , s.sname from rental r join store s on r.sno = s.sno where r.mno = ? and rstate = 1 order by r.rdate asc , r.rtime asc limit ? ,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
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
            // 기본 SQL 문
            String sql = "UPDATE rental SET rstate = 0, rprice = 0, rreason = ? WHERE rno = ?";
            
            System.out.println(rentalDto.getRreason());
            // rreason 값이 '기타'일 경우 rreason_detail도 함께 업데이트
            if ("reason".equals(rentalDto.getRreason())) {
                sql = "UPDATE rental SET rstate = 0, rprice = 0, rreason = '기타', rreason_detail = ? WHERE rno = ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            
            // '기타'일 경우와 아닐 경우에 따라 파라미터 설정
            if ("reason".equals(rentalDto.getRreason())) {
                // ps.setString(1, rentalDto.getRreason());
                ps.setString(1, rentalDto.getRreasonEtc()); // rreason_detail 값 설정
                ps.setInt(2, rentalDto.getRno());
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
	
	// [5] 가맹점 조회 (index 페이지 출력하기)
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
	
	// [6] DB 결제 처리 및 결제 정보 insert 
	public boolean pay(PaymentDto paymentDto) {
		try {
			String sql = "insert into pay (imp_uid, dprice , rno) values (?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paymentDto.getImp_uid());
			ps.setInt(2, paymentDto.getPaid_amount());
			ps.setInt(3, paymentDto.getRno());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// [7] 환불할 정보 조회
	public PaymentDto findPay(int rno) {
		try {
			System.out.println("번호"+ rno);
			String sql = "select * from pay where rno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				PaymentDto paymentDto = new PaymentDto();
				
				paymentDto.setImp_uid(rs.getString("imp_uid"));
				paymentDto.setPaid_amount(rs.getInt("dprice"));
				
				System.out.println(paymentDto.getImp_uid() + "금액 : " + paymentDto.getPaid_amount());
				
				return paymentDto;
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	// [8] DB 환불처리
	public boolean refund(PaymentDto paymentDto) {
		try {
			String sql = "update pay set refund = true where mid_uid =? and dprice = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paymentDto.getImp_uid());
			ps.setInt(2, paymentDto.getPaid_amount());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
		
	}
	
	// 총페이지 수
	 public int getTotalSize(int sno) {
	    	try {
	    		String sql = "select count(*) from rental where mno = ?";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setInt(1, sno);
	    		ResultSet rs = ps.executeQuery();
	    		if(rs.next()) {return rs.getInt(1);}
	    	}catch (Exception e) {System.out.println(e);}
	    	return 0;
	    }
	
}
