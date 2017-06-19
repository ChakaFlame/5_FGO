/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * SearchCustomerController.java
 *
 */


import java.sql.*;
import jsys.sales.common.*;
import jsys.sales.dao.*;
import jsys.sales.entity.*;

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

			req.setAttribute("memberCode", member.memberCode);
			req.setAttribute("password", member.password);

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
