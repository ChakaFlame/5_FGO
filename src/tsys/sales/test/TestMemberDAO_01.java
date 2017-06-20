package tsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.MemberDAO;
import tsys.sales.entity.Member;

public class TestMemberDAO_01 {

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
			Member member = memberDAO.findMember("CM0002","pass");

			if(member == null){
				System.out.println("結果なし。");
			}
			else{
			System.out.println("メンバーコード：" + member.getMemberCode());
			System.out.println("メンバー名：" + member.getName());
			}

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
