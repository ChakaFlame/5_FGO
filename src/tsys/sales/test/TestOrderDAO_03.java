package tsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

			String string_date = "2017-06-30";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date=null;
			try {
				date = format.parse(string_date);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			System.out.println(string_date);
			System.out.println(date);

			order.setOrderDate(date);
			order.setOrderTotal(2000);
			order.setMemberCode("test");
			order.setPayment("01");

			String updateNo = orderDAO.insertOrder(order);

			if (updateNo != null) {
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

