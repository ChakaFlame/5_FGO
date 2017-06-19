///**
// * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
// *
// * TestMemberDAO03_insertMember.java
// *
// */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.MemberDAO;
import tsys.sales.entity.Member;

public class TestMemberDAO03_insertMember {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Member member2 = null;
		// テストのための準備としてデータベースに接続する。

		try {
			con = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ここからテストを行う。
		try {
			MemberDAO memberDAO = new MemberDAO(con);		//登録テスト用データの設定
			Member member = new Member();
			member.setMemberCode("TE0001");
			member.setName("テストネーム");
			member.setPassword("pass");
			member.setMail("test@tst.com");
			member.setZipCode("111-1111");
			member.setPrefecture("東京都");
			member.setAddress("港区南");
			member.setTel("11-1111-1111");

			member = memberDAO.insertMember(member);		//insertMemberの実行

	    	PreparedStatement stmt = null;
	    	ResultSet res = null; 							//結果セット
	    	String sql = "SELECT MemberCode" + " FROM Member WHERE MemberCode = TE0001";		//DBに登録したデータを改めて検索、確認

	    	stmt = con.prepareStatement(sql);
	    	res = stmt.executeQuery();

	    	if (res.next()) {
    					member2 = new Member(
    					res.getString("memberCode"),
    					res.getString("name"),
    					res.getString("password"),
    					res.getString("mail"),
    					res.getString("zipcode"),
    					res.getString("prefecture"),
    					res.getString("address"),
    					res.getString("tel")
    					);
    		}

			System.out.println("DBに登録した情報：" + member2.getMemberCode() +  member2.getName() + member2.getPassword() +
					member2.getMail() + member2.getZipCode() + member2.getPrefecture() + member2.getAddress() + member2.getTel() );		//登録データの表示


		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try {// データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
