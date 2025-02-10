package rental.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rental.model.dto.MemberDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MemberDao extends Dao{

	@Getter
	private static MemberDao instance = new MemberDao();
	
	public boolean signup(MemberDto memberDto) {
		try {
			String sql = "INSERT INTO member (mid, mpwd, mphone, mgender) VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMid());
			ps.setString(2, memberDto.getMpwd());
			ps.setString(3, memberDto.getMphone());
			ps.setInt(4, memberDto.getMgender());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
}
