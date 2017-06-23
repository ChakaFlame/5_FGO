package tsys.sales.logic;

import java.sql.*;
import tsys.sales.common.*;
import tsys.sales.dao.*;
import tsys.sales.entity.*;
import tsys.sales.dao.ConnectionManager;

public class NewAccountLogic {

	public boolean checkAddress(String mail) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Member member = null;
		boolean caflag = false;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			MemberDAO memberDAO = new MemberDAO(con);
			caflag = memberDAO.checkAddress(mail);

			// 検索結果がない場合、エラーを発生させる。
			if(member == null) {
				throw new SalesBusinessException("エラーが発生しました。");
			}

		}catch(SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("エラーが発生しました。");
		}finally {
			try {
				if(con != null){
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("エラーが発生しました。");
			}
		}

		return caflag;
	}
}
