package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Hotel;
import tsys.sales.entity.OrderDetail;

public class OrderDetailLogic {
	static Connection con = null;

	/**
	 *
	 * @param orderNo
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<OrderDetail> orderDetail(int orderNo) {
		ArrayList<OrderDetail> orderDetailList = null;
		try {
			con = ConnectionManager.getConnection();
			OrderDAO orderDAO = new OrderDAO(con);


			orderDetailList = orderDAO.findOrderDetail(orderNo);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return orderDetailList;
	}

	public ArrayList<Hotel> orderHotelDetail(String itemCode) {
		ArrayList<Hotel> orderHotelDetailList = null;
		try {
			con = ConnectionManager.getConnection();
			HotelDAO hotelDao = new HotelDAO(con);


			orderHotelDetailList = HotelDAO.findHotel(itemCode);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return orderHotelDetailList;
	}
}
