package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.CustomerDAO;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;

public class OrderCancelLogic {

	public void orderCancel(int orderNo, ArrayList<OrderDetail> orderDetailList)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Order order = null;
		Hotel hotel = null;

		try {
			con = ConnectionManager.getConnection();
			/**
			 * orderDAOにorderNoを参照して注文削除の依頼
			 */
			OrderDAO orderDAO = new OrderDAO(con);
			order = orderDAO.deleteOrder(orderNo);

			/**
			 * HotelDAOにorderDetailListを参照させて、予約数を元に戻す
			 */
			HotelDAO hotelDAO= new HotelDAO(con);
			hotel = hotelDAO.updateStock(orderDetailList);


		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
			}
		}
	}
}
