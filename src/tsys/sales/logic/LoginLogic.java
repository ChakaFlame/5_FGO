package tsys.sales.logic;

import java.sql.*;
import tsys.sales.common.*;
import tsys.sales.dao.*;
import tsys.sales.entity.*;
import tsys.sales.dao.ConnectionManager;

public class LoginLogic {

	public boolean login(String memberCode, String password) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Member member = null;
		boolean loginflag = false;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			MemberDAO memberDAO = new MemberDAO(con);
			member = memberDAO.findMember(memberCode,password);

			// 検索結果がない場合、エラーを発生させる。
			if(member == null) {
				throw new SalesBusinessException("条件に一致する商品がありません。");
			}

			req.setAttribute("memberCode", member.getMemberCode());
			req.setAttribute("password", member.getPassword());

			loginflag = true;

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

		return loginflag;
	}
}
