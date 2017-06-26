package tsys.sales.logic;

import tsys.sales.common.*;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderListLogic {
	Connection con = null;


	public ArrayList<Order> orderList (String memberCode) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();
			OrderDAO orderDAO = new OrderDAO(con);
			orderList = orderDAO.findOrder(memberCode);

			if (orderList.isEmpty()){
				throw new SalesBusinessException("注文履歴なし。");
			}

		}catch(SQLException | SalesBusinessException e) {
			e.printStackTrace();
		}finally {
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}

}
