package tsys.sales.test;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;


public class TestOrderDAO_01 {
	public static void main(String[] args) {{
		Connection con = null;

		System.out.println("テスト開始");

		// テストのための準備としてデータベースに接続する。
		try{
			con = ConnectionManager.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}

		// ここからテストを行う。
		String memberCode = "CM00002";
		try{
			OrderDAO orderDAO = new OrderDAO(con);
			ArrayList<Order> orderList = orderDAO.findOrder(memberCode);
			for (Order order : orderList) {
				System.out.println("メンバーコード：" + order.getMemberCode());
				System.out.println("受注番号：" + order.getOrderNo());
				System.out.println("注文日" + order.getOrderDate());
				System.out.println("受注合計" + order.getOrderTotal());
				System.out.println("受注商品料金：" + order.getPayment());
			}
		}catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		}finally {
			try{
				if(con != null){
					con.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	}
}
