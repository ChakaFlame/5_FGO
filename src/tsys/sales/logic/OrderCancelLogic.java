package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.*;

public class OrderCancelLogic {
	/**
	 * ホテルを検索し、
	 * OrderMasterテーブル,OrderDetailテーブルに受注情報を追加
	 * Hotelテーブルの在庫情報を更新
	 * @param order
	 * @return
	 * @throws SalesBusinessException
	 * @throws SalesSystemException
	 */
	public boolean orderCancel(int orderNo, ArrayList<OrderDetail>orderDetailList) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		boolean cancelFlag = false;

		try {
			//データベースの接続を取得する。
			con = ConnectionManager.getConnection();

			//OrderMasterテーブル
			//OrderDetailテーブルの更新
			//OrderDAOを生成し、メソッドを呼び出す。
			OrderDAO orderDAO = new OrderDAO(con);
			con.setAutoCommit(false);
			orderDAO.deleteOrder(orderNo, orderDetailList);

			//Hotelテーブルの更新
			//HotelDAOを生成し、メソッドを呼び出す。
			HotelDAO hotelDAO = new HotelDAO(con);
			hotelDAO.updateStock(orderDetailList);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("エラーが発生しました。");
		} finally {
			try {
				if (con != null) {
					con.commit();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("エラーが発生しました。");
			}
		}
		cancelFlag = true;
		return cancelFlag;
	}
}
