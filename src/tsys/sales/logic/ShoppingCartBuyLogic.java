package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;
import tsys.sales.entity.Member;

public class ShoppingCartBuyLogic {
	/**
	 * 顧客を検索し、顧客の住所を返す。
	 * @param memberCode
	 * @return 住所
	 * @throws SalesBusinessException データベースエラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public Member findAddress(String memberCode) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Member member = null;
		try {
			//データベースの接続を取得する。
			con = ConnectionManager.getConnection();

			//OrderDAOを生成し、メソッドを呼び出す。
			OrderDAO orderDAO = new OrderDAO(con);
			member = orderDAO.findAddress(memberCode);

			//検索結果がない場合、エラーを発生させる。
			if(member == null) {
				throw new SalesBusinessException("エラーが発生しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("エラーが発生しました。");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("エラーが発生しました。");
			}
		}
	return member;
	}
}
