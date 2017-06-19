package tsys.sales.logic;

import java.sql.*;
import java.util.*;
import tsys.sales.dao.HotelDAO;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Hotel;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;
import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;

public class OrderCancelLogic {

	public void orderCancel(int orderNo, ArrayList<OrderDetail> orderDetailList)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Order order = null;
		Hotel hotel = null;
		Boolean orderflag,hotelflag;

		try {
			con = ConnectionManager.getConnection();
			/**
			 * orderDAOにorderNoを参照して注文削除の依頼
			 */
			OrderDAO orderDAO = new OrderDAO(con);
			orderflag = orderDAO.deleteOrder(orderNo);

			/**
			 * HotelDAOにorderDetailListを参照させて、予約数を元に戻す
			 */
			HotelDAO hotelDAO= new HotelDAO(con);
			hotelflag = hotelDAO.updateStock(orderDetailList);


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
