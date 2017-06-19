///**
// * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
// *
// * TestMemberDAO02_findMember.java
// *
// */



import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.MemberDAO;
//import tsys.sales.entity.Member;

public class TestMemberDAO02_findMember {

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
			boolean caflag = memberDAO.checkAddress("hanako@aaa.com");

			//System.out.println("メールアドレス：" + member.getmail());
			System.out.println("既に登録されているメールアドレスならばfalse：" + caflag);

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
