package rental.model.dao.member;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dao.Dao;
import rental.model.dto.MemberDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MemberDao extends Dao{
	
	

	@Getter
	private static MemberDao instance = new MemberDao();
	
	// [1] 회원 회원가입
	public int signup(MemberDto memberDto) {
		try {
			String sql = "insert into member (mid, mpwd, mphone, maddr, mgender) VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMid());
			ps.setString(2, memberDto.getMpwd());
			ps.setString(3, memberDto.getMphone());
			ps.setString(4, memberDto.getMaddr());
			ps.setInt(5, memberDto.getMgender());

			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return 0;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return 1;
	}
	
	// [2] 회원 로그인
	public int login(MemberDto memberDto) {
		try {
			String sql = "select mno from member where mid = ? and mpwd = ? and mstate=0";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMid());
			ps.setString(2, memberDto.getMpwd());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int mno = rs.getInt("mno");
				
				return mno; // 로그인된 회원번호를 세션 처리를 위해 리턴
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	
	// [3] 내정보 조회
	public MemberDto myinfo(int loginMno) {
		try {
			String sql = "select * from member where mno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginMno);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				MemberDto memberDto = new MemberDto();
				
				memberDto.setMno(rs.getInt("mno"));
				memberDto.setMid(rs.getString("mid"));
				memberDto.setMpwd(rs.getString("mpwd"));
				memberDto.setMphone(rs.getString("mphone"));
				memberDto.setMgender(rs.getInt("mgender"));
				memberDto.setMstate(rs.getInt("mstate"));
				memberDto.setMdate(rs.getString("mdate"));
				memberDto.setMaddr(rs.getString("maddr"));
				
				return memberDto;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	// [4] 회원 정보 수정
	public boolean update(MemberDto memberDto) {
		try {
			String sql = "update member set mpwd =? , mphone =?, mgender =? where mno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMpwd());
			ps.setString(2, memberDto.getMphone());
			ps.setInt(3, memberDto.getMgender());
			ps.setInt(4, memberDto.getMno());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// [4] 회원탈퇴
	public boolean delete(int mno) {
		try {
			String sql = "update member set mstate = 1 where mno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	// [5] 비밀번호 찾기
	public MemberDto findMpwd(MemberDto memberDto) {
		try {
			String sql = "select mpwd from member where mid = ? and mphone = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMid());
			ps.setString(2, memberDto.getMphone());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				MemberDto mpwd = new MemberDto();
				
				memberDto.setMpwd(rs.getString("mpwd"));
				
				return memberDto;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	// [6] 유효성 검사를 위한 회원 전체 조회
	public ArrayList<MemberDto> getMember() {
	    ArrayList<MemberDto> list = new ArrayList<>();
	    try {
	        String sql = "select * from member";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            MemberDto memberDto = new MemberDto();
	            memberDto.setMno(rs.getInt("mno"));
	            memberDto.setMid(rs.getString("mid"));
	            memberDto.setMpwd(rs.getString("mpwd"));
	            memberDto.setMphone(rs.getString("mphone"));
	            memberDto.setMaddr(rs.getString("maddr"));
	            memberDto.setMgender(rs.getInt("mgender"));
	            memberDto.setMstate(rs.getInt("mstate"));
	            memberDto.setMdate(rs.getString("mdate"));
	            list.add(memberDto);
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL 오류 발생: " + e.getMessage());
	    }
	    return list;
	}

}
