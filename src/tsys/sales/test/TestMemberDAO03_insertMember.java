package tsys.sales.test;

import java.sql.Connection;
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
			member.setName("テストネーム");
			member.setPassword("pass");
			member.setMail("test@tst.com");
			member.setZipCode("111-1111");
			member.setPrefecture("東京都");
			member.setAddress("港区南");
			member.setTel("11-1111-1111");

			member = memberDAO.insertMember(member);		//insertMemberの実行
			System.out.println(member.getName());
			System.out.println(member.getMemberCode());
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
