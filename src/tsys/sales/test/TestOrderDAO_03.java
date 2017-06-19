package tsys.sales.test;
/*
import java.sql.Connection;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;

public class TestOrderDAO_03{
	public static void main(String[] args) {

		Connection con = null;

		// テストのための準備としてデータベースに接続する。

		try {
			con = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ここからテストを行う。
		try {
			OrderDAO orderDAO = new OrderDAO(con);

			Order order = new Order();

			order.setItemCode("test");
			order.setPrice(25000);
			order.setQuantity(2);


			boolean updated = orderDAO.insertOrder(order);

			if (updated) {
				System.out.println("更新完了。");
			}else{
				System.out.println("更新失敗。");
			}

		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try { //データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
*/
