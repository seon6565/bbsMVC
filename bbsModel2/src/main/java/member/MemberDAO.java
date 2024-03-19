/**
 * 
 */
package member;



import common.JDBConnect;

/**
 * 
 */
public class MemberDAO extends JDBConnect {
	
	public MemberDAO(){super();}
	
	public MemberDAO(String driver, String url, String dbId, String dbPwd){
		super(driver,url,dbId,dbPwd);
	}
	
	public MemberDTO getMemberInfo(String id, String pwd) {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from tbl_member where memberId=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd").equals(pwd)) {
					dto.setMemberId(rs.getString("memberID"));
					dto.setName(rs.getString("name"));
					dto.setPwd(rs.getString("pwd"));
					dto.setJumin(rs.getString("jumin"));
				}
				
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
}
