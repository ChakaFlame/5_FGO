package tsys.sales.logic;

import java.sql.*;
import tsys.sales.common.*;
import tsys.sales.dao.*;
import tsys.sales.entity.*;
import tsys.sales.dao.ConnectionManager;

public class AccountConfirmationLogic {

	public Member memberConfirmed(Member member) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Member member_IM = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			MemberDAO memberDAO = new MemberDAO(con);
			member_IM = memberDAO.insertMember(member);					//member:ユーザの入力情報を持つ//引数に自動采番されたメンバーコードを持ったmember_iM

			// 検索結果がない場合、エラーを発生させる。
			if(member_IM == null) {
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

		return member_IM;
	}
}
