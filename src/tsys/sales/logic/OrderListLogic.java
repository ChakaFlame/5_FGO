package tsys.sales.logic;

import tsys.sales.common.*;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

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
			else{
				Date current = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(current);
				cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)-6));
				current = cal.getTime();

				DateFormat format = new SimpleDateFormat("yyyy-M-d");

				Iterator<Order> iter = orderList.iterator();
				while (iter.hasNext()){
					Order order = iter.next();
					Date orderDate = format.parse(order.getOrderDate());
					if(orderDate.before(current)){
						iter.remove();
					}
				}
			}
		}catch(SQLException | SalesBusinessException | ParseException e) {
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

	public ArrayList<Order> orderList (int orderNo) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		String orderNoStr=String.valueOf(orderNo);
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();
			OrderDAO orderDAO = new OrderDAO(con);
			orderList = orderDAO.findOrder(orderNoStr);

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
