package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.OrderDetail;

public class OrderDetailLogic {
	static Connection con = null;
	/**
	 *
	 * @param orderNo
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList<OrderDetail> orderDetail(int orderNo) throws SQLException{

		con = ConnectionManager.getConnection();
		OrderDAO orderDAO = new OrderDAO(con);
		ArrayList<OrderDetail> orderDetailList = orderDAO.findOrderDetail(orderNo);
		
		return orderDetailList;
	}
}
