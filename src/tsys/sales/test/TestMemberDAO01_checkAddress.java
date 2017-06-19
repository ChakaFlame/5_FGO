///**
// * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
// *
// * TestMemberDAO01_checkAddress.java
// *
// */



import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.MemberDAO;
import tsys.sales.entity.Member;

public class TestMemberDAO01_checkAddress {

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
			MemberDAO memberDAO = new MemberDAO(con);
			Member member = memberDAO.findMember("CA0001","pass");

			System.out.println("メンバーコード：" + member.getMemberCode());
			System.out.println("パスワード：" + member.getPassword());


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
